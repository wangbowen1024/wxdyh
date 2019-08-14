package com.wxdyh.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxdyh.enums.ErrorEnum;
import com.wxdyh.service.MaterialService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * CommonTest class
 * 通用接口测试类
 *
 * @author BowenWang
 * @date 2019/08/05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class CommonTest {

    private String token = "24_2m4lmPZawWWbmB4hLwHCU8A74k-S_MaPPDy3uQa-297IC-f8y4XRAf2kRuDoKFx5n4khGNmV2AQcgmM8KdrhTz4XftJzbWoWs_xuMnDcPrLp0HmoKBR4uebzXxq2j4a8toFUO4GW5kOCtg4oZSBgAIATMV";

    @Autowired
    private MaterialService materialService;

    /**
     * 调用获取素材图文（news）、图片（image）、视频（video）、语音 （voice）列表的方法
     * @throws Exception
     */
    @Test
    public void testGetMaterial() throws Exception {
        JSONArray jsonArray = materialService.getMaterialOfType(token,"news");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject);
        }
    }

    @Test
    public void test() throws Exception {
        System.out.println(ErrorEnum.TYPE_ERROR);
    }


}
