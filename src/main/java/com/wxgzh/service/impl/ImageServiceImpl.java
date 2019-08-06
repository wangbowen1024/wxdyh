package com.wxgzh.service.impl;

import com.wxgzh.dao.ImageDao;
import com.wxgzh.domain.request.ImageRequest;
import com.wxgzh.domain.response.ImageResponse;
import com.wxgzh.domain.material.Image;
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
    public void saveImage(ImageRequest requestImageMessage) {
        imageDao.saveImage(requestImageMessage);
    }

    @Override
    public ImageResponse returnImage(String mediaId) {
        ImageResponse responseImage = new ImageResponse();
        responseImage.setImage(new Image(mediaId));
        return responseImage;
    }
}
