package com.wxdyh.domain.common;

import org.apache.log4j.Logger;

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

    private static Logger logger = Logger.getLogger(ConfigInfo.class);
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

    static {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = ConfigInfo.class.getResourceAsStream("/gzhConfig.properties");
            properties.load(in);
        } catch (IOException e) {
            logger.error("gzhConfig.properties读取失败");
        }finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("gzhConfig.properties文件流关闭出现异常");
            }
        }
        WXGZH_ID = properties.getProperty("WXGZH_ID");
        ENCODING_AES_KEY = properties.getProperty("ENCODING_AES_KEY");
        APP_ID = properties.getProperty("APP_ID");
        APP_SECRET = properties.getProperty("APP_SECRET");
        TOKEN = properties.getProperty("TOKEN");
    }


}
