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
@Table(name = "sys_student", schema = "attendance", catalog = "")
public class SysStudentEntity {
    private int studentId;
    private String studentName;
    private String studentCode;
    private String studentPhone;
    private Integer userId;

    @Transient
    private SysUserEntity sysUserEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "student_code")
    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    @Basic
    @Column(name = "student_phone")
    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Transient
    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysStudentEntity that = (SysStudentEntity) o;
        return studentId == that.studentId &&
                Objects.equals(studentName, that.studentName) &&
                Objects.equals(studentCode, that.studentCode) &&
                Objects.equals(studentPhone, that.studentPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentName, studentCode, studentPhone);
    }
}
