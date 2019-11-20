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
@Table(name = "sys_college", schema = "attendance", catalog = "")
public class SysCollegeEntity {
    private int collegeId;
    private String collegeName;
    private String collegeCode;

    @Id
    @Column(name = "college_id")
    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    @Basic
    @Column(name = "college_name")
    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @Basic
    @Column(name = "college_code")
    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysCollegeEntity that = (SysCollegeEntity) o;
        return collegeId == that.collegeId &&
                Objects.equals(collegeName, that.collegeName) &&
                Objects.equals(collegeCode, that.collegeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collegeId, collegeName, collegeCode);
    }
}
