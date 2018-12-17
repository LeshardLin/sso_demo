package com.jhkj.sso_demo.service;

import com.jhkj.face2019_demo.entity.SysUser;
import com.jhkj.face2019_demo.repository.SysUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @auther: LinYan
 * @date: 2018/11/30 15:36
 * @description:用户服务层实现类
 */
@Service
@Slf4j
public class SysUserService {

    @Autowired
    SysUserRepository sysUserRepository;

    /**
     * 根据用户的账户名查询用户
     *
     * @param account 用户的账户名
     * @return 用户信息
     */
    public SysUser findUser(String account) {
        Optional<SysUser> userOptional = sysUserRepository.findByAccount(account);
        //判断userOptional是否为空
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            return null;
        }
    }

    /**
     * 新增用户
     *
     * @param userVO 录入的用户对象
     * @return
     */
//    public SysUser addUser(UserVO userVO) {
//        SysUser user = new SysUser();
//        user = UserDto.adapt2Pojo(userVO);
//
//
//    }
}
