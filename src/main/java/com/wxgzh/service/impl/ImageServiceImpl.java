package com.wxgzh.service.impl;

import com.wxgzh.dao.ImageDao;
import com.wxgzh.domain.RequestImage;
import com.wxgzh.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ImageMessageServiceImpl class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public void saveImage(RequestImage requestImageMessage) {
        imageDao.saveImage(requestImageMessage);
    }
}
