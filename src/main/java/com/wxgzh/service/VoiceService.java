package com.wxgzh.service;

import com.wxgzh.domain.RequestVoice;

/**
 * VoiceService class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
public interface VoiceService {
    /**
     * 保存文本消息
     * @param requestVoice
     */
    void saveVoice(RequestVoice requestVoice);
}
