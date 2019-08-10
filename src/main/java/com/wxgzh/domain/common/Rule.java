package com.wxgzh.domain.common;

import java.io.Serializable;

/**
 * Rule class
 * 自动回复规则类
 *
 * @author BowenWang
 * @date 2019/08/09
 */
public class Rule implements Serializable {
    /**
     * ID
     */
    private Integer id;
    /**
     * 规则名称
     */
    private String name;
    /**
     * 规则匹配类型
     */
    private String type;
    /**
     * 规则匹配内容
     */
    private String content;
    /**
     * 回复类型
     */
    private String replayType;
    /**
     * 回复内容
     */
    private String replayContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplayType() {
        return replayType;
    }

    public void setReplayType(String replayType) {
        this.replayType = replayType;
    }

    public String getReplayContent() {
        return replayContent;
    }

    public void setReplayContent(String replayContent) {
        this.replayContent = replayContent;
    }


    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", replayType='" + replayType + '\'' +
                ", replayContent='" + replayContent + '\'' +
                '}';
    }
}
