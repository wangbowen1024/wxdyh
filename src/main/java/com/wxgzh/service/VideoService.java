package com.wxgzh.service;

import com.wxgzh.domain.request.RequestVideo;
import com.wxgzh.domain.response.ResponseVideo;
import com.wxgzh.domain.innerclass.Video;

/**
 * VideoService class
 *
 * @author BowenWang
 * @date 2019/08/05
 */
public interface VideoService {
    /**
     * 保存视频消息
     *
     * @param requestVideo
     */
    void saveVideo(RequestVideo requestVideo);

    /**
     * 返回语音消息
     *
     * @param mediaId
     * @param title
     * @param description
     * @return
     */
    ResponseVideo returnVideo(String mediaId, String title, String description);

    /**
     * 返回语音消息
     *
     * @param video
     * @return
     */
    ResponseVideo returnVideo(Video video);
}
