package com.wxgzh.domain.miniclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Image class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Image implements Serializable {

    @XmlElement(name = "MediaId")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "mediaId='" + mediaId + '\'' +
                '}';
    }
}
