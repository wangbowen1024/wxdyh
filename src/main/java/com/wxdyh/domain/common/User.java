package com.wxdyh.domain.common;

import java.io.Serializable;

/**
 * User class
 *
 * @author BowenWang
 * @date 2019/08/11
 */
public class User implements Serializable {
    /**
     * 数据库自增长ID
     */
    private Integer id;
    /**
     * 公众号唯一ID
     */
    private String openId;
    /**
     * 状态：1关注，0取消关注，-1拉黑
     */
    private Integer status;
    /**
     * 权限
     */
    private String authority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
