package com.wxdyh.service;

import com.alibaba.fastjson.JSONObject;
import com.wxdyh.domain.common.Rule;

import java.util.List;

/**
 * RuleService class
 *
 * @author BowenWang
 * @date 2019/08/10
 */
public interface RuleService {
    /**
     * 添加规则
     * @param rule
     * @return
     */
    JSONObject saveRule(Rule rule);

    /**
     * 删除规则
     * @param id
     * @return
     */
    JSONObject deleteRule(Integer id);

    /**
     * 查询所有规则
     * @return
     */
    List<Rule> findAllRules();
}
