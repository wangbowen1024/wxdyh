package com.wxdyh.service;

import com.wxdyh.domain.common.User;

import java.util.List;

/**
 * EventService class
 *
 * @author BowenWang
 * @date 2019/08/11
 */
public interface EventService {
    /**
     * 订阅
     * @param openId
     */
    void subscribe(String openId);

    /**
     * 取消订阅
     * @param openId
     */
    void unsubscribe(String openId);

    /**
     * 查询自动回复关注者消息内容
     * @return
     */
    String findSubscribeReplay();

    /**
     * 更新自动回复关注者消息内容
     * @param content
     */
    int updateSubscribeReplay(String content);

    /**
     * 查找所有管理员
     * @return
     */
    List<User> findAllAdmin();
}
