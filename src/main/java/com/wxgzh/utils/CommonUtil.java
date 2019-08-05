package com.wxgzh.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.wxgzh.domain.common.ConfigInfo;
import com.wxgzh.domain.innerclass.Material;
import com.wxgzh.domain.innerclass.MaterialImage;
import com.wxgzh.domain.innerclass.MaterialParam;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * CommonUtil class
 * 通用接口工具类
 *
 * @author BowenWang
 * @date 2019/08/05
 */
public class CommonUtil {
    /**
     * accessToken
     */
    public static String accessToken = "24_GjeLFPR9jip25DJ0LwHCU8A74k-S_MaPPDy3uTxFRWXZtEiLuk4zEOBVJmMHnCq1d42WCsv9DzsbEUlHud2ct1oLNvu2MC1utGBFm8NiOk-B71P6uy_3aLR3rAXcSegCNjU39sayshzICPOEVLEiAHACSL";

    /**
     * 获取Token的时间,毫秒
     */
    public static Long outTime = 0L;

    /**
     * 获取access_token的URL
     */
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret="
            + ConfigInfo.appSecret;
    /**
     * 获取素材列表URL
     */
    public final static String MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";

    private static Logger logger = Logger.getLogger(CommonUtil.class);

    /**
     * 获取access_token，只能在系统白名单内的IP发起的才能成功
     * @return
     * @throws Exception
     */
    public static String getAccessToken() throws Exception{
        // token无效才重新获取
        Long nowTime = System.currentTimeMillis();
        if (accessToken == null || nowTime.compareTo(outTime) > 0) {
            URL url = new URL(token_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();

            // 获取返回的字符
            InputStream inputStream = connection.getInputStream();
            // 设置到期时间
            outTime = System.currentTimeMillis() + 7200 * 1000;
            int size =inputStream.available();
            byte[] bs =new byte[size];
            inputStream.read(bs);
            String message=new String(bs, StandardCharsets.UTF_8);
            // 获取access_token
            JSONObject jsonObject = JSONObject.parseObject(message);
            accessToken = jsonObject.getString("access_token");
            System.out.println(">> NEW_TOKEN");
        }
        return accessToken;
    }

    /**
     * 获取素材列表并存入集合中
     * @param accessToken 获取接口凭证的唯一标识
     * @param type 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count 返回素材的数量，取值在1到20之间
     * @return
     */
    public static JSONObject getMaterialJsonObject(String accessToken, String type, int offset, int count) {
        // 定义一个空的参数字符串
        String outputStr = "";
        // 替换调access_token
        String requestUrl = MATERIAL.replace("ACCESS_TOKEN", accessToken);
        // 调用接口所需要的参数实体类
        MaterialParam para = new MaterialParam();
        para.setType(type);
        para.setOffset(offset);
        para.setCount(count);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(para);
        // 将参数对象转换成json字符串
        outputStr = jsonObject.toString();
        // 发送https请求(请求的路径,方式,所携带的参数)
        jsonObject = HttpUtil.doHttps(requestUrl, "POST", outputStr);
        return jsonObject;
    }

    public static List<Material> getMaterial(String accessToken, String type, int offset, int count) {
        // 定义图文素材实体类集合
        List<Material> lists = new ArrayList<>();
        JSONObject jsonObject = getMaterialJsonObject(accessToken, type, offset, count);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("item");
                for (Object aJsonArray : jsonArray) {
                    JSONObject json = (JSONObject) aJsonArray;
                    json = json.getJSONObject("content");
                    JSONArray arr = json.getJSONArray("news_item");
                    json = (JSONObject) arr.get(0);

                    Material material = new Material();
                    String title = json.getString("title");
                    String author = json.getString("author");
                    String digest = json.getString("digest");
                    String thumb_media_id = json.getString("thumb_media_id");
                    String url = json.getString("url");
                    String content = json.getString("content");
                    material.setTitle(title);
                    material.setAuthor(author);
                    material.setDigest(digest);
                    material.setThumb_media_id(thumb_media_id);
                    material.setUrl(url);
                    material.setContent(content);
                    material.setShow_cover_pic(1);
                    lists.add(material);
                }
            } catch (JSONException e) {
                accessToken = null;
                // 获取Material失败
                logger.error("获取Material失败 errcode:{" + jsonObject.getInteger("errcode")
                        + "} errmsg:{" + jsonObject.getString("errmsg") + "}", e);
            }
        }
        return lists;
    }

    public static List<MaterialImage> getMaterialOfImage(String accessToken, String type, int offset, int count) {
        // 定义图文素材实体类集合
        List<MaterialImage> lists = new ArrayList<>();
        JSONObject jsonObject = getMaterialJsonObject(accessToken, type, offset, count);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("item");
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject json = (JSONObject) jsonArray.get(i);
                    MaterialImage image = new MaterialImage();
                    image.setMedia_id(json.getString("media_id"));
                    image.setName(json.getString("name"));
                    try {
                        // 旧的image 没有url 需处理异常 新添加的有url
                        image.setUrl(json.getString("url"));
                    } catch (Exception e) {
                        System.out.println("url 不存在异常");
                    }
                    lists.add(image);
                }
            } catch (JSONException e) {
                accessToken = null;
                // 获取Material失败
                logger.error("获取Material失败 errcode:{" + jsonObject.getInteger("errcode")
                        + "} errmsg:{" + jsonObject.getString("errmsg") + "}", e);
            }
        }
        return lists;
    }
}
