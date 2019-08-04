package com.wxgzh.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * ResponseTextMessage class
 * 响应文本消息
 *
 * @author BowenWang
 * @date 2019/08/03
 */
@XmlRootElement(name = "xml")
public class ResponseText extends BaseResponseMessage {

    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     */
    @XmlElement(name = "Content")
    private String content;

    public ResponseText() {
        setMsgType("text");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
