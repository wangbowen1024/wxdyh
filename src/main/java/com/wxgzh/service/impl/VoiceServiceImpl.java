package com.wxgzh.service.impl;

import com.wxgzh.dao.VoiceDao;
import com.wxgzh.domain.request.RequestVoice;
import com.wxgzh.domain.response.ResponseVoice;
import com.wxgzh.domain.innerclass.Voice;
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
    public void saveVoice(RequestVoice requestVoice) {
        voiceDao.saveVoice(requestVoice);
    }

    @Override
    public ResponseVoice returnVoice(String mediaId) {
        Voice voice = new Voice(mediaId);
        ResponseVoice responseVoice = new ResponseVoice();
        responseVoice.setVoice(voice);
        return responseVoice;
    }
}
