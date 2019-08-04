package com.wxgzh.dao;

import com.wxgzh.domain.RequestText;
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

    @Insert("insert into textRecord(toUserName,fromUserName,createTime,msgType,msgId,content) values(#{toUserName},#{fromUserName},#{createTime},#{msgType},#{msgId},#{content})")
    void saveText(RequestText requestTextMessage);

}
