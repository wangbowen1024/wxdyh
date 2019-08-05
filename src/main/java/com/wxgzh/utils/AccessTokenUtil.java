package com.wxgzh.utils;

import com.alibaba.fastjson.JSONObject;
import com.wxgzh.domain.ConfigInfo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * AccessTokenUtil class
 *
 * @author BowenWang
 * @date 2019/08/05
 */
public class AccessTokenUtil {

    private static final String APPID = "XXX";
    private static final String APPSECRET = "xxx";

    public static String getAccessToken() throws Exception{
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + ConfigInfo.appId + "&secret=" + ConfigInfo.appSecret;
        System.out.println("URL for getting accessToken accessTokenUrl="+accessTokenUrl);

        URL url = new URL(accessTokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();

        //获取返回的字符
        InputStream inputStream = connection.getInputStream();
        int size =inputStream.available();
        byte[] bs =new byte[size];
        inputStream.read(bs);
        String message=new String(bs, StandardCharsets.UTF_8);
        System.out.println("MSG:" + message);
        //获取access_token
        JSONObject jsonObject = JSONObject.parseObject(message);
        String accessToken = jsonObject.getString("access_token");
        String expires_in = jsonObject.getString("expires_in");
        System.out.println("accessToken="+accessToken);
        System.out.println("expires_in="+expires_in);
        return accessToken;
    }

    public static void main(String[] args){
        try {
            AccessTokenUtil.getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
