package com.jhkj.sso_demo.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @auther: LinYan
 * @date: 2018/11/30 14:56
 * @description:
 */
@Data
public class DeptVO {
    /**
     * 更新删除部门时需要id
     */
    private int id;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不可以为空")
    @Length(max = 15, min = 2, message = "部门名称长度需要在2~15个字之间")
    private String name;
    /**
     * 上级部门id
     */
    private int parentId;
    /**
     * 展示顺序
     */
    @NotNull(message = "展示顺序不可以为空")
    private int seq;
    /**
     * 备注
     */
    @Length(max = 150, message = "备注的长度需要在150个字之间")
    private String remark;
}
