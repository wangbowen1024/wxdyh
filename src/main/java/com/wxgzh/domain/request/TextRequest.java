package com.wxgzh.domain.request;

/**
 * RequestText class
 * 请求文本消息
 *
 * @author BowenWang
 * @date 2019/08/03
 */

public class TextRequest extends BaseRequestMessage {

    /**
     * 文本消息内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
