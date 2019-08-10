package com.wxgzh.dao;

import com.wxgzh.domain.common.Rule;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RuleDao class
 *
 * @author BowenWang
 * @date 2019/08/09
 */
@Repository
public interface RuleDao {

    /**
     * 添加规则
     *
     * Options注解直接将最新的ID，通过反射直接放到参数对象rule中
     *
     * @param rule
     * @return
     */
    @Insert("insert into rule(name,type,content,replayType,replayContent)values(#{name},#{type},#{content},#{replayType},#{replayContent})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int saveRule(Rule rule);

    /**
     * 删除规则
     * @param id
     * @return
     */
    @Delete("delete from rule where id = #{id}")
    int deleteRule(Integer id);

    /**
     * 查找所有规则
     * @return
     */
    @Select("select * from rule")
    List<Rule> findAllRules();

    /**
     * 根据ID查询规则
     * @param id
     * @return
     */
    @Select("select * from rule where id = #{id}")
    Rule findById(Integer id);
}
