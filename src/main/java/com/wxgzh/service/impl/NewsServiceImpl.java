package com.wxgzh.service.impl;

import com.wxgzh.domain.material.Article;
import com.wxgzh.domain.material.Item;
import com.wxgzh.domain.response.NewsResponse;
import com.wxgzh.service.NewsServce;
import org.springframework.stereotype.Service;

/**
 * NewsServiceImpl class
 *
 * @author BowenWang
 * @date 2019/08/06
 */
@Service
public class NewsServiceImpl implements NewsServce {

    @Override
    public NewsResponse returnNews(String title, String description, String picUrl, String url) {
        Item item = new Item(title, description, picUrl, url);
        return returnNews(item);
    }

    @Override
    public NewsResponse returnNews(Item item) {
        NewsResponse responseNews = new NewsResponse();
        Article article = new Article(item);
        responseNews.setArticle(article);
        return responseNews;
    }
}
