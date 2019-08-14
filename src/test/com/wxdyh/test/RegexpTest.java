package com.wxdyh.test;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegexpTest class
 *
 * @author BowenWang
 * @date 2019/08/09
 */
public class RegexpTest {
    @Test
    public void testRegexp() {
        Matcher matcher = Pattern.compile("\\d+").matcher("5648");
        if (matcher.matches()) {
            System.out.println(">>" + matcher.group());
        } else {
            System.out.println(">> NO");
        }
    }
}
