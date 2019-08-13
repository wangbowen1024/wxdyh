package com.wxgzh.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxgzh.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SubscribeController class
 * 订阅控制器
 *
 * @author BowenWang
 * @date 2019/08/11
 */
@Controller
@RequestMapping("/subscribe")
public class SubscribeController {
    @Autowired
    private EventService eventService;

    /**
     * 返回当前订阅消息
     * @return
     */
    @PostMapping("/content")
    @ResponseBody
    public Object getSubscribeReplay() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", eventService.findSubscribeReplay());
        return jsonObject;
    }

    /**
     * 修改自动回复订阅消息
     * @param content
     * @return
     */
    @PostMapping("/{content}")
    @ResponseBody
    public Object getSubscribeReplay(@PathVariable String content) {
        JSONObject jsonObject = new JSONObject();
        int rows = eventService.updateSubscribeReplay(content);
        if (rows == 1) {
            jsonObject.put("status", "success");
            MessageController.autoReplaySubscribe = content;
        } else {
            jsonObject.put("status", "fail");
        }
        return jsonObject;
    }
}
