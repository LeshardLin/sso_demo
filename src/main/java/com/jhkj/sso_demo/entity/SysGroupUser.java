package com.jhkj.sso_demo.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @auther: LinYan
 * @date: 2018/12/13 10:28
 * @description:
 */
@Entity
@Table(name = "sys_group_user", schema = "face2019_demo")
public class SysGroupUser {
    private int id;
    private int roleId;
    private int userId;

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
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysGroupUser that = (SysGroupUser) o;
        return id == that.id &&
                roleId == that.roleId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, userId);
    }
}
