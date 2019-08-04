package com.wxgzh.service.impl;

import com.wxgzh.dao.VoiceDao;
import com.wxgzh.domain.RequestVoice;
import com.wxgzh.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * VoiceSeriveImpl class
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
}
