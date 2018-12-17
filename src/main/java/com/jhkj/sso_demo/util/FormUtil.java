package com.jhkj.sso_demo.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: LinYan
 * @Date: 2018/9/29 11:24
 * @Description: 用于页面跳转的controller
 */
@Controller
public class FormUtil {

    @RequestMapping(value = "/{form}")
    public String form2Controller(@PathVariable("form") String form) {
        return form;
    }
}
