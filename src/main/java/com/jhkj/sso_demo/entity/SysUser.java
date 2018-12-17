package com.jhkj.sso_demo.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @auther: LinYan
 * @date: 2018/12/14 10:16
 * @description:
 */
@Entity
@Table(name = "sys_user", schema = "face2019_demo")
public class SysUser {
    private int id;
    private String account;
    private String telephone;
    private String mail;
    private String password;
    private String salt;
    private int type;
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
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
        SysUser user = (SysUser) o;
        return id == user.id &&
                type == user.type &&
                status == user.status &&
                Objects.equals(account, user.account) &&
                Objects.equals(telephone, user.telephone) &&
                Objects.equals(mail, user.mail) &&
                Objects.equals(password, user.password) &&
                Objects.equals(remark, user.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, telephone, mail, password, type, status, remark);
    }
}
