package com.wxgzh.controller;

import com.wxgzh.domain.*;
import com.wxgzh.domain.request.RequestImage;
import com.wxgzh.domain.request.RequestText;
import com.wxgzh.domain.request.RequestVideo;
import com.wxgzh.domain.request.RequestVoice;
import com.wxgzh.domain.response.BaseResponseMessage;
import com.wxgzh.enums.Message;
import com.wxgzh.service.ImageService;
import com.wxgzh.service.TextService;
import com.wxgzh.service.VideoService;
import com.wxgzh.service.VoiceService;
import com.wxgzh.utils.AccessTokenUtil;
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
    private TextService textService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private VoiceService voiceService;
    @Autowired
    private VideoService videoService;

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
        // 获取发送者ID
        String fromUserName = (String) params.get("FromUserName");
        // 获取消息类型
        String msgType = (String) params.get("MsgType");
        if (Message.TEXT.getMsgType().equals(msgType) || Message.VOICE.getMsgType().equals(msgType)) {
            // 用于储存文本或语音转换文本结果
            String content;
            if (Message.TEXT.getMsgType().equals(msgType)) {
                // 接收文本消息
                RequestText message = (RequestText) XmlUtil.mapToBean(params, RequestText.class);
                textService.saveText(message);
                content = message.getContent();
            } else {
                // 接收语音消息
                RequestVoice message = (RequestVoice) XmlUtil.mapToBean(params, RequestVoice.class);
                voiceService.saveVoice(message);
                content = message.getRecognition();
                return checkAdminMessage(params, fromUserName);
            }
            // ******************************自定义匹配规则以及对应的业务,注意匹配顺序**********************************
            /*
             * 获取TOKEN
             */
            if (content.contains("获取令牌")) {
                return responseParse(textService.returnText(AccessTokenUtil.getAccessToken()), fromUserName);
            }
            /*
            * 测试返回图片
            */
            if (content.contains("获取图片")) {
                return responseParse(imageService.returnImage("nfB-1AErW9_nQNYAk2DBWyQkB6-04SQgf_T39pGyVfEf5ne0z4xpd60Zb7ssbJPn"),
                        fromUserName);
            }
            /*
             * 测试返回语音
             */
            if (content.contains("获取语音")) {
                return responseParse(voiceService.returnVoice("O3v7AI7NmGyFdggob9aiUm3_B0G9dGv9A4-YYJxRu_r0c-_cDH5gLaJL72VdnyBW"),
                        fromUserName);
            }
            /*
             * 测试返回视频
             */
            if (content.contains("获取视频")) {
                return responseParse(videoService.returnVideo("VQSIN2EmE5LVqbwYF7ri6OtANN8G64cUTeAGZ5NhYjD-1_whXHsrtyEvUq6ztrlG", "测试视频", "用于测试返回视频"),
                        fromUserName);
            }
            if (content.length() > 0) {
                // 机器人回复
                return responseParse(textService.getRobotReply(content), fromUserName);
            }
            // ----------------------------------      E      N      D      ----------------------------------------
        } else if (Message.IMAGE.getMsgType().equals(msgType)) {
            // 接收是图片消息
            RequestImage message = (RequestImage) XmlUtil.mapToBean(params, RequestImage.class);
            imageService.saveImage(message);
            return checkAdminMessage(params, fromUserName);
        } else if (Message.VIDEO.getMsgType().equals(msgType) || Message.SHORT_VIDEO.getMsgType().equals(msgType)) {
            // 接收视频（短视频）消息
            RequestVideo message = (RequestVideo) XmlUtil.mapToBean(params, RequestVideo.class);
            videoService.saveVideo(message);
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
                return responseParse(textService.returnText("MediaId: " + map.get("MediaId")),
                        fromUserName);
            }
        }
        return "success";
    }
}
