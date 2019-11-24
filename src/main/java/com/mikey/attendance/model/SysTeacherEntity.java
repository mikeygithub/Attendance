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
@Table(name = "sys_teacher", schema = "attendance", catalog = "")
public class SysTeacherEntity {
    private int teacherId;
    private String teacherName;
    private String teacherCode;
    private String teacherPhone;
    private String teacherEmail;
    private Integer userId;

    @Transient
    private SysUserEntity sysUserEntity;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teacher_id")
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "teacher_name")
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Basic
    @Column(name = "teacher_code")
    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    @Basic
    @Column(name = "teacher_phone")
    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    @Basic
    @Column(name = "teacher_email")
    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysTeacherEntity that = (SysTeacherEntity) o;
        return teacherId == that.teacherId &&
                Objects.equals(teacherName, that.teacherName) &&
                Objects.equals(teacherCode, that.teacherCode) &&
                Objects.equals(teacherPhone, that.teacherPhone) &&
                Objects.equals(teacherEmail, that.teacherEmail);
    }

    @Transient
    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherName, teacherCode, teacherPhone, teacherEmail);
    }
}
