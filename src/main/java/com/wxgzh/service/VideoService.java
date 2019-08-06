package com.wxgzh.service;

import com.wxgzh.domain.request.VideoRequest;
import com.wxgzh.domain.response.VideoResponse;
import com.wxgzh.domain.material.Video;

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
    void saveVideo(VideoRequest requestVideo);

    /**
     * 返回语音消息
     *
     * @param mediaId
     * @param title
     * @param description
     * @return
     */
    VideoResponse returnVideo(String mediaId, String title, String description);

    /**
     * 返回语音消息
     *
     * @param video
     * @return
     */
    VideoResponse returnVideo(Video video);
}
