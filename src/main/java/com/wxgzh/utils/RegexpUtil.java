package com.wxgzh.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegexpUtil class
 * 正则表达式工具类
 *
 * @author BowenWang
 * @date 2019/08/09
 */
public class RegexpUtil {
    /**
     * 全匹配
     */
    private static final String FULL = "全匹配";
    /**
     * 半匹配（模糊匹配）
     */
    private static final String HALF = "半匹配";
    /**
     * 自定义正则
     */
    private static final String DEFINED = "自定义";


    /**
     * 正则匹配
     *
     * @param input 用户输入的字符串
     * @param rule  匹配规则
     * @param type  匹配类型
     * @return
     */
    public static JSONObject matching(String input, String rule, String type) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "fail");
        try {
            Matcher matcher = null;
            if (FULL.equals(type)) {
                /* 全匹配 */
                matcher = Pattern.compile(rule).matcher(input);
                if (matcher.matches()) {
                    jsonObject.put("status", "success");
                    jsonObject.put("context", matcher.group());
                }
            } else if(HALF.equals(type)) {
                /* 半匹配 */
                matcher = Pattern.compile(".*" + rule + ".*").matcher(input);
                if (matcher.matches()) {
                    jsonObject.put("status", "success");
                    jsonObject.put("context", matcher.group());
                }
            } else if (DEFINED.equals(type)) {
                /* 自定义 */
                matcher = Pattern.compile(rule).matcher(input);
            }
            if (matcher.matches()) {
                jsonObject.put("status", "success");
                jsonObject.put("context", matcher.group());
            }
        } catch (Exception e) {
            jsonObject.put("status", "fail");
        }
        return jsonObject;
    }

}
