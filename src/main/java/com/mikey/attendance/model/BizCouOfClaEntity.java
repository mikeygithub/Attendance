package com.mikey.attendance.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName hibernate_generate
 * @Author 麦奇
 * @Email biaogejiushibiao@outlook.com
 * @Date 11/21/19 11:13 AM
 * @Version 1.0
 * @Description:
 **/

@Entity
@Table(name = "biz_cou_of_cla", schema = "attendance", catalog = "")
public class BizCouOfClaEntity {
    private int bocoId;
    private Integer courseId;
    private Integer classesId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "boco_id")
    public int getBocoId() {
        return bocoId;
    }

    public void setBocoId(int bocoId) {
        this.bocoId = bocoId;
    }

    @Basic
    @Column(name = "course_id")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "classes_id")
    public Integer getClassesId() {
        return classesId;
    }

    public void setClassesId(Integer classesId) {
        this.classesId = classesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BizCouOfClaEntity that = (BizCouOfClaEntity) o;
        return bocoId == that.bocoId &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(classesId, that.classesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bocoId, courseId, classesId);
    }
}
