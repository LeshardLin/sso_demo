package com.jhkj.sso_demo.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @auther: LinYan
 * @date: 2018/12/13 10:28
 * @description:
 */
@Entity
@Table(name = "sys_group_acl", schema = "face2019_demo", catalog = "")
public class SysGroupAcl {
    private int id;
    private int roleId;
    private int aclId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "acl_id")
    public int getAclId() {
        return aclId;
    }

    public void setAclId(int aclId) {
        this.aclId = aclId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysGroupAcl that = (SysGroupAcl) o;
        return id == that.id &&
                roleId == that.roleId &&
                aclId == that.aclId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, aclId);
    }
}
