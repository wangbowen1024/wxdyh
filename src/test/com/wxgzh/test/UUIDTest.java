package com.wxgzh.test;

import com.wxgzh.utils.UuidUtil;
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
        System.out.println(UuidUtil.getUUID());
    }
}
