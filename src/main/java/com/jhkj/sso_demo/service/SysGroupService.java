package com.jhkj.sso_demo.service;

import com.jhkj.face2019_demo.entity.SysGroup;
import com.jhkj.face2019_demo.entity.SysUser;
import com.jhkj.face2019_demo.repository.SysGroupRepository;
import com.jhkj.face2019_demo.vo.GroupVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @auther: LinYan
 * @date: 2018/12/13 13:55
 * @description:
 */
@Service
@Slf4j
public class SysGroupService {

    @Autowired
    SysGroupRepository sysGroupRepository;

    public boolean save(GroupVo groupVo) {
        //根据组名查询组
        Optional<SysGroup> optionalSysGroup = sysGroupRepository.findByName(groupVo.getName());
        //验证当前组是否存在
        if (optionalSysGroup.isPresent()) {
            log.info("当前组已存在");
            return false;
        } else {
            //如果当前组不存在，创建新的组
            SysGroup group = new SysGroup();
            BeanUtils.copyProperties(groupVo, group);

            /**
             * 设置所属者人
             */
            //获取当前用户
            SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            group.setOwner(currentUser.getId());

            sysGroupRepository.save(group);
            return true;
        }
    }
}
