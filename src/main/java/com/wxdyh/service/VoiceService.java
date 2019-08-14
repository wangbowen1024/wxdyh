package com.wxdyh.service;

import com.wxdyh.domain.request.VoiceRequest;
import com.wxdyh.domain.response.VoiceResponse;

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
    void saveVoice(VoiceRequest requestVoice);

    /**
     * 返回语音消息
     *
     * @param mediaId
     */
    VoiceResponse returnVoice(String mediaId);
}
