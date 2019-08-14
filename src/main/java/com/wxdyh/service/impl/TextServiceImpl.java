package com.wxdyh.service.impl;

import com.wxdyh.dao.TextDao;
import com.wxdyh.domain.request.TextRequest;
import com.wxdyh.domain.response.TextResponse;
import com.wxdyh.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public void saveText(TextRequest requestTextMessage) {
        textDao.saveText(requestTextMessage);
    }

    @Override
    public TextResponse returnText(String content) {
        TextResponse responseText = new TextResponse();
        responseText.setContent(content);
        return responseText;
    }


}
