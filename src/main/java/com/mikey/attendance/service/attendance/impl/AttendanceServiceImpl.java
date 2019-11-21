package com.mikey.attendance.service.attendance.impl;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.attendance.AttendanceDao;
import com.mikey.attendance.model.BizAttendanceEntity;
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
    public void save(BizAttendanceEntity bizAttendanceEntity) {

        attendanceDao.save(bizAttendanceEntity);

    }

    @Override
    public void delete(BizAttendanceEntity bizAttendanceEntity) {

        attendanceDao.delete(bizAttendanceEntity);

    }

    @Override
    public void update(BizAttendanceEntity bizAttendanceEntity) {

        attendanceDao.update(bizAttendanceEntity);

    }

    @Override
    public BizAttendanceEntity findById(BizAttendanceEntity bizAttendanceEntity) {
       return attendanceDao.findById(bizAttendanceEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<BizAttendanceEntity> pageBean) {
        return attendanceDao.findByPage(key,pageBean);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        attendanceDao.deleteBatch(Ids);
    }
}
