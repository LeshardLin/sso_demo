package com.jhkj.sso_demo.repository;

import com.jhkj.face2019_demo.entity.SysDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @auther: LinYan
 * @date: 2018/11/30 15:38
 * @description:
 */
@Repository
public interface SysDeptRepository extends JpaRepository<SysDept, Integer> {
    /**
     * 根据部门名称查询部门信息
     *
     * @param deptName 部门名称
     * @return 部门信息
     */
    Optional<SysDept> findByName(String deptName);
}
