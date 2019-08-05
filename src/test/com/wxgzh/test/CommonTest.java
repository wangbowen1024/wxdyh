package com.wxgzh.test;

import com.alibaba.fastjson.JSONObject;
import com.wxgzh.domain.common.ConfigInfo;
import com.wxgzh.domain.innerclass.Material;
import com.wxgzh.domain.innerclass.MaterialImage;
import com.wxgzh.utils.CommonUtil;
import org.junit.Test;

import java.util.List;

/**
 * CommonTest class
 * 通用接口测试类
 *
 * @author BowenWang
 * @date 2019/08/05
 */
public class CommonTest {
    /**
     * 调用获取素材图文（news）列表的方法
     * @throws Exception
     */
    @Test
    public void testGetMaterial() throws Exception {
        JSONObject jsonObject = CommonUtil.getMaterialJsonObject(CommonUtil.accessToken, "news", 0, 10);
        System.out.println(jsonObject);
    }

    /**
     * 调用获取素材图片（image）、视频（video）、语音 （voice）
     * @throws Exception
     */
    @Test
    public void testGetMaterial2() throws Exception {
        JSONObject jsonObject = CommonUtil.getMaterialJsonObject(CommonUtil.accessToken, "image", 0, 10);
        System.out.println(jsonObject);
        List<MaterialImage> image = CommonUtil.getMaterialOfImage(CommonUtil.accessToken, "image", 0, 10);
        System.out.println("SIZE: " + image.size());
    }
}
