package com.wxgzh.utils;

import java.util.UUID;

/**
 * UuidUtil class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
public class UuidUtil {
    /**
     * 获取UUID
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
