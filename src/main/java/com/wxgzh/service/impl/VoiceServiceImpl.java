package com.wxgzh.service.impl;

import com.wxgzh.dao.VoiceDao;
import com.wxgzh.domain.request.VoiceRequest;
import com.wxgzh.domain.response.VoiceResponse;
import com.wxgzh.domain.material.Voice;
import com.wxgzh.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * VoiceServiceImpl class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@Service
public class VoiceServiceImpl implements VoiceService {

    @Autowired
    private VoiceDao voiceDao;

    @Override
    public void saveVoice(VoiceRequest requestVoice) {
        voiceDao.saveVoice(requestVoice);
    }

    @Override
    public VoiceResponse returnVoice(String mediaId) {
        Voice voice = new Voice(mediaId);
        VoiceResponse responseVoice = new VoiceResponse();
        responseVoice.setVoice(voice);
        return responseVoice;
    }
}
