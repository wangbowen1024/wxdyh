package com.wxgzh.controller;

import com.wxgzh.domain.BaseResponseMessage;
import com.wxgzh.domain.RequestTextMessage;
import com.wxgzh.domain.ResponseTextMessage;
import com.wxgzh.enums.ConfigInfo;
import com.wxgzh.enums.Message;
import com.wxgzh.service.TextMessageService;
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
    private TextMessageService textMessageService;

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
        Object result = null;
        Message messageType = null;
        // 获取消息类型
        String msgType = (String) params.get("MsgType");
        // 如果是文本消息
        if (Message.TEXT.getMsgType().equalsIgnoreCase(msgType)) {
            messageType = Message.TEXT;
            RequestTextMessage message = (RequestTextMessage) XmlUtil.mapToBean(params, RequestTextMessage.class);
            // 获取请求内容
            String content = message.getContent();
            // 解析内容匹配业务条件
            if (content.length() > 0) {
                result = textMessageService.getRobotReply(content);
            }
        }
        // 返回消息
        return result == null ? null : responseParse(result, messageType, (String) params.get("FromUserName"));
    }

    /**
     * 处理返回结果
     * @param obj
     * @param msgType
     * @return
     */
    private Object responseParse(Object obj, Message msgType, String toUserName) {
        // 配置基本回复属性
        BaseResponseMessage base = new BaseResponseMessage();
        base.setToUserName(toUserName);
        base.setFromUserName(ConfigInfo.WXGZH_ID.getValue());
        base.setCreateTime(String.valueOf(System.currentTimeMillis()));
        base.setMsgType(msgType.getMsgType());

        // 如果返回文本
        if (Message.TEXT.equals(msgType)) {
            ResponseTextMessage response = new ResponseTextMessage(base);
            response.setContent((String) obj);
            return response;
        }

        return null;
    }

}
