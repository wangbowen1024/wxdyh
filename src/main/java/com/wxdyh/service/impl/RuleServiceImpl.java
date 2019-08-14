package com.wxdyh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wxdyh.controller.MessageController;
import com.wxdyh.dao.RuleDao;
import com.wxdyh.domain.common.Rule;
import com.wxdyh.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * RuleServiceImpl class
 *
 * @author BowenWang
 * @date 2019/08/10
 */
@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleDao ruleDao;

    @Override
    public JSONObject saveRule(Rule rule) {
        JSONObject result = new JSONObject();
        int rows = ruleDao.saveRule(rule);
        if (rows == 1) {
            // 保存成功，缓存中也更新
            MessageController.ruleList.add(rule);
            result.put("status", "success");
        } else {
            result.put("status", "fail");
        }
        return result;
    }

    @Override
    public JSONObject deleteRule(Integer id) {
        JSONObject result = new JSONObject();
        int rows = ruleDao.deleteRule(id);
        if (rows == 1) {
            // 删除成功，缓存中也删除
            Iterator<Rule> iterator = MessageController.ruleList.iterator();
            while (iterator.hasNext()) {
                Rule rule = iterator.next();
                if (rule.getId().equals(id)) {
                    iterator.remove();
                    break;
                }
            }
            result.put("status", "success");
        } else {
            result.put("status", "fail");
        }
        return result;
    }

    @Override
    public List<Rule> findAllRules() {
        List<Rule> ruleList = ruleDao.findAllRules();
        // 将规则列表存入内存缓存
        if (MessageController.ruleList == null) {
            MessageController.ruleList = ruleList;
        }
        return ruleList;
    }
}
