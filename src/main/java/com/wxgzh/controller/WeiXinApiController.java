package com.wxgzh.controller;

import com.wxgzh.domain.*;
import com.wxgzh.enums.Message;
import com.wxgzh.service.ImageService;
import com.wxgzh.service.TextService;
import com.wxgzh.service.VoiceService;
import com.wxgzh.utils.SignUtil;
import com.wxgzh.utils.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * WeiXinApiController class
 *
 * @author BowenWang
 * @date 2019/08/03
 */
@Controller
@RequestMapping("/CodeWen")
public class WeiXinApiController {

    @Autowired
    private TextService textMessageService;
    @Autowired
    private ImageService imageMessageService;
    @Autowired
    private VoiceService voiceService;

    /**
     * 只有返回成功echostr，微信才会认可这个接口
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public void checkApi(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
    }

    /**
     * 接收用户发送的信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/api", method = RequestMethod.POST, produces = {"application/xml;charset=UTF-8"})
    @ResponseBody
    public Object receiveMessage(@RequestBody String request) throws Exception {
        Map<String, Object> params = XmlUtil.xmlStrToMap(request);
        String fromUserName = (String) params.get("FromUserName");
        // 获取消息类型
        String msgType = (String) params.get("MsgType");
        if (Message.TEXT.getMsgType().equals(msgType) || Message.VOICE.getMsgType().equals(msgType)) {
            /**
             * 如果是文本或语音（语音会转为文本）消息
             */
            // 请求内容
            String content;
            if (Message.TEXT.getMsgType().equals(msgType)) {
                RequestText message = (RequestText) XmlUtil.mapToBean(params, RequestText.class);
                textMessageService.saveText(message);
                content = message.getContent();
            } else {
                RequestVoice message = (RequestVoice) XmlUtil.mapToBean(params, RequestVoice.class);
                voiceService.saveVoice(message);
                content = message.getRecognition();
            }
            // **************自定义匹配规则以及对应的业务,注意匹配顺序****************
            if (content.contains("获取图片")) {
                // 测试返回图片
                return responseParse(imageMessageService.returnImage("jVBUH8jU8G738piFuRr2U8s3Z5eI5uokfsFJgB20Wa-y2FMbIsTcH0ijavMD0Wfn"),
                        fromUserName);
            }
            if (content.length() > 0) {
                // 机器人回复
                return responseParse(textMessageService.getRobotReply(content), fromUserName);
            }
            // ******************************END**************************************
        } else if (Message.IMAGE.getMsgType().equals(msgType)) {
            /**
             * 如果是图片消息
             */
            RequestImage message = (RequestImage) XmlUtil.mapToBean(params, RequestImage.class);
            imageMessageService.saveImage(message);
            return checkAdminMessage(params, fromUserName);
        }
        return "success";
    }

    /**
     * 处理返回结果
     * @param obj Response响应消息类
     * @param toUserName 接收者ID
     * @return
     */
    private Object responseParse(BaseResponseMessage obj, String toUserName) {
        if (obj != null) {
            // 配置基本回复属性
            obj.setToUserName(toUserName);
            obj.setFromUserName(ConfigInfo.wxgzhId);
            obj.setCreateTime(String.valueOf(System.currentTimeMillis()));
        }
        return obj;
    }

    /**
     * 检查是否是管理员消息。是：若有MediaId返回
     * @param map
     * @param fromUserName
     * @return
     */
    private Object checkAdminMessage(Map<String, Object> map, String fromUserName ) {
        // 如果发送者是管理员
        if (ConfigInfo.adminSet.contains(fromUserName)) {
            // 如果包含MediaId值
            if (map.containsKey("MediaId")) {
                return responseParse(textMessageService.returnText("MediaId: " + map.get("MediaId")),
                        fromUserName);
            }
        }
        return "success";
    }
}
