package com.wxgzh.service;

import com.wxgzh.domain.RequestText;
import com.wxgzh.domain.ResponseText;

/**
 * TextMessageService class
 *
 * @author BowenWang
 * @date 2019/08/03
 */

public interface TextService {

    /**
     * 获取机器人回复
     * @param question
     * @return
     */
    ResponseText getRobotReply(String question);

    /**
     * 保存文本消息
     * @param requestTextMessage
     */
    void saveText(RequestText requestTextMessage);

    /**
     * 返回文本消息
     * @param content
     * @return
     */
    ResponseText returnText(String content);
}
