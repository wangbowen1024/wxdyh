package com.wxdyh.service;

import com.wxdyh.domain.material.Item;
import com.wxdyh.domain.response.NewsResponse;

/**
 * NewsServce class
 *
 * @author BowenWang
 * @date 2019/08/06
 */
public interface NewsServce {
    /**
     * 返回图文消息
     * @param title
     * @param description
     * @param picUrl
     * @param url
     * @return
     */
    NewsResponse returnNews(String title, String description, String picUrl, String url);

    /**
     * 返回图文消息
     * @param item
     * @return
     */
    NewsResponse returnNews(Item item);
}
