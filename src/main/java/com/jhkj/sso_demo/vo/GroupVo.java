package com.jhkj.sso_demo.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @auther: LinYan
 * @date: 2018/12/13 14:03
 * @description:
 */
@Data
public class GroupVo {

    /**
     * 更新删除组时需要id
     */
    private int id;
    /**
     * 组名
     */
    @NotBlank(message = "组不可以为空")
    @Length(max = 15, min = 2, message = "组名的长度需要在2~15个字符之间")
    private String name;

//    /**
//     * 所属者
//     */
//    private int owner;

    /**
     * 可见人的id集合
     */
    private String visible;

    /**
     * 备注
     */
    @Length(max = 150, message = "备注的长度需要在150个字之间")
    private String remark;
}
