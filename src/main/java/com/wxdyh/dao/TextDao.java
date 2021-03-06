package com.wxdyh.dao;

import com.wxdyh.domain.request.TextRequest;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * TextDao class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@Repository
public interface TextDao {

    /**
     * 保存文本
     * @param requestTextMessage
     */
    @Insert("insert into textRecord(toUserName,fromUserName,createTime,msgType,msgId,content) values(#{toUserName},#{fromUserName},#{createTime},#{msgType},#{msgId},#{content})")
    void saveText(TextRequest requestTextMessage);

}
