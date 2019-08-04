package com.wxgzh.enums;

/**
 * Message class
 *
 * @author BowenWang
 * @date 2019/08/03
 */
public enum Message {
    /**
     * 文本消息类型
     */
    TEXT("text", 0),
    VOICE("voice", 1),
    IMAGE("image", 2);

    private String msgType;
    private Integer id;


    Message(String msgType, Integer id) {
        this.msgType = msgType;
        this.id = id;
    }

    public String getMsgType() {
        return msgType;
    }

    public Integer getId() {
        return id;
    }

}
