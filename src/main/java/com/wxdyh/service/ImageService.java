package com.wxdyh.service;

import com.wxdyh.domain.request.ImageRequest;
import com.wxdyh.domain.response.ImageResponse;

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
