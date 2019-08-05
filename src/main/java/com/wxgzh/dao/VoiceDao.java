package com.wxgzh.dao;

import com.wxgzh.domain.request.VoiceRequest;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * VoiceDao class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@Repository
public interface VoiceDao {
    /**
     * 保存语音消息
     * @param requestVoiceMessage
     */
    @Insert("insert into voiceRecord(toUserName,fromUserName,createTime,msgType,msgId,mediaId,format,recognition) values(#{toUserName},#{fromUserName},#{createTime},#{msgType},#{msgId},#{mediaId},#{format},#{recognition})")
    void saveVoice(VoiceRequest requestVoiceMessage);
}
