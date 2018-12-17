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
@Table(name = "sys_log", schema = "face2019_demo", catalog = "")
public class SysLog {
    private int id;
    private int type;
    private int targetId;
    private String oldValue;
    private String newValue;
    private String operator;
    private Timestamp operateTime;
    private String operateIp;
    private int status;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "target_id")
    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    @Basic
    @Column(name = "old_value")
    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Basic
    @Column(name = "new_value")
    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
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

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysLog sysLog = (SysLog) o;
        return id == sysLog.id &&
                type == sysLog.type &&
                targetId == sysLog.targetId &&
                status == sysLog.status &&
                Objects.equals(oldValue, sysLog.oldValue) &&
                Objects.equals(newValue, sysLog.newValue) &&
                Objects.equals(operator, sysLog.operator) &&
                Objects.equals(operateTime, sysLog.operateTime) &&
                Objects.equals(operateIp, sysLog.operateIp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, targetId, oldValue, newValue, operator, operateTime, operateIp, status);
    }
}
