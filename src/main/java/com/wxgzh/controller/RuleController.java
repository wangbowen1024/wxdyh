package com.wxgzh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxgzh.dao.RuleDao;
import com.wxgzh.domain.common.Rule;
import com.wxgzh.service.RuleService;
import com.wxgzh.utils.RegexpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * RuleController class
 *
 * @author BowenWang
 * @date 2019/08/09
 */
@Controller
@RequestMapping("/rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;


    /**
     * 检查匹配规则
     * @param json
     * @return
     */
    @PostMapping("/check")
    @ResponseBody
    public Object checkRule(@RequestBody String json) {
        JSONObject result = JSON.parseObject(json);
        return RegexpUtil.matching(result.getString("text"), result.getString("rule"),
                result.getString("type"));
    }

    /**
     * 添加规则
     * @param json
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object saveRule(@RequestBody String json) {
        Rule rule = JSON.parseObject(json, Rule.class);
        return ruleService.saveRule(rule);
    }

    /**
     * 获取规则列表
     * @return
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public Object getRules() {
        return ruleService.findAllRules();
    }

    /**
     * 删除规则
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Object deleteRule(@PathVariable Integer id) {
        return ruleService.deleteRule(id);
    }


}
