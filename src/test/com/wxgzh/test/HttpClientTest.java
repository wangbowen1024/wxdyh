package com.wxgzh.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxgzh.utils.HttpUtil;
import org.junit.Test;

import java.util.HashMap;

/**
 * HttpClientTest class
 *
 * @author BowenWang
 * @date 2019/08/03
 */
public class HttpClientTest {

    /**
     * 测试GET请求
     */
    @Test
    public void testDoGet() {
        String result = HttpUtil.doGet("http://wangbowen.cn", null);
        System.out.println(result);
    }

    /**
     * 测试POST请求
     */
    @Test
    public void testDoPost() {
        HashMap<String, String> map = new HashMap<>();
        String question = "你好";
        map.put("question", question);
        String result = HttpUtil.doPost("http://101.37.245.209:9999/robot", map);
        JSONObject jsonObject = (JSONObject) JSON.parse(result);
        JSONObject semantic = jsonObject.getJSONArray("semantic").getJSONObject(0);
        JSONObject slots = semantic.getJSONArray("slots").getJSONObject(0);
        String normValue = slots.getString("normValue");
        System.out.println("Q: " + question);
        System.out.println("A: " + normValue);
    }
}
