package com.wxdyh.domain.response;

import com.wxdyh.domain.material.Article;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ResponseNews class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@XmlRootElement(name = "xml")
public class NewsResponse extends BaseResponseMessage {

    @XmlElement(name = "ArticleCount")
    private String articleCount = "1";

    @XmlElement(name = "Articles")
    private Article article;

    public NewsResponse() {
        setMsgType("news");
    }

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
