package com.wxgzh.service.impl;

import com.wxgzh.controller.MessageController;
import com.wxgzh.dao.EventDao;
import com.wxgzh.domain.common.ConfigInfo;
import com.wxgzh.domain.common.User;
import com.wxgzh.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * EventServiceImpl class
 *
 * @author BowenWang
 * @date 2019/08/11
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    public void subscribe(String openId) {
        User user = eventDao.findUserById(openId);
        // 如果该用户已经订阅过，且不再黑名单中。更新状态
        if (user != null && user.getStatus() != -1) {
            user.setStatus(1);
            eventDao.updateStatus(user);
        } else {
            User u = new User();
            u.setOpenId(openId);

            // 如果数据库中没有用户，那么该用户成为管理员
            if (MessageController.isFirstUser) {
                if (eventDao.totalUser() != 0) {
                    MessageController.isFirstUser = false;
                }
            }
            if (MessageController.isFirstUser) {
                u.setAuthority("admin");
                ConfigInfo.ADMIN_SET.add(openId);
            } else {
                u.setAuthority("user");
            }
            eventDao.saveUser(u);
        }
    }

    @Override
    public void unsubscribe(String openId) {
        User user = eventDao.findUserById(openId);
        user.setStatus(0);
        eventDao.updateStatus(user);
    }

    @Override
    public String findSubscribeReplay() {
        return eventDao.findSubscribeMessage();
    }

    @Override
    public int updateSubscribeReplay(String content) {
        return eventDao.updateSubscribeMessage(content);
    }

    @Override
    public List<User> findAllAdmin() {
        return eventDao.findAllAdmin();
    }
}
