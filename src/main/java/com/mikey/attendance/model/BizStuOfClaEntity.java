package com.mikey.attendance.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName hibernate_generate
 * @Author 麦奇
 * @Email biaogejiushibiao@outlook.com
 * @Date 11/22/19 8:35 AM
 * @Version 1.0
 * @Description:
 **/

@Entity
@Table(name = "biz_stu_of_cla", schema = "attendance", catalog = "")
public class BizStuOfClaEntity {
    private int bsocId;
    private Integer stuId;
    private Integer claId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bsoc_id")
    public int getBsocId() {
        return bsocId;
    }

    public void setBsocId(int bsocId) {
        this.bsocId = bsocId;
    }

    @Basic
    @Column(name = "stu_id")
    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    @Basic
    @Column(name = "cla_id")
    public Integer getClaId() {
        return claId;
    }

    public void setClaId(Integer claId) {
        this.claId = claId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BizStuOfClaEntity that = (BizStuOfClaEntity) o;
        return bsocId == that.bsocId &&
                Objects.equals(stuId, that.stuId) &&
                Objects.equals(claId, that.claId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bsocId, stuId, claId);
    }
}
