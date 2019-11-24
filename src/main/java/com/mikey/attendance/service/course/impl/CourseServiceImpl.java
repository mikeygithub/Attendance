package com.mikey.attendance.service.course.impl;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.course.CourseDao;
import com.mikey.attendance.model.SysCourseEntity;
import com.mikey.attendance.service.course.CourseService;
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
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public void save(SysCourseEntity sysCourseEntity,String courseClass) {

        courseDao.save(sysCourseEntity,courseClass);

    }

    @Override
    public void delete(SysCourseEntity sysCourseEntity) {

        courseDao.delete(sysCourseEntity);

    }

    @Override
    public void update(SysCourseEntity sysCourseEntity, String courseClasses) {

        courseDao.update(sysCourseEntity,courseClasses);

    }

    @Override
    public SysCourseEntity findById(SysCourseEntity sysCourseEntity) {
       return courseDao.findById(sysCourseEntity);
    }

    @Override
    public PageBean findByPage(String key,Integer teacherId, PageBean<SysCourseEntity> pageBean) {
        return courseDao.findByPage(key,teacherId,pageBean);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        courseDao.deleteBatch(Ids);
    }
}
