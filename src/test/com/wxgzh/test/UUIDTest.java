package com.wxgzh.test;

import org.junit.Test;

import java.util.UUID;

/**
 * UUIDTest class
 *
 * @author BowenWang
 * @date 2019/08/03
 */
public class UUIDTest {
    /**
     * 生成UUID
     */
    @Test
    public void testUUID() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        String s = uuid.toString().replaceAll("-", "");
        System.out.println(s);
    }
}
