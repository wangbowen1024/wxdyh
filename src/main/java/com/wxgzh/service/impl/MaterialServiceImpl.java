package com.wxgzh.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.wxgzh.domain.material.News;
import com.wxgzh.domain.material.Multimedia;
import com.wxgzh.domain.material.MaterialParam;
import com.wxgzh.enums.MaterialEnum;
import com.wxgzh.service.MaterialService;
import com.wxgzh.utils.HttpUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * MaterialServiceImpl class
 *
 * @author BowenWang
 * @date 2019/08/06
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    /**
     * 获取素材总数URL
     */
    private static final String MATERIAL_TOTAL_URL = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";
    /**
     * 获取素材列表URL
     */
    private static final String MATERIAL_LIST_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";

    private static Logger logger = Logger.getLogger(MaterialServiceImpl.class);


    @Override
    public JSONObject getMaterialTotal(String accessToken) throws Exception {
        String requestUrl = MATERIAL_TOTAL_URL.replace("ACCESS_TOKEN", accessToken);
        return HttpUtil.doHttps(requestUrl, "POST", null);
    }

    @Override
    public JSONArray getMaterialOfType(String accessToken, String type) throws Exception {
        JSONArray jsonArray = new JSONArray();
        JSONObject materialTotal = getMaterialTotal(accessToken);
        if (MaterialEnum.NEWS.getType().equals(type)) {
            Integer newsCount = materialTotal.getInteger("news_count");
            int count = newsCount % 20 == 0 ? newsCount / 20 : newsCount / 20 + 1;
            for (int i = 0; i < count; i++) {
                List<News> newsList = getMaterialOfNews(accessToken, i, 20);
                jsonArray.addAll(newsList);
            }
        } else {
            Integer materialCount = materialTotal.getInteger(type + "_count");
            int count = materialCount % 20 == 0 ? materialCount / 20 : materialCount / 20 + 1;
            for (int i = 0; i < count; i++) {
                List<Multimedia> multimediaList = getMaterialOfMultimedia(accessToken, type, i, 20);
                jsonArray.addAll(multimediaList);
            }
        }
        return jsonArray;
    }

    /**
     * 获取素材列表Json对象
     * @param type 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count 返回素材的数量，取值在1到20之间
     * @return
     */
    private JSONObject getMaterialJsonObject(String accessToken,String type, int offset, int count) throws Exception {
        // 替换调access_token
        String requestUrl = MATERIAL_LIST_URL.replace("ACCESS_TOKEN", accessToken);
        // 调用接口所需要的参数实体类
        MaterialParam para = new MaterialParam(type, offset, count);
        // 将参数对象转换成json字符串
        String outputStr = JSON.toJSONString(para);
        // 发送https请求(请求的路径,方式,所携带的参数)
        return HttpUtil.doHttps(requestUrl, "POST", outputStr);
    }

    /**
     * 返回图文素材集合
     * @param accessToken
     * @param offset
     * @param count
     * @return
     * @throws Exception
     */
    private List<News> getMaterialOfNews(String accessToken, int offset, int count) throws Exception {
        // 定义图文素材实体类集合
        List<News> lists = new ArrayList<>();
        JSONObject jsonObject = getMaterialJsonObject(accessToken, "news", offset, count);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("item");
                for (Object aJsonArray : jsonArray) {
                    JSONObject json = (JSONObject) aJsonArray;
                    json = json.getJSONObject("content");
                    JSONArray arr = json.getJSONArray("news_item");
                    json = (JSONObject) arr.get(0);

                    News material = new News();
                    material.setTitle(json.getString("title"));
                    material.setAuthor(json.getString("author"));
                    material.setDigest(json.getString("digest"));
                    material.setThumb_media_id(json.getString("thumb_media_id"));
                    material.setUrl(json.getString("url"));
                    material.setContent(json.getString("content"));
                    material.setShow_cover_pic(1);
                    lists.add(material);
                }
            } catch (JSONException e) {
                logger.error("获取Material失败 " + jsonObject.toJSONString(), e);
            }
        }
        return lists;
    }

    private List<Multimedia> getMaterialOfMultimedia(String accessToken, String type, int offset, int count) throws Exception {
        // 定义图文素材实体类集合
        List<Multimedia> lists = new ArrayList<>();
        JSONObject jsonObject = getMaterialJsonObject(accessToken, type, offset, count);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("item");
                for (Object aJsonArray : jsonArray) {
                    JSONObject json = (JSONObject) aJsonArray;
                    Multimedia image = new Multimedia();
                    image.setMedia_id(json.getString("media_id"));
                    image.setName(json.getString("name"));
                    image.setUrl(json.getString("url"));
                    lists.add(image);
                }
            } catch (JSONException e) {
                logger.error("获取Material失败 " + jsonObject.toJSONString(), e);
            }
        }
        return lists;
    }
}
