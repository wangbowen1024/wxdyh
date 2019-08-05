package com.wxgzh.controller;

import com.wxgzh.domain.common.ConfigInfo;
import com.wxgzh.domain.request.ImageRequest;
import com.wxgzh.domain.request.TextRequest;
import com.wxgzh.domain.request.VideoRequest;
import com.wxgzh.domain.request.VoiceRequest;
import com.wxgzh.domain.response.BaseResponseMessage;
import com.wxgzh.enums.Message;
import com.wxgzh.service.*;
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
    @Autowired
    private NewsServce newsServce;

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
                TextRequest message = (TextRequest) XmlUtil.mapToBean(params, TextRequest.class);
                textService.saveText(message);
                content = message.getContent();
            } else {
                // 接收语音消息
                VoiceRequest message = (VoiceRequest) XmlUtil.mapToBean(params, VoiceRequest.class);
                voiceService.saveVoice(message);
                content = message.getRecognition();
                return checkAdminMessage(params, fromUserName);
            }
            // ******************************自定义匹配规则以及对应的业务,注意匹配顺序**********************************
            /*
             * 获取投票
             */
            if (content.contains("获取投票")) {
                return responseParse(newsServce.returnNews("投票", "投票回复测试","http://mmbiz.qpic.cn/mmbiz_jpg/r4zXRibcqibMVuO1nedMTA0FxSC9ZnQproEa1HdGiagh5iaFL5l01cCN3ctTl55pJH4JJNP0Rf9mqmb5allCiaxroDw/0?wx_fmt=jpeg", "http://mp.weixin.qq.com/s?__biz=MzA5NTE5OTk0OQ==&mid=100000005&idx=1&sn=e646304a7b0de01a725a25c8ef7ddbef&chksm=1043b6a727343fb1d753dee6f5a8b4c481438bbed062df496f9d4ce164512a34b4ad3b70e939#rd"),fromUserName);
            }
            /*
             * 获取文章
             */
            if (content.contains("获取文章")) {
                return responseParse(newsServce.returnNews("文章", "文章回复测试", "http://mmbiz.qpic.cn/mmbiz_jpg/r4zXRibcqibMVuO1nedMTA0FxSC9ZnQpronIwDSCyZpFIkbJwblbIibmvRYXKib1siaoQ2icOat34M8660lDVsSkQzpQ/0?wx_fmt=jpeg","http://mp.weixin.qq.com/s?__biz=MzA5NTE5OTk0OQ==&mid=100000001&idx=1&sn=76fe90dfa80b68eef04376f303e8da5c&chksm=1043b6a327343fb512b6433fc31258e190b78f9479d2530af9890441bfc1c49f18049727b12c#rd"), fromUserName);
            }
            /*
            * 测试返回图片
            */
            if (content.contains("获取图片")) {
                return responseParse(imageService.returnImage("vNC5zO819IakQUwBUE5YardQ-8AxGc3q6-5KkT_C0LI"),
                        fromUserName);
            }
            /*
             * 测试返回语音
             */
            if (content.contains("获取语音")) {
                return responseParse(voiceService.returnVoice("vNC5zO819IakQUwBUE5YaiIOnLpJAQdpccvzYrAJuuU"),
                        fromUserName);
            }
            /*
             * 测试返回视频
             */
            if (content.contains("获取视频")) {
                return responseParse(videoService.returnVideo("vNC5zO819IakQUwBUE5YaibP2SENLKyEUkiiftUG724", "测试视频", "用于测试返回视频"),
                        fromUserName);
            }
            if (content.length() > 0) {
                // 机器人回复
                return responseParse(textService.getRobotReply(content), fromUserName);
            }
            // ----------------------------------      E      N      D      ----------------------------------------
        } else if (Message.IMAGE.getMsgType().equals(msgType)) {
            // 接收是图片消息
            ImageRequest message = (ImageRequest) XmlUtil.mapToBean(params, ImageRequest.class);
            imageService.saveImage(message);
            return checkAdminMessage(params, fromUserName);
        } else if (Message.VIDEO.getMsgType().equals(msgType) || Message.SHORT_VIDEO.getMsgType().equals(msgType)) {
            // 接收视频（短视频）消息
            VideoRequest message = (VideoRequest) XmlUtil.mapToBean(params, VideoRequest.class);
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
