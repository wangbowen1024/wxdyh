package com.wxgzh.service;

import com.wxgzh.domain.request.ImageRequest;
import com.wxgzh.domain.response.ImageResponse;

/**
 * ImageMessageService class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
public interface ImageService {
    /**
     * 保存图片
     *
     * @param requestImageMessage
     */
    void saveImage(ImageRequest requestImageMessage);

    /**
     * 返回图片消息
     *
     * @return
     */
    ImageResponse returnImage(String mediaId);
}
