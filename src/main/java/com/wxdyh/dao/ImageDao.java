package com.wxdyh.dao;

import com.wxdyh.domain.request.ImageRequest;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * ImageDao class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@Repository
public interface ImageDao {
    /**
     * 保存图片
     * @param imageMessage
     */
    @Insert("insert into imageRecord(toUserName,fromUserName,createTime,msgType,msgId,picUrl,mediaId) values(#{toUserName},#{fromUserName},#{createTime},#{msgType},#{msgId},#{picUrl},#{mediaId})")
    void saveImage(ImageRequest imageMessage);
}
