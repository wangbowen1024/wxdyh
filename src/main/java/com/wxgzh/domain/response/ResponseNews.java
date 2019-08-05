package com.wxgzh.domain.response;

import com.wxgzh.domain.innerclass.Article;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ResponseNews class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@XmlRootElement(name = "xml")
public class ResponseNews extends BaseResponseMessage {

    @XmlElement(name = "ArticleCount")
    private String articleCount;

    @XmlElement(name = "Articles")
    private Article article;
}
