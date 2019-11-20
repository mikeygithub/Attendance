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
@Table(name = "sys_course", schema = "attendance", catalog = "")
public class SysCourseEntity {
    private int courseId;
    private String courseName;
    private String courseCode;
    private Integer teacherId;

    @Id
    @Column(name = "course_id")
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "course_name")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "course_code")
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Basic
    @Column(name = "teacher_id")
    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysCourseEntity that = (SysCourseEntity) o;
        return courseId == that.courseId &&
                Objects.equals(courseName, that.courseName) &&
                Objects.equals(courseCode, that.courseCode) &&
                Objects.equals(teacherId, that.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, courseCode, teacherId);
    }
}
