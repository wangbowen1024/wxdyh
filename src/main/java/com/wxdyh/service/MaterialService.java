package com.wxdyh.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * 素材服务
 */
public interface MaterialService {
    /**
     * 获取素材总数
     * @return
     */
    JSONObject getMaterialTotal(String accessToken) throws Exception;

    /**
     * 获取指定类型的素材集合
     *
     * @param type
     * @return
     */
    JSONArray getMaterialOfType(String accessToken, String type) throws Exception;


    JSONObject getMaterialJsonObject(String accessToken, String type, int offset, int count);
}
