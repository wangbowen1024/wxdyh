package com.wxgzh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxgzh.domain.common.ConfigInfo;
import com.wxgzh.domain.common.Rule;
import com.wxgzh.domain.material.Item;
import com.wxgzh.domain.response.BaseResponseMessage;
import com.wxgzh.enums.MaterialEnum;
import com.wxgzh.service.*;
import com.wxgzh.utils.HttpUtil;
import com.wxgzh.utils.RegexpUtil;
import com.wxgzh.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;


/**
 * MessageController class
 * 用户消息处理控制器
 *
 * @author BowenWang
 * @date 2019/08/10
 */
@Controller
public class MessageController {

    @Autowired
    private TextService textService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private VoiceService voiceService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private NewsServce newsServce;
    @Autowired
    private RuleService ruleService;

    public static List<Rule> ruleList;

    /**
     * 用于处理用户的文字消息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/message")
    @ResponseBody
    public Object processMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 用户发送的文字消息内容
        String message = (String) request.getAttribute("message");
        // 发送者用户ID
        String sender = (String) request.getAttribute("sender");
        // 遍历按顺序匹配规则
        if (ruleList == null) {
            ruleList = ruleService.findAllRules();
        }
        for (Rule rule : ruleList) {
            JSONObject matching = RegexpUtil.matching(message, rule.getContent(), rule.getType());
            if ("success".equals(matching.getString("status"))) {
                return replayMessage(parseRuleToResponse(rule), sender);
            }
        }
        /***************************自定义匹配规则以及对应的业务,注意匹配顺序和逻辑关系********************************/

        /**
         * 获取TOKEN
         */
        if (message.contains("获取令牌")) {
            return replayMessage(textService.returnText(TokenUtil.getAccessToken()), sender);
        }
        if (message.length() > 0) {
            // 机器人回复
            return replayMessage(textService.returnText(getRobotReply(message)), sender);
        }
        /** ----------------------------------      E      N      D      -------------------------------------------- */
        return "success";
    }

    /**
     * 处理返回结果
     * @param obj Response响应消息类
     * @param toUserName 接收者ID
     * @return
     */
    private Object replayMessage(BaseResponseMessage obj, String toUserName) {
        if (obj != null) {
            // 配置基本回复属性
            obj.setToUserName(toUserName);
            obj.setFromUserName(ConfigInfo.WXGZH_ID);
            obj.setCreateTime(String.valueOf(System.currentTimeMillis()));
        }
        return obj;
    }

    /**
     * 将规则回复内容转为可回复类型
     * @param rule
     * @return
     */
    private BaseResponseMessage parseRuleToResponse(Rule rule) {
        if (MaterialEnum.TEXT.getType().equals(rule.getReplayType())) {
            // 文字
            return textService.returnText(rule.getReplayContent());
        }else if (MaterialEnum.VOICE.getType().equals(rule.getReplayType())) {
            // 语音
            return voiceService.returnVoice(rule.getReplayContent());
        }else if (MaterialEnum.IMAGE.getType().equals(rule.getReplayType())) {
            // 图片
            return imageService.returnImage(rule.getReplayContent());
        }else if (MaterialEnum.VIDEO.getType().equals(rule.getReplayType())) {
            // 视频
            JSONObject jsonObject = MaterialController.videoMap.get(rule.getReplayContent());
            return videoService.returnVideo(rule.getReplayContent(), jsonObject.getString("name"),"");
        } else if (MaterialEnum.NEWS.getType().equals(rule.getReplayType())) {
            // 图文
            JSONObject jsonObject = MaterialController.newsMap.get(rule.getReplayContent());
            return newsServce.returnNews(jsonObject.getString("title"), jsonObject.getString("digest"),
                    jsonObject.getString("thumb_url"), jsonObject.getString("url"));
        }
        return textService.returnText("服务器繁忙");
    }



    /**
     * 获取机器人回复信息
     * @param question
     * @return
     */
    private String getRobotReply(String question) {
        HashMap<String, String> map = new HashMap<>(1);
        map.put("question", question);
        String result = HttpUtil.doPost("http://101.37.245.209:9999/robot", map);
        JSONObject jsonObject = (JSONObject) JSON.parse(result);
        JSONObject semantic = jsonObject.getJSONArray("semantic").getJSONObject(0);
        JSONObject slots = semantic.getJSONArray("slots").getJSONObject(0);
        return slots.getString("normValue");
    }

}
