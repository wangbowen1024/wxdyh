package com.wxdyh.domain.response;

import com.wxdyh.domain.material.Video;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ResponseVideo class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@XmlRootElement(name = "xml")
public class VideoResponse extends BaseResponseMessage {

    /**
     * 视频内容
     */
    @XmlElement(name = "Video")
    private Video video;

    public VideoResponse() {
        setMsgType("video");
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
