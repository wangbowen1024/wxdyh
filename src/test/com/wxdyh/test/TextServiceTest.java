package com.wxdyh.test;

import com.wxdyh.domain.request.TextRequest;
import com.wxdyh.service.TextService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TextServiceTest class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class TextServiceTest {
    @Autowired
    private TextService textMessageService;

    /**
     * 测试添加文本
     */
    @Test
    public void testSaveText() {
        TextRequest requestTextMessage = new TextRequest();
        requestTextMessage.setCreateTime("3");
        requestTextMessage.setFromUserName("4");
        requestTextMessage.setMsgId("5");
        requestTextMessage.setMsgType("6");
        requestTextMessage.setToUserName("7");
        requestTextMessage.setContent("8");

        textMessageService.saveText(requestTextMessage);
    }
}
