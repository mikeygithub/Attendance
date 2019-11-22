package com.mikey.attendance.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName hibernate_generate
 * @Author 麦奇
 * @Email biaogejiushibiao@outlook.com
 * @Date 11/20/19 11:02 AM
 * @Version 1.0
 * @Description:
 **/

@Entity
@Table(name = "sys_admin", schema = "attendance", catalog = "")
public class SysAdminEntity {
    private int adminId;
    private String adminName;
    private String adminCode;
    private String adminPhone;
    private String adminEmail;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_id")
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "admin_name")
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Basic
    @Column(name = "admin_code")
    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    @Basic
    @Column(name = "admin_phone")
    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    @Basic
    @Column(name = "admin_email")
    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysAdminEntity that = (SysAdminEntity) o;
        return adminId == that.adminId &&
                Objects.equals(adminName, that.adminName) &&
                Objects.equals(adminCode, that.adminCode) &&
                Objects.equals(adminPhone, that.adminPhone) &&
                Objects.equals(adminEmail, that.adminEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, adminName, adminCode, adminPhone, adminEmail);
    }
}
