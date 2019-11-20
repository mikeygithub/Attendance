package com.mikey.attendance.service.attendance.impl;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.attendance.AttendanceDao;
import com.mikey.attendance.dao.colleges.CollegesDao;
import com.mikey.attendance.dao.course.CourseDao;
import com.mikey.attendance.model.BusAttendanceEntity;
import com.mikey.attendance.model.SysCollegeEntity;
import com.mikey.attendance.model.SysCourseEntity;
import com.mikey.attendance.service.attendance.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:32
 * @Describe：
 **/
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;

    @Override
    public void save(BusAttendanceEntity busAttendanceEntity) {

        attendanceDao.save(busAttendanceEntity);

    }

    @Override
    public void delete(BusAttendanceEntity busAttendanceEntity) {

        attendanceDao.delete(busAttendanceEntity);

    }

    @Override
    public void update(BusAttendanceEntity busAttendanceEntity) {

        attendanceDao.update(busAttendanceEntity);

    }

    @Override
    public BusAttendanceEntity findById(BusAttendanceEntity busAttendanceEntity) {
       return attendanceDao.findById(busAttendanceEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<BusAttendanceEntity> pageBean) {
        return attendanceDao.findByPage(key,pageBean);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        attendanceDao.deleteBatch(Ids);
    }
}
