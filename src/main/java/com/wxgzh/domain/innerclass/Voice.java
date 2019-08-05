package com.wxgzh.domain.innerclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Voice class
 *
 * @author BowenWang
 * @date 2019/08/05
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Voice implements Serializable {

    @XmlElement(name = "MediaId")
    private String mediaId;

    public Voice(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
