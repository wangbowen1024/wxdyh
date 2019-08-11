package com.wxgzh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxgzh.domain.common.ConfigInfo;
import com.wxgzh.domain.common.Rule;
import com.wxgzh.domain.material.Item;
import com.wxgzh.domain.material.Video;
import com.wxgzh.domain.response.BaseResponseMessage;
import com.wxgzh.enums.MaterialEnum;
import com.wxgzh.service.*;
import com.wxgzh.utils.HttpUtil;
import com.wxgzh.utils.RegexpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    private EventService eventService;
    /**
     * 规则列表
     */
    public static List<Rule> ruleList;
    /**
     * 自动回复关注者消息内容
     */
    public static String autoReplaySubscribe;
    /**
     * 判断是否是首位用户的标志
     */
    public static boolean isFirstUser = true;

    /**
     * 用于处理用户的文字消息
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/message")
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
                return replayMessage(MaterialEnum.valueOf(rule.getReplayType()), parseRuleToResponse(rule), sender);
            }
        }
        /********  自定义匹配规则以及对应的业务,注意匹配顺序和逻辑关系(或者自行转发到其他Controller进行处理)  *********/

        // 机器人回复
        if (message.length() > 0) {
            return replayMessage(MaterialEnum.TEXT, getRobotReply(message), sender);
        }

        /** ----------------------------------      E      N      D      -------------------------------------------- */
        return "success";
    }

    /**
     * 事件消息处理
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/event")
    @ResponseBody
    public Object processEvent(HttpServletRequest request, HttpServletResponse response) {
        // 事件内容
        String event = (String) request.getAttribute("event");
        // 发送者用户ID
        String sender = (String) request.getAttribute("sender");
        // 订阅消息
        if ("subscribe".equals(event)) {
            eventService.subscribe(sender);
            if (autoReplaySubscribe == null) {
                autoReplaySubscribe = eventService.findSubscribeReplay();
            }
            if (MessageController.isFirstUser) {
                MessageController.isFirstUser = false;
                return replayMessage(MaterialEnum.TEXT, "欢迎关注公众号！o(*￣▽￣*)ブ您是首位关注公共号的用户，" +
                        "自动成为管理员。赶快登陆公众号后台管理进行操作把", sender);
            }
            return replayMessage(MaterialEnum.TEXT, autoReplaySubscribe, sender);
        } else if ("unsubscribe".equals(event)) {
            // 取消订阅消息
            eventService.unsubscribe(sender);
        }
        return "success";
    }

    /**
     * 回复消息
     * @param type 回复类型
     * @param obj 回复的内容（文字、语音、图片传String,视频传Video,图文传Item）
     * @param toUserName 发送者ID
     * @return
     */
    private BaseResponseMessage replayMessage(MaterialEnum type, Object obj, String toUserName) {
        BaseResponseMessage responseMessage = null;
        if (MaterialEnum.TEXT.equals(type)) {
            responseMessage = textService.returnText((String) obj);
        } else if (MaterialEnum.IMAGE.equals(type)) {
            responseMessage = imageService.returnImage((String) obj);
        } else if (MaterialEnum.VIDEO.equals(type)) {
            responseMessage = videoService.returnVideo((Video) obj);
        } else if (MaterialEnum.VOICE.equals(type)) {
            responseMessage = voiceService.returnVoice((String) obj);
        } else if (MaterialEnum.NEWS.equals(type)) {
            responseMessage = newsServce.returnNews((Item) obj);
        }
        // 配置基本回复属性
        if (responseMessage != null) {
            responseMessage.setToUserName(toUserName);
            responseMessage.setFromUserName(ConfigInfo.WXGZH_ID);
            responseMessage.setCreateTime(String.valueOf(System.currentTimeMillis()));
        }
        return responseMessage;
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

    /** ***************************************  以下添加自定义功能函数  ******************************************** */

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
