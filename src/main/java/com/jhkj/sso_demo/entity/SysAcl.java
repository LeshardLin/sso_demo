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
@Table(name = "sys_acl", schema = "face2019_demo", catalog = "")
public class SysAcl {
    private int id;
    private String code;
    private String name;
    private int aclModuleId;
    private String url;
    private int type;
    private int status;
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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "acl_module_id")
    public int getAclModuleId() {
        return aclModuleId;
    }

    public void setAclModuleId(int aclModuleId) {
        this.aclModuleId = aclModuleId;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        SysAcl sysAcl = (SysAcl) o;
        return id == sysAcl.id &&
                aclModuleId == sysAcl.aclModuleId &&
                type == sysAcl.type &&
                status == sysAcl.status &&
                seq == sysAcl.seq &&
                Objects.equals(code, sysAcl.code) &&
                Objects.equals(name, sysAcl.name) &&
                Objects.equals(url, sysAcl.url) &&
                Objects.equals(remark, sysAcl.remark) &&
                Objects.equals(operator, sysAcl.operator) &&
                Objects.equals(operateTime, sysAcl.operateTime) &&
                Objects.equals(operateIp, sysAcl.operateIp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, aclModuleId, url, type, status, seq, remark, operator, operateTime, operateIp);
    }
}
