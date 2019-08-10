package com.wxgzh.service;

import com.wxgzh.domain.request.TextRequest;
import com.wxgzh.domain.response.TextResponse;

/**
 * TextMessageService class
 *
 * @author BowenWang
 * @date 2019/08/03
 */

public interface TextService {

    /**
     * 保存文本消息
     *
     * @param requestTextMessage
     */
    void saveText(TextRequest requestTextMessage);

    /**
     * 返回文本消息
     *
     * @param content
     * @return
     */
    TextResponse returnText(String content);
}
