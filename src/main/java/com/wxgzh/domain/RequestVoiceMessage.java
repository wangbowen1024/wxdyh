package com.wxgzh.domain;

/**
 * RequestVoiceMessage class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
public class RequestVoiceMessage extends BaseRequestMessage {
    /**
     * 语音消息媒体id，可以调用获取临时素材接口拉取该媒体
     */
    private String mediaID;

    /**
     * 语音格式：amr
     */
    private String format;

    /**
     * 语音识别结果，UTF8编码
     */
    private String recognition;

    public String getMediaID() {
        return mediaID;
    }

    public void setMediaID(String mediaID) {
        this.mediaID = mediaID;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    @Override
    public String toString() {
        return "RequestVoiceMessage{" +
                "mediaID='" + mediaID + '\'' +
                ", format='" + format + '\'' +
                ", recognition='" + recognition + '\'' +
                '}';
    }
}
