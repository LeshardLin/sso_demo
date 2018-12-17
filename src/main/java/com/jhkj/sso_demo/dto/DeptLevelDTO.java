package com.jhkj.sso_demo.dto;

import com.jhkj.face2019_demo.entity.SysDept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: LinYan
 * @date: 2018/11/30 17:05
 * @description: 部门层级类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptLevelDTO extends SysDept {
    //创建部门层级的列表
    private List<DeptLevelDTO> deptList = new ArrayList<>();

    /**
     * 创建适配器，将POJO转为DTO
     *
     * @param dept POJO对象
     * @return dto对象
     */
    public static DeptLevelDTO adapt(SysDept dept) {
        DeptLevelDTO deptDto = new DeptLevelDTO();
        //将dept中的属性复制到deptDto中
        BeanUtils.copyProperties(dept, deptDto);
        return deptDto;
    }
}
