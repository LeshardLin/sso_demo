package com.jhkj.sso_demo.repository;

import com.jhkj.face2019_demo.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @auther: LinYan
 * @date: 2018/11/27 10:07
 * @description:
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

    /**
     * 根据账户名查询用户
     *
     * @param account 用户的账户名
     * @return 用户类型的optional对象，optional可能为null
     */
    Optional<SysUser> findByAccount(String account);
}
