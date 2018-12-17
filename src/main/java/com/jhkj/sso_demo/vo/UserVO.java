package com.jhkj.sso_demo.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @auther: LinYan
 * @date: 2018/11/28 10:47
 * @description:
 */
@Data
public class UserVO {

    /**
     * 添加用户进入组时需要id
     */
    private int id;
    /**
     * 账户名
     */
    @NotBlank(message = "账户名不可以为空")
    @Length(max = 15, min = 2, message = "账户名长度需要在2~15个字符之间")
    private String account;

    @NotBlank(message = "密码不能为空")
    @Length(max = 16, min = 6, message = "密码应当为6~16位数字、字母或字符其中两种的组合")
    private String password;

    @NotBlank(message = "重复密码不能为空")
    @Length(max = 16, min = 6, message = "重复密码长度需要在6~16个字符之间")
    private String rePassword;

    private String rememberMe;
}
