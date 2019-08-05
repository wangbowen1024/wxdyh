package com.wxgzh.domain.request;

import java.io.Serializable;

/**
 * BaseRequestMessage class
 * 消息基类用于存放公共属性
 *
 * @author BowenWang
 * @date 2019/08/04
 */
public class BaseRequestMessage implements Serializable {
    /**
     * 开发者微信号
     */
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    private String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    private String createTime;

    /**
     * 消息类型，文本为text
     */
    private String msgType;

    /**
     * 	消息id，64位整型
     */
    private String msgId;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

}
