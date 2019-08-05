package com.wxgzh.test;

import com.wxgzh.domain.request.RequestVoice;
import com.wxgzh.service.VoiceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * VoiceServiceTest class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class VoiceServiceTest {
    @Autowired
    private VoiceService voiceService;

    /**
     * 测试添加语音
     */
    @Test
    public void testSaveVoice() {
        RequestVoice requestVoice = new RequestVoice();
        requestVoice.setMediaId("1");
        requestVoice.setFormat("2");
        requestVoice.setCreateTime("3");
        requestVoice.setFromUserName("4");
        requestVoice.setMsgId("5");
        requestVoice.setMsgType("6");
        requestVoice.setToUserName("7");
        requestVoice.setRecognition("8");

        voiceService.saveVoice(requestVoice);
    }
}
