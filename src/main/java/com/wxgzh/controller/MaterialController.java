package com.wxgzh.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxgzh.enums.ErrorEnum;
import com.wxgzh.enums.MaterialEnum;
import com.wxgzh.service.MaterialService;
import com.wxgzh.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public static Map<String, JSONObject> newsMap = new HashMap<>(16);
    public static Map<String, JSONObject> imageMap = new HashMap<>(16);
    public static Map<String, JSONObject> voiceMap = new HashMap<>(16);
    public static Map<String, JSONObject> videoMap = new HashMap<>(16);

    /**
     * 设置初始化刷新判断标记
     */
    private boolean isInitNewsMap = false;
    private boolean isInitImageMap = false;
    private boolean isInitVoiceMap = false;
    private boolean isInitVideoMap = false;

    @Autowired
    private MaterialService materialService;

    /**
     * 查询素材列表
     * @param type 查询的素材类型
     * @return
     * @throws Exception
     */
    @PostMapping("/{type}")
    @ResponseBody
    public Object getMaterial(@PathVariable String type) throws Exception {
        if (MaterialEnum.NEWS.getType().equals(type)) {
            if (!isInitNewsMap) {
                freshMaterial("news");
                isInitNewsMap = true;
            }
            return newsMap.values();
        } else if (MaterialEnum.IMAGE.getType().equals(type)) {
            if (!isInitImageMap) {
                freshMaterial("image");
                isInitImageMap = true;
            }
            return imageMap.values();
        } else if (MaterialEnum.VIDEO.getType().equals(type)) {
            if (!isInitVideoMap) {
                freshMaterial("video");
                isInitVideoMap = true;
            }
            return videoMap.values();
        } else if (MaterialEnum.VOICE.getType().equals(type)) {
            if (!isInitVoiceMap) {
                freshMaterial("voice");
                isInitVoiceMap = true;
            }
            return voiceMap.values();
        } else {
            return ErrorEnum.TYPE_ERROR.toJson();
        }
    }

    @PostMapping("/fresh/{type}")
    @ResponseBody
    public Object freshMaterial(@PathVariable String type) throws Exception {
        if (MaterialEnum.NEWS.getType().equals(type)) {
            freshMap(newsMap, "news");
        } else if (MaterialEnum.IMAGE.getType().equals(type)) {
            freshMap(imageMap, "image");
        } else if (MaterialEnum.VOICE.getType().equals(type)) {
            freshMap(voiceMap, "voice");
        } else if (MaterialEnum.VIDEO.getType().equals(type)) {
            freshMap(videoMap, "video");
        }
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
