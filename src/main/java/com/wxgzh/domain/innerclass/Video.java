package com.wxgzh.domain.innerclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Video class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Video {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    @XmlElement(name = "MediaId")
    private String mediaId;

    /**
     * 视频消息的标题
     */
    @XmlElement(name = "Title")
    private String title;

    /**
     * 视频消息的描述
     */
    @XmlElement(name = "Description")
    private String description;

    public Video(String mediaId, String title, String description) {
        this.mediaId = mediaId;
        this.title = title;
        this.description = description;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
