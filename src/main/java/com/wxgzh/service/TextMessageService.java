package com.wxgzh.service;

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
    String getRobotReply(String question);
}
