package com.wxgzh.service;

import com.wxgzh.domain.ResponseTextMessage;

/**
 * TextMessageService class
 *
 * @author BowenWang
 * @date 2019/08/03
 */

public interface TextMessageService {

    /**
     * 获取机器人回复
     * @param question
     * @return
     */
    ResponseTextMessage getRobotReply(String question);
}
