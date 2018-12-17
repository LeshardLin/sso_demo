package com.jhkj.sso_demo.service;

import com.jhkj.face2019_demo.entity.SysDept;
import com.jhkj.face2019_demo.repository.SysDeptRepository;
import com.jhkj.face2019_demo.util.LevelUtil;
import com.jhkj.face2019_demo.vo.DeptVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

/**
 * @auther: LinYan
 * @date: 2018/11/30 15:36
 * @description:部门服务层实现类
 */
@Service
@Slf4j
public class SysDeptService {

    @Autowired
    SysDeptRepository sysDeptRepository;

    /**
     * 新增部门
     *
     * @param deptVO
     * @return
     */
    public boolean save(DeptVO deptVO) {
        Optional<SysDept> optionalSysDept = sysDeptRepository.findByName(deptVO.getName());
        //验证部门是否存在
        if (optionalSysDept.isPresent()) {
            log.info("部门已存在");
            return false;
        } else {
            //创建部门
            SysDept dept = new SysDept();
            BeanUtils.copyProperties(deptVO, dept);

            /**
             * 设置层级
             */
            dept.setLevel(LevelUtil.calculateLevel(getLevel(deptVO.getParentId()), deptVO.getParentId()));

            /**
             * 设置操作人
             */
            //TODO 设置操作人和IP
            //获取当前用户
//            SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal()
            dept.setOperator("system");
            dept.setOperateIp("127.0.0.1");
            /**
             * 获取当前时间
             */
            dept.setOperateTime(new Timestamp(new Date().getTime()));

            sysDeptRepository.save(dept);
            return true;
        }
    }

    /**
     * 更新部门信息
     *
     * @param deptVO 部门视图页面对象
     * @return 成功或失败标识
     */
//    public boolean update(DeptVO deptVO) {
//        //根据页面对象的ID查询数据库中的对象
//        Optional<SysDept> optionalSysDept = sysDeptRepository.findById(deptVO.getId());
//        //验证部门是否存在
//        if (optionalSysDept.isPresent()) {
//            //修改当前部门信息，将DeptVo中的值拷贝至dept中
//            SysDept dept = new SysDept();
//            BeanUtils.copyProperties(deptVO, dept);
//
//            /**
//             * 设置层级
//             */
//            dept.setLevel(LevelUtil.calculateLevel(getLevel(deptVO.getParentId()), deptVO.getParentId()));
//
//            /**
//             * 设置操作人
//             */
//            //TODO 设置操作人和IP
//            dept.setOperator("system");
//            dept.setOperateIp("127.0.0.1");
//            /**
//             * 获取当前时间
//             */
//            dept.setOperateTime(new Timestamp(new Date().getTime()));
//            sysDeptRepository.save(dept);
//            return true;
//        } else {
//            log.info("部门不存在");
//            return false;
//        }
//    }

    /**
     * 根据传入的部门id获取层级
     *
     * @param deptId 部门id
     * @return 当前部门id的层级
     */
    private String getLevel(Integer deptId) {
        Optional<SysDept> optionalSysDept = sysDeptRepository.findById(deptId);
        if (optionalSysDept.isPresent()) {
            return optionalSysDept.get().getLevel();
        } else {
            return null;
        }
    }
}
