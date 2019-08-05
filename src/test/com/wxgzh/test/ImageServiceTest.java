package com.wxgzh.test;

import com.wxgzh.domain.request.RequestImage;
import com.wxgzh.service.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ImageDaoTest class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class ImageServiceTest {

    @Autowired
    private ImageService imageMessageService;

    /**
     * 测试添加图片
     */
    @Test
    public void testSaveImage() {
        RequestImage requestImageMessage = new RequestImage();
        requestImageMessage.setMediaId("1");
        requestImageMessage.setPicUrl("2");
        requestImageMessage.setCreateTime("3");
        requestImageMessage.setFromUserName("4");
        requestImageMessage.setMsgId("5");
        requestImageMessage.setMsgType("6");
        requestImageMessage.setToUserName("7");

        imageMessageService.saveImage(requestImageMessage);
    }
}
