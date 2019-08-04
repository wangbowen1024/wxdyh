package com.wxgzh.domain;

import java.util.HashSet;
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
    public static String wxgzhId = "gh_453696665e91";

    /**
     * 令牌(Token)
     */
    public static String token = "23f2401862304246b963f92117515f7a";

    /**
     * 服务器地址(URL)
     */
    public static String url = "http://wangbowen.cn/WX_GZH/CodeWen/api";

    /**
     * 消息加解密密钥
     */
    public static String encodingAESKey = "II7cHczIfng2vktH4U0oNShHOIwlx4UKFzUCgkmOcgR";

    /**
     * AppID
     */
    public static String appId = "wxf1b19cbb144bdc64";

    /**
     * AppSecret
     */
    public static String appSecret = "93c6790b133f050bd3a26c81664bf7d8";

    /**
     * 管理员列表
     */
    public static Set<String> adminSet;

    /**
     * 信息是否被更新了
     */
    public static boolean isUpdate = false;

    static {
        adminSet = new HashSet<>();
        adminSet.add("ozQB9uIfVUXhbcz5GyoOiDsgo0aI");
    }
}
