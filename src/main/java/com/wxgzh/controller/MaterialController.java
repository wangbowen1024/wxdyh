package com.wxgzh.controller;

import com.alibaba.fastjson.JSON;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    private String token = "24_-Sm5Ic9J5uax2FT1zIzTqPk49mUhO-3DM8X4_N0AVt6koK1klAbW5PVnRjS4ttcaR1jKGIqqa8wtlErb_AWYVZWRbnRwVLM6alspwns-6Brm4me43CZCgIEr024-X6Gj45oWCh3lVZWxekbyJFEhAIAZVU";


    @GetMapping("/{type}")
    @ResponseBody
    public Object getMaterial(@PathVariable String type) throws Exception {
        if (MaterialEnum.NEWS.getType().equals(type) || MaterialEnum.IMAGE.getType().equals(type) ||
                MaterialEnum.VIDEO.getType().equals(type) || MaterialEnum.VOICE.getType().equals(type)) {
            return materialService.getMaterialOfType(token, type);
        } else {
            return ErrorEnum.TYPE_ERROR.toJson();
        }
    }
}
