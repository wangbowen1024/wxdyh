package com.wxgzh.dao;

import com.wxgzh.domain.request.VideoRequest;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * ImageDao class
 *
 * @author BowenWang
 * @date 2019/08/05
 */
@Repository
public interface VideoDao {

    /**
     * 保存视频消息
     * @param videoMessage
     */
    @Insert("insert into videoRecord(toUserName,fromUserName,createTime,msgType,msgId,mediaId,thumbMediaId) values(#{toUserName},#{fromUserName},#{createTime},#{msgType},#{msgId},#{mediaId},#{thumbMediaId})")
    void saveVideo(VideoRequest videoMessage);
}
