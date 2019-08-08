package com.wxgzh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxgzh.enums.ErrorEnum;
import com.wxgzh.enums.MaterialEnum;
import com.wxgzh.service.MaterialService;
import com.wxgzh.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

    @PostMapping("/{type}/{token}")
    @ResponseBody
    public Object getMaterial(@PathVariable String type, @PathVariable String token) throws Exception {
        if (MaterialEnum.NEWS.getType().equals(type) || MaterialEnum.IMAGE.getType().equals(type) ||
                MaterialEnum.VIDEO.getType().equals(type) || MaterialEnum.VOICE.getType().equals(type)) {
            return materialService.getMaterialJsonObject(/*TokenUtil.getAccessToken()*/"24_R9CBdmtThdbCI1fh1Ir3P9quTpPLKG8KLDjsMYAZHrJLeWqvEVW3OLyROgcfDNpMGnLxNSSNJVfzkZhA9FaDK3aj1ezjVLcn4N9Ugtdb8uuv4GDGfrmN-cex6bvPaO1F66J1AA0DCs9UfilrRZRgAJABJB\n", type,0,20);
        } else {
            return ErrorEnum.TYPE_ERROR.toJson();
        }
    }
}
