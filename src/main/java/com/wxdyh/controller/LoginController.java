package com.wxdyh.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxdyh.domain.common.Timer;
import com.wxdyh.utils.JwtUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * LoginController class
 *
 * @author BowenWang
 * @date 2019/08/09
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    /**
     * 登陆
     * @param token
     * @return
     */
    @PostMapping("/{token}")
    @ResponseBody
    public Object login(@PathVariable String token) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "fail");
        if (Timer.loginToken.equalsIgnoreCase(token)) {
            jsonObject.put("status", "success");
            jsonObject.put("token", JwtUtils.sign());
        }
        return jsonObject;
    }

    /**
     * 检查登陆
     * @param request
     * @return
     */
    @RequestMapping("/check/token")
    @ResponseBody
    public Object checkLogin(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "fail");
        String token = request.getHeader("token");
        if (null != token) {
            boolean result = JwtUtils.verify(token);
            if (result) {
                jsonObject.put("status", "success");
                return jsonObject;
            }
        }
        return jsonObject;
    }
}
