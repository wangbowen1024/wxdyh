package com.wxgzh.controller;

import com.wxgzh.enums.ErrorEnum;
import com.wxgzh.enums.MaterialEnum;
import com.wxgzh.service.MaterialService;
import com.wxgzh.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * MaterialController class
 *
 * @author BowenWang
 * @date 2019/08/06
 */
@Controller
@RequestMapping("/Material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping("/{type}")
    @ResponseBody
    public Object getMaterial(@PathVariable String type) throws Exception {
        if (MaterialEnum.NEWS.getType().equals(type) || MaterialEnum.IMAGE.getType().equals(type) ||
                MaterialEnum.VIDEO.getType().equals(type) || MaterialEnum.VOICE.getType().equals(type)) {
            return materialService.getMaterialOfType(TokenUtil.getAccessToken(), type);
        } else {
            return ErrorEnum.TYPE_ERROR.toJson();
        }
    }
}
