package com.mikey.attendance.model;

import javax.persistence.*;
import java.sql.Timestamp;
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
@Table(name = "biz_attendance", schema = "attendance", catalog = "")
public class BizAttendanceEntity {
    private int attendanceId;
    private Integer attendanceType;
    private Timestamp attendanceTime;
    private Integer attendanceStuId;
    private Integer attendanceCasId;
    @Transient
    private SysStudentEntity sysStudentEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attendance_id")
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    @Basic
    @Column(name = "attendance_type")
    public Integer getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(Integer attendanceType) {
        this.attendanceType = attendanceType;
    }

    @Basic
    @Column(name = "attendance_time")
    public Timestamp getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Timestamp attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    @Basic
    @Column(name = "attendance_stu_id")
    public Integer getAttendanceStuId() {
        return attendanceStuId;
    }

    public void setAttendanceStuId(Integer attendanceStuId) {
        this.attendanceStuId = attendanceStuId;
    }

    @Basic
    @Column(name = "attendance_cas_id")
    public Integer getAttendanceCasId() {
        return attendanceCasId;
    }

    public void setAttendanceCasId(Integer attendanceCasId) {
        this.attendanceCasId = attendanceCasId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BizAttendanceEntity that = (BizAttendanceEntity) o;
        return attendanceId == that.attendanceId &&
                Objects.equals(attendanceType, that.attendanceType) &&
                Objects.equals(attendanceTime, that.attendanceTime) &&
                Objects.equals(attendanceStuId, that.attendanceStuId) &&
                Objects.equals(attendanceCasId, that.attendanceCasId);
    }

    @Transient
    public SysStudentEntity getSysStudentEntity() {
        return sysStudentEntity;
    }

    public void setSysStudentEntity(SysStudentEntity sysStudentEntity) {
        this.sysStudentEntity = sysStudentEntity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendanceId, attendanceType, attendanceTime, attendanceStuId, attendanceCasId);
    }
}
