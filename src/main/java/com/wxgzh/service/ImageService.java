package com.wxgzh.service;

import com.wxgzh.domain.request.RequestImage;
import com.wxgzh.domain.response.ResponseImage;

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
    void saveImage(RequestImage requestImageMessage);

    /**
     * 返回图片消息
     *
     * @return
     */
    ResponseImage returnImage(String mediaId);
}
