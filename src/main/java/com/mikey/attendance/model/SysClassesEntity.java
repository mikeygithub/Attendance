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
@Table(name = "sys_classes", schema = "attendance", catalog = "")
public class SysClassesEntity {
    private int classesId;
    private String classesName;
    private String classesCode;

    @Id
    @Column(name = "classes_id")
    public int getClassesId() {
        return classesId;
    }

    public void setClassesId(int classesId) {
        this.classesId = classesId;
    }

    @Basic
    @Column(name = "classes_name")
    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    @Basic
    @Column(name = "classes_code")
    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysClassesEntity that = (SysClassesEntity) o;
        return classesId == that.classesId &&
                Objects.equals(classesName, that.classesName) &&
                Objects.equals(classesCode, that.classesCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classesId, classesName, classesCode);
    }
}
