package com.jhkj.sso_demo.repository;

import com.jhkj.face2019_demo.entity.SysGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @auther: LinYan
 * @date: 2018/12/13 13:56
 * @description:
 */
@Repository
public interface SysGroupRepository extends JpaRepository<SysGroup, Integer> {

    Optional<SysGroup> findByName(String name);
}
