package com.wxgzh.service;

import com.wxgzh.domain.request.RequestVoice;
import com.wxgzh.domain.response.ResponseVoice;

/**
 * VoiceService class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
public interface VoiceService {
    /**
     * 保存语音消息
     *
     * @param requestVoice
     */
    void saveVoice(RequestVoice requestVoice);

    /**
     * 返回语音消息
     *
     * @param mediaId
     */
    ResponseVoice returnVoice(String mediaId);
}
