package com.jhkj.sso_demo.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther: LinYan
 * @date: 2018/11/27 10:15
 * @description: 自定义全局异常处理类
 */
@Slf4j
@ControllerAdvice
public class SpringExceptionResolver {

    @ExceptionHandler(UnauthorizedException.class)
    public ModelAndView unauthorizedExceptionHandle(HttpServletRequest request) {
        //获取URL地址
        String url = request.getRequestURL().toString();
        JsonData jsonData = JsonData.fail("unauthorized");
        log.error("unauthorized, url:" + url);
        ModelAndView modelAndView = new ModelAndView("/403", jsonData.toMap());
        return modelAndView;
    }

//    @Override
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        //获取URL地址
//        String url = request.getRequestURL().toString();
//        ModelAndView modelAndView;
//        //全局异常
//        //默认的MSG
//        String defaultMSG = "System error";
//
//        //设置数据请求以.json结尾，页面请求以.html结尾
//        if (url.endsWith(".json")) {
//            if (ex instanceof PermissionException) {
//                JsonData jsonData = JsonData.fail(ex.getMessage());
//                modelAndView = new ModelAndView("jsonView", jsonData.toMap());
//            } else if (ex instanceof UnauthorizedException) {
//                modelAndView = new ModelAndView("/403");
//            } else {
//                log.error("unknow json, url:" + url, ex);
//                JsonData jsonData = JsonData.fail(defaultMSG);
//                modelAndView = new ModelAndView("jsonView", jsonData.toMap());
//            }
//        } else if (url.endsWith(".html")) {
//            log.error("unknow page, url:" + url, ex);
//            JsonData jsonData = JsonData.fail(defaultMSG);
//            modelAndView = new ModelAndView("exception", jsonData.toMap());
//        } else {
//            log.error("unknow exception, url:" + url, ex);
//            JsonData jsonData = JsonData.fail(defaultMSG);
//            modelAndView = new ModelAndView("jsonView", jsonData.toMap());
//        }
//        return modelAndView;
//    }
}
