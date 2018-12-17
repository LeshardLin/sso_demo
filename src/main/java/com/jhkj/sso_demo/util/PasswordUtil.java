package com.jhkj.sso_demo.util;

import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * @auther: LinYan
 * @date: 2018/11/20 11:15
 * @description:
 */
public class PasswordUtil {
    //设置散列次数
    private final static int HASH_ITERATIONS = 1024;

    /**
     * 自定义加密
     *
     * @param source 明文密码
     * @return 经过SHA256加密并散列1024次加盐的密文密码
     */
    public static String getPassword(String source,String salt) {
        return new Sha256Hash(source, salt, HASH_ITERATIONS).toString();
    }
}
