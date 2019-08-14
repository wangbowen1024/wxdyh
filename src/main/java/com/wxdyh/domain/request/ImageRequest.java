package com.wxdyh.domain.request;

/**
 * RequestImage class
 * 接受图片消息
 *
 * @author BowenWang
 * @date 2019/08/04
 */
public class ImageRequest extends BaseRequestMessage {

    /**
     * 图片链接（由系统生成）
     */
    private String picUrl;

    /**
     * 图片消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String mediaId;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
