package com.jhkj.sso_demo.dto;

import com.jhkj.face2019_demo.common.JsonData;
import com.jhkj.face2019_demo.entity.SysUser;
import com.jhkj.face2019_demo.vo.UserVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @auther: LinYan
 * @date: 2018/12/5 16:26
 * @description:
 */
@Data
@Slf4j
public class UserDto {
    Long uid;
    String account;
    String password;
    String rePassword;
    String salt;
    String rids;

    /**
     * 创建适配器，将当前对象转为POJO对象
     *
     * @return 数据库映射的实体类
     */
    public static SysUser adapt2Pojo(UserVO userVo) {
        SysUser user = new SysUser();
        //将userVo中的属性复制到user中
        BeanUtils.copyProperties(userVo, user);
        return user;
    }

    /**
     * 创建适配器，将Vo对象转为DTO对象
     *
     * @return
     */
    public static UserDto adapt2Dto(UserVO userVo) {
        UserDto userDto = new UserDto();
        //将userVo中的属性复制到user中
        BeanUtils.copyProperties(userVo, userDto);
        return userDto;
    }

    /**
     * 校验用户信息是否合法
     *
     * @return 成功或是失败的信息
     */
    public JsonData checkInfo() {
        //使用Reluctant数量匹配，从左向右依次将密码吞下，判断字母是否出现一次或多次
        int isAlpha = password.matches(".*\\p{Alpha}+?.*") ? 1 : 0;
        //使用Reluctant数量匹配，从左向右依次将密码吞下，判断数字是否出现一次或多次
        int isDigit = password.matches(".*\\p{Digit}+?.*") ? 1 : 0;
        //使用Reluctant数量匹配，从左向右依次将密码吞下，判断字符是否出现一次货多次
        int isPunct = password.matches(".*\\p{Punct}+?.*") ? 1 : 0;

        //校验账户名
        if ("".equals(account.trim())) {
            log.info("账号不能为空");
            return JsonData.fail("账号不能为空");
        } else if (account.contains(" ")) {
            log.info("账号不能包含空格");
            return JsonData.fail("账号不能包含空格");
        } else if (account.length() < 2 || account.length() > 15) {
            log.info("账号长度应当在2~15个字符之间");
            return JsonData.fail("账号长度应当在2~15个字符之间");
        }

        //校验密码
        if ("".equals(password.trim())) {
            log.info("密码不能为空");
            return JsonData.fail("密码不能为空");
        } else if (password.contains(" ")) {
            log.info("密码不能包含空格");
            return JsonData.fail("密码不能包含空格");
        } else if (isAlpha + isDigit + isPunct < 2 || password.length() < 6 || password.length() > 16) {
            log.info("密码应当为6~16位数字、字母或字符其中两种的组合");
            return JsonData.fail("密码应当为6~16位数字、字母或字符其中两种的组合");
        } else if (!password.equals(rePassword)) {
            log.info("密码不一致");
            return JsonData.fail("密码不一致");
        } else if (password.contains(account)) {
            log.info("密码包含用户名");
            return JsonData.fail("密码包含用户名");
        }
        return JsonData.success();
    }

}
