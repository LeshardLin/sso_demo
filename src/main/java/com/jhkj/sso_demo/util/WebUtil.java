package com.jhkj.sso_demo.util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @auther: LinYan
 * @date: 2018/11/20 9:43
 * @description: 工具类，用于将request请求中封装的数据写在JavaBean中
 */
public class WebUtil {

    public static <T> T request2Bean(HttpServletRequest request, Class<T> t) {
        try {
            T bean = t.newInstance();

            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String value = request.getParameter(name);

                //将KV值赋值给bean
                BeanUtils.setProperty(bean, name, value);
            }

            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
