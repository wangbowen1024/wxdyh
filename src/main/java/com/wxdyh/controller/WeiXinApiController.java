package com.wxdyh.controller;


import com.wxdyh.domain.common.ConfigInfo;
import com.wxdyh.domain.request.*;
import com.wxdyh.enums.MaterialEnum;
import com.wxdyh.service.*;
import com.wxdyh.utils.SignUtil;
import com.wxdyh.utils.XmlUtil;
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
     * @param xml
     * @return
     */
    @RequestMapping(value = "/api", method = RequestMethod.POST, produces = {"application/xml;charset=UTF-8"})
    public void receiveMessage(@RequestBody String xml, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        request.setAttribute("forwardCheck", ConfigInfo.TOKEN);
        Map<String, Object> params = XmlUtil.xmlStrToMap(xml);
        // 获取发送者ID
        String fromUserName = (String) params.get("FromUserName");
        // 获取消息类型
        String msgType = (String) params.get("MsgType");
        if (MaterialEnum.TEXT.getType().equals(msgType) || MaterialEnum.VOICE.getType().equals(msgType)) {
            // 用于储存文本或语音转换文本结果
            String content;
            if (MaterialEnum.TEXT.getType().equals(msgType)) {
                // 接收文本消息
                TextRequest message = (TextRequest) XmlUtil.mapToBean(params, TextRequest.class);
                /*textService.saveText(message);*/
                content = message.getContent();
            } else {
                // 接收语音消息
                VoiceRequest message = (VoiceRequest) XmlUtil.mapToBean(params, VoiceRequest.class);
                /*voiceService.saveVoice(message);*/
                // 语音最后会带一个中文句号
                String tmp = message.getRecognition();
                content = tmp.substring(0, tmp.length() - 1);
            }
            // 登陆请求转发到登陆控制器
            if ("登陆".equals(content)) {
                request.setAttribute("sender", fromUserName);
                request.getRequestDispatcher("/loginToken").forward(request, response);
            }
            // 转发请求给消息控制器
            request.setAttribute("message", content);
            request.setAttribute("sender", fromUserName);
            request.getRequestDispatcher("/message").forward(request, response);
        } else if (MaterialEnum.IMAGE.getType().equals(msgType)) {
            // 接收是图片消息
            ImageRequest message = (ImageRequest) XmlUtil.mapToBean(params, ImageRequest.class);
            /*imageService.saveImage(message);*/
        } else if (MaterialEnum.VIDEO.getType().equals(msgType) || MaterialEnum.SHORT_VIDEO.getType().equals(msgType)) {
            // 接收视频（短视频）消息
            VideoRequest message = (VideoRequest) XmlUtil.mapToBean(params, VideoRequest.class);
            /*videoService.saveVideo(message);*/
        } else if (MaterialEnum.EVENT.getType().equals(msgType)) {
            // 接受事件消息
            EventRequest message = (EventRequest) XmlUtil.mapToBean(params, EventRequest.class);
            request.setAttribute("event", message.getEvent());
            request.setAttribute("sender", fromUserName);
            request.getRequestDispatcher("/event").forward(request, response);
        }
    }
}
