package com.wxgzh.domain;

import com.wxgzh.domain.innerclass.Image;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ResponseImage class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@XmlRootElement(name = "xml")
public class ResponseImage extends BaseResponseMessage {
    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     */
    @XmlElement(name = "Image")
    private Image image;

    public ResponseImage() {
        setMsgType("image");
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}


