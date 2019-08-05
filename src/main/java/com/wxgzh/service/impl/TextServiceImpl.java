package com.wxgzh.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxgzh.dao.TextDao;
import com.wxgzh.domain.request.RequestText;
import com.wxgzh.domain.response.ResponseText;
import com.wxgzh.service.TextService;
import com.wxgzh.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * TextMessageServiceImpl class
 *
 * @author BowenWang
 * @date 2019/08/03
 */
@Service
public class TextServiceImpl implements TextService {

    @Autowired
    private TextDao textDao;

    @Override
    public ResponseText getRobotReply(String question) {
        HashMap<String, String> map = new HashMap<>(1);
        map.put("question", question);
        String result = HttpUtil.doPost("http://101.37.245.209:9999/robot", map);
        JSONObject jsonObject = (JSONObject) JSON.parse(result);
        JSONObject semantic = jsonObject.getJSONArray("semantic").getJSONObject(0);
        JSONObject slots = semantic.getJSONArray("slots").getJSONObject(0);
        String normValue = slots.getString("normValue");

        ResponseText responseTextMessage = new ResponseText();
        responseTextMessage.setContent(normValue);
        return responseTextMessage;
    }

    @Override
    public void saveText(RequestText requestTextMessage) {
        textDao.saveText(requestTextMessage);
    }

    @Override
    public ResponseText returnText(String content) {
        ResponseText responseText = new ResponseText();
        responseText.setContent(content);
        return responseText;
    }


}
