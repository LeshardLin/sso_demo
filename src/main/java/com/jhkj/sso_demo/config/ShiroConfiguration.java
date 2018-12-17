package com.jhkj.sso_demo.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @auther: LinYan
 * @date: 2018/11/22 9:25
 * @description: Shiro配置类
 */
@Configuration
public class ShiroConfiguration {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        //Key是路径的正则表达式，Value是使用什么样的拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //authc表示需要验证身份才能访问，还有一些比如anon表示不需要验证身份就能访问等。
        filterChainDefinitionMap.put("/logout", "logout");
        //index页面必须要身份认证或者"记住我"才能访问
        filterChainDefinitionMap.put("/", "user");
        filterChainDefinitionMap.put("/index", "user");
        //login不需要身份认证
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        //开放登录注册接口
        filterChainDefinitionMap.put("/userLogin", "anon");
        filterChainDefinitionMap.put("/userRegister", "anon");

//        filterChainDefinitionMap.put("/queryUser", "perms[user:query]");
//        filterChainDefinitionMap.put("/druid/**", "anon");
        //写在最后，拦截所有请求
        //authc拦截器负责拦截需要登录认证的路径,但是"记住我"不能够被识别通过。
        //user拦截器内部会执行authc拦截器,登录认证完成和"记住我"都可以通过。
        //所以如果不使用"记住我"功能就配置authc拦截器,如果使用"记住我"就配置user拦截器。
        filterChainDefinitionMap.put("/**", "user");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * @param credentialMatcher 已经通过@Bean注入的credentiaMatcher对象
     * @return
     */
    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentiaMatcher") HashedCredentialsMatcher credentialMatcher) {
        AuthRealm authRealm = new AuthRealm();
        //设置缓存
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        authRealm.setCredentialsMatcher(credentialMatcher);
        return authRealm;
    }

    //设置加密方式，用于对用户登录的明文密码进行加密对比
    @Bean("credentiaMatcher")
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-256");//散列算法
        hashedCredentialsMatcher.setHashIterations(1024);//散列的次数
        return hashedCredentialsMatcher;
    }

    //开启Shiri AOP注解支持。使用代理方式;所以需要开启代码支持。
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    //为匹配的目标Bean自动创建代理。
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     *
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        //记住我cookie生效时间7天 ,单位秒;
        simpleCookie.setMaxAge(60 * 60 * 24 * 7);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

}
