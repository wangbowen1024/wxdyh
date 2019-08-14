package com.wxdyh.enums;

import com.alibaba.fastjson.JSONObject;

/**
 * ErrorEnum class
 * 错误枚举
 *
 * @author BowenWang
 * @date 2019/08/06
 */
public enum ErrorEnum {
    /**
     * 类型错误
     */
    TYPE_ERROR(40001,"invalid media_type");

    /**
     * 错误编码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    ErrorEnum(Integer errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errcode", getErrcode());
        jsonObject.put("errmsg", getErrmsg());
        return jsonObject;
    }
}
