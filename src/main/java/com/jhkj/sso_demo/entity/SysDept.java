package com.jhkj.sso_demo.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @auther: LinYan
 * @date: 2018/12/13 10:28
 * @description:
 */
@Entity
@Table(name = "sys_dept", schema = "face2019_demo", catalog = "")
public class SysDept {
    private int id;
    private String name;
    private int parentId;
    private String level;
    private int seq;
    private String remark;
    private String operator;
    private Timestamp operateTime;
    private String operateIp;

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
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "operate_time")
    public Timestamp getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Timestamp operateTime) {
        this.operateTime = operateTime;
    }

    @Basic
    @Column(name = "operate_ip")
    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysDept sysDept = (SysDept) o;
        return id == sysDept.id &&
                parentId == sysDept.parentId &&
                seq == sysDept.seq &&
                Objects.equals(name, sysDept.name) &&
                Objects.equals(level, sysDept.level) &&
                Objects.equals(remark, sysDept.remark) &&
                Objects.equals(operator, sysDept.operator) &&
                Objects.equals(operateTime, sysDept.operateTime) &&
                Objects.equals(operateIp, sysDept.operateIp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId, level, seq, remark, operator, operateTime, operateIp);
    }
}
