package com.wxgzh.utils;

import com.alibaba.fastjson.JSONObject;
import com.wxgzh.domain.common.ConfigInfo;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * CommonUtil class
 * 通用接口工具类
 *
 * @author BowenWang
 * @date 2019/08/05
 */
public class TokenUtil {
    /**
     * access_Token
     */
    private static String accessToken = "24_GQWdx2EmmGiCsQEzeb_6PSbRxe3F5bkBQwxRkC447H2sTzzRHbHQ0MCa2WW6HkEc9pFhryxpCzl_mXCsoD7Cg3MVO9AMZE5TwU0UiFUSHOi6jBB5-ukQMQcba1o3r2hiDWSMEmFTbGONYp40FYFiAGALEL";

    /**
     * 获取Token的时间,毫秒
     */
    private static Long outTime = System.currentTimeMillis() + 100 * 60 * 1000;

    /**
     * 获取access_token的URL
     */
    private final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取access_token，只能在系统白名单内的IP发起的才能成功
     * @return
     * @throws Exception
     */
    public static String getAccessToken() throws Exception{
        // token无效才重新获取
        Long nowTime = System.currentTimeMillis();
        if (accessToken == null || nowTime.compareTo(outTime) > 0) {
            String tokenUrl = TOKEN_URL.replace("APPSECRET", ConfigInfo.APP_SECRET)
                    .replace("APPID", ConfigInfo.APP_ID);
            URL url = new URL(tokenUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();

            // 获取返回的字符
            InputStream inputStream = connection.getInputStream();
            // 设置到期时间:提前5分钟
            outTime = System.currentTimeMillis() + 6900 * 1000;
            int size =inputStream.available();
            byte[] bs =new byte[size];
            inputStream.read(bs);
            String message=new String(bs, StandardCharsets.UTF_8);
            // 获取access_token
            JSONObject jsonObject = JSONObject.parseObject(message);
            accessToken = jsonObject.getString("access_token");
        }
        return accessToken;
    }
}
