package com.wxdyh.service.impl;

import com.wxdyh.dao.VideoDao;
import com.wxdyh.domain.request.VideoRequest;
import com.wxdyh.domain.response.VideoResponse;
import com.wxdyh.domain.material.Video;
import com.wxdyh.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * VideoServiceImpl class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    @Override
    public void saveVideo(VideoRequest requestVideo) {
        videoDao.saveVideo(requestVideo);
    }

    @Override
    public VideoResponse returnVideo(String mediaId, String title, String description) {
        return returnVideo(new Video(mediaId, title, description));
    }

    @Override
    public VideoResponse returnVideo(Video video) {
        VideoResponse responseVideo = new VideoResponse();
        responseVideo.setVideo(video);
        return responseVideo;
    }
}
