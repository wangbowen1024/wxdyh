package com.wxgzh.domain.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Random;

/**
 * Timer class
 * 定时器类
 *
 * @author BowenWang
 * @date 2019/08/12
 */
@Component
public class Timer {
    /**
     * 登陆令牌
     */
    public static String loginToken;
    public static final String TOKEN_STRING = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";
    public static final Random RANDOM = new Random();

    /**
     * 每60秒刷新一次令牌
     */
    @Scheduled(fixedDelay = 1000 * 60)
    public void refreshLoginToken() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(TOKEN_STRING.charAt(RANDOM.nextInt(TOKEN_STRING.length())));
        }
        loginToken = sb.toString();
    }
}
