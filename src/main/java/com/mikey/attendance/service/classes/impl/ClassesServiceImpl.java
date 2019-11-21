package com.mikey.attendance.service.classes.impl;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.classes.ClassesDao;
import com.mikey.attendance.model.SysClassesEntity;
import com.mikey.attendance.service.classes.ClassesService;
import com.mikey.attendance.vo.R;
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
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesDao classesDao;

    @Override
    public void save(SysClassesEntity classesEntity) {
        classesDao.save(classesEntity);
    }

    @Override
    public void delete(SysClassesEntity classesEntity) {
        classesDao.delete(classesEntity);
    }

    @Override
    public void update(SysClassesEntity classesEntity) {
        classesDao.update(classesEntity);
    }

    @Override
    public SysClassesEntity findById(SysClassesEntity classesEntity) {
       return classesDao.findById(classesEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<SysClassesEntity> pageBean) {

        return classesDao.findByPage(key,pageBean);
    }

    @Override
    public void deleteBatch(String[] Ids) {
      classesDao.deleteBatch(Ids);
    }

    @Override
    public R getClasses(String courseId) {
        return classesDao.getClasses(courseId);
    }
}
