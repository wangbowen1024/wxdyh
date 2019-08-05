package com.wxgzh.domain;

import com.wxgzh.domain.response.BaseResponseMessage;

import java.io.Serializable;

/**
 * AutoReply class
 * 自动回复消息
 *
 * @author BowenWang
 * @date 2019/08/04
 */
public class AutoReply implements Serializable {

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 关键词
     */
    private String keyWord;

    /**
     * 是否全匹配
     */
    private boolean isWholeMatch;

    /**
     * 回复内容类型
     */
    private String replyType;

    /**
     * 回复内容
     */
    private BaseResponseMessage replyMessage;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public boolean isWholeMatch() {
        return isWholeMatch;
    }

    public void setWholeMatch(boolean wholeMatch) {
        isWholeMatch = wholeMatch;
    }

    public String getReplyType() {
        return replyType;
    }

    public void setReplyType(String replyType) {
        this.replyType = replyType;
    }

    public BaseResponseMessage getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(BaseResponseMessage replyMessage) {
        this.replyMessage = replyMessage;
    }
}
