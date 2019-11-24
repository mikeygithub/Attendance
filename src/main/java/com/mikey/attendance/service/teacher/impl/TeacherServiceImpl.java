package com.mikey.attendance.service.teacher.impl;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.teacher.TeacherDao;
import com.mikey.attendance.model.SysTeacherEntity;
import com.mikey.attendance.service.teacher.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public void save(SysTeacherEntity sysTeacherEntity, Integer sex) {

        teacherDao.save(sysTeacherEntity,sex);

    }

    @Override
    public void delete(SysTeacherEntity sysTeacherEntity) {

        teacherDao.delete(sysTeacherEntity);

    }

    @Override
    public void update(SysTeacherEntity sysTeacherEntity, Integer sex) {

        teacherDao.update(sysTeacherEntity, sex);

    }

    @Override
    public SysTeacherEntity findById(SysTeacherEntity sysTeacherEntity) {
       return teacherDao.findById(sysTeacherEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<SysTeacherEntity> pageBean) {
        return teacherDao.findByPage(key,pageBean);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        teacherDao.deleteBatch(Ids);
    }
}
