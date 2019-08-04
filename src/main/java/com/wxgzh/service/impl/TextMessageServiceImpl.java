package com.wxgzh.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxgzh.service.TextMessageService;
import com.wxgzh.utils.HttpUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * TextMessageServiceImpl class
 *
 * @author BowenWang
 * @date 2019/08/03
 */
@Service
public class TextMessageServiceImpl implements TextMessageService {
    @Override
    public String getRobotReply(String question) {
        HashMap<String, String> map = new HashMap<>(1);
        map.put("question", question);
        String result = HttpUtils.doPost("http://101.37.245.209:9999/robot", map);
        JSONObject jsonObject = (JSONObject) JSON.parse(result);
        JSONObject semantic = jsonObject.getJSONArray("semantic").getJSONObject(0);
        JSONObject slots = semantic.getJSONArray("slots").getJSONObject(0);
        return slots.getString("normValue");
    }
}
