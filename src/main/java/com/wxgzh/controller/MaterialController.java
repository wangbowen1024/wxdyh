package com.wxgzh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxgzh.domain.material.Item;
import com.wxgzh.domain.material.News;
import com.wxgzh.enums.ErrorEnum;
import com.wxgzh.enums.MaterialEnum;
import com.wxgzh.service.MaterialService;
import com.wxgzh.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * MaterialController class
 *
 * @author BowenWang
 * @date 2019/08/06
 */
@Controller
@RequestMapping("/material")
public class MaterialController {
    /**
     * 用于缓存素材数据的Map
     */
    public static Map<String, JSONObject> newsMap = new HashMap<>();
    public static Map<String, JSONObject> imageMap = new HashMap<>();
    public static Map<String, JSONObject> voiceMap = new HashMap<>();
    public static Map<String, JSONObject> videoMap = new HashMap<>();

    /**
     * 设置初始化刷新判断标记
     */
    private boolean freshed = false;

    @Autowired
    private MaterialService materialService;

    @PostMapping("/{type}")
    @ResponseBody
    public Object getMaterial(@PathVariable String type) throws Exception {
        if (!freshed) {
            freshMaterial();
            freshed = true;
        }
        if (MaterialEnum.NEWS.getType().equals(type)) {
            return newsMap.values();
        } else if (MaterialEnum.IMAGE.getType().equals(type)) {
            return imageMap.values();
        } else if (MaterialEnum.VIDEO.getType().equals(type)) {
            return videoMap.values();
        } else if (MaterialEnum.VOICE.getType().equals(type)) {
            return voiceMap.values();
        } else {
            return ErrorEnum.TYPE_ERROR.toJson();
        }
    }

    @PostMapping("/fresh")
    @ResponseBody
    public Object freshMaterial() throws Exception {
        freshMap(newsMap, "news");
        freshMap(imageMap, "image");
        freshMap(voiceMap, "voice");
        freshMap(videoMap, "video");
        return new JSONObject().put("status", "success");
    }

    /**
     * 更新Map
     * @param map
     * @param type
     * @throws Exception
     */
    private void freshMap(Map<String, JSONObject> map, String type) throws Exception {
        map.clear();
        JSONArray materials = materialService.getMaterialOfType(TokenUtil.getAccessToken(), type);
        for (int i = 0; i < materials.size(); i++) {
            JSONObject jsonObject = materials.getJSONObject(i);
            if (MaterialEnum.NEWS.getType().equals(type)) {
                map.put(jsonObject.getString("thumb_media_id"), jsonObject);
            } else {
                map.put(jsonObject.getString("media_id"), jsonObject);
            }
        }
    }

}
