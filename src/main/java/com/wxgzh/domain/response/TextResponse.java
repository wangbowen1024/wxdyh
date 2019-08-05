package com.wxgzh.domain.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ResponseText class
 * 响应文本消息
 *
 * @author BowenWang
 * @date 2019/08/03
 */
@XmlRootElement(name = "xml")
public class TextResponse extends BaseResponseMessage {

    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     */
    @XmlElement(name = "Content")
    private String content;

    public TextResponse() {
        setMsgType("text");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}