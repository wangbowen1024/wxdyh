package com.wxgzh.domain.common;

import com.alibaba.fastjson.JSONObject;
import com.wxgzh.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * UrlInterceptor class
 * URL拦截器
 *
 * @author BowenWang
 * @date 2019/08/13
 */
public class UrlInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 内部转发请求，直接放行
        String forwardCheck = (String) request.getAttribute("forwardCheck");
        if (forwardCheck != null && forwardCheck.equals(ConfigInfo.TOKEN)) {
            return true;
        }
        String token = request.getHeader("token");
        if (null != token) {
            return JwtUtils.verify(token);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("redirect", "true");
        response.getWriter().print(jsonObject);
        return false;
    }

}
