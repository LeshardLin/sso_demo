package com.jhkj.sso_demo.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @auther: LinYan
 * @date: 2018/12/13 10:28
 * @description:
 */
@Entity
@Table(name = "sys_acl_module", schema = "face2019_demo", catalog = "")
public class SysAclModule {
    private int id;
    private String name;
    private int parentId;
    private String level;
    private int seq;
    private int status;
    private String remark;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "parent_id")
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Basic
    @Column(name = "seq")
    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysAclModule that = (SysAclModule) o;
        return id == that.id &&
                parentId == that.parentId &&
                seq == that.seq &&
                status == that.status &&
                Objects.equals(name, that.name) &&
                Objects.equals(level, that.level) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId, level, seq, status, remark);
    }
}
