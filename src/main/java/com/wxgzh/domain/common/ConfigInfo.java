package com.wxgzh.domain.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Constant class
 * 配置信息类
 *
 * @author BowenWang
 * @date 2019/08/03
 */
public class ConfigInfo {
    /**
     * 微信公众号原始ID
     */
    public static final String WXGZH_ID;

    /**
     * 消息加解密密钥
     */
    public static final String ENCODING_AES_KEY;

    /**
     * AppID
     */
    public static final String APP_ID;

    /**
     * AppSecret
     */
    public static final String APP_SECRET;

    /**
     * Token
     */
    public static final String TOKEN;

    /**
     * 管理员列表
     */
    public static Set<String> ADMIN_SET = new HashSet<>();

    /**
     * 信息是否被更新了
     */
    public static boolean IS_UPDATE = false;

    static {
        Properties properties = new Properties();
        InputStream inputStream = ConfigInfo.class.getResourceAsStream("gzhConfig.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("【ERROR】gzhConfig.properties读取失败");
        }
        WXGZH_ID = properties.getProperty("WXGZH_ID");
        ENCODING_AES_KEY = properties.getProperty("ENCODING_AES_KEY");
        APP_ID = properties.getProperty("APP_ID");
        APP_SECRET = properties.getProperty("APP_SECRET");
        TOKEN = properties.getProperty("TOKEN");
    }


}
