package com.wxgzh.service.impl;

import com.wxgzh.dao.VideoDao;
import com.wxgzh.domain.request.RequestVideo;
import com.wxgzh.domain.response.ResponseVideo;
import com.wxgzh.domain.innerclass.Video;
import com.wxgzh.service.VideoService;
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
    public void saveVideo(RequestVideo requestVideo) {
        videoDao.saveVideo(requestVideo);
    }

    @Override
    public ResponseVideo returnVideo(String mediaId, String title, String description) {
        return returnVideo(new Video(mediaId, title, description));
    }

    @Override
    public ResponseVideo returnVideo(Video video) {
        ResponseVideo responseVideo = new ResponseVideo();
        responseVideo.setVideo(video);
        return responseVideo;
    }
}
