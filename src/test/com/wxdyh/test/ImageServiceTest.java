package com.wxdyh.test;

import com.wxdyh.domain.request.ImageRequest;
import com.wxdyh.service.ImageService;
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
        ImageRequest requestImageMessage = new ImageRequest();
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
