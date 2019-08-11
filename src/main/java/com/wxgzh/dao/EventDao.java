package com.wxgzh.dao;

import com.wxgzh.domain.common.User;
import com.wxgzh.domain.request.EventRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * EventDao class
 *
 * @author BowenWang
 * @date 2019/08/11
 */
@Repository
public interface EventDao {
    /**
     * 新用户订阅，添加用户
     * @param user
     */
    @Insert("insert into user (openId,authority) values(#{openId},#{authority})")
    void saveUser(User user);

    /**
     * 更新用户状态信息
     * @param user
     */
    @Update("update user set status=#{status} where openId = #{openId}")
    void updateStatus(User user);

    /**
     * 更改用户权限
     * @param openId
     * @param authority
     */
    @Update("update user set authority=#{authority} where openId = #{openId}｝")
    void updateAuthority(String openId, String authority);

    /**
     * 根据用户ID查询
     * @param openId
     * @return
     */
    @Select("select * from user where openId = #{openId}")
    User findUserById(String openId);

    /**
     * 查询自动回复关注者消息内容
     * @return
     */
    @Select("select content from subscribeMessage where id = 1")
    String findSubscribeMessage();

    /**
     * 更新自动回复关注者消息内容
     * @param content
     * @return
     */
    @Update("update subscribeMessage set content = #{content} where id = 1")
    int updateSubscribeMessage(String content);

    @Select("select count(*) from user")
    int totalUser();
}
