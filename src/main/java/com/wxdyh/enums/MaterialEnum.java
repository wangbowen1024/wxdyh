package com.wxdyh.enums;

/**
 * MaterialEnum enum
 * 消息类型
 *
 * @author BowenWang
 * @date 2019/08/03
 */
public enum MaterialEnum {
    /**
     * 文本
     */
    TEXT("text", 0),
    /**
     * 语音
     */
    VOICE("voice", 1),
    /**
     * 图片
     */
    IMAGE("image", 2),
    /**
     * 视频
     */
    VIDEO("video", 3),
    /**
     * 短视频
     */
    SHORT_VIDEO("shortvideo", 4),
    /**
     * 图文
     */
    NEWS("news", 5),
    /**
     * 事件
     */
    EVENT("event", 6);

    private String materialType;
    private Integer id;


    MaterialEnum(String materialType, Integer id) {
        this.materialType = materialType;
        this.id = id;
    }

    public String getType() {
        return materialType;
    }

    public Integer getId() {
        return id;
    }

}
