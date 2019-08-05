package com.wxgzh.dao;

import com.wxgzh.domain.request.RequestText;
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
    void saveText(RequestText requestTextMessage);

}
