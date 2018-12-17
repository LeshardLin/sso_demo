package com.jhkj.sso_demo.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @auther: LinYan
 * @date: 2018/12/13 10:28
 * @description:
 */
@Entity
@Table(name = "sys_dept_user", schema = "face2019_demo", catalog = "")
public class SysDeptUser {
    private int id;
    private int deptId;
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
    @Column(name = "dept_id")
    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
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
        SysDeptUser that = (SysDeptUser) o;
        return id == that.id &&
                deptId == that.deptId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deptId, userId);
    }
}
