package com.mikey.attendance.service.student.impl;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.student.StudentDao;
import com.mikey.attendance.model.SysStudentEntity;
import com.mikey.attendance.service.student.StudentService;
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
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public void save(SysStudentEntity studentEntity,Integer classId) {
        studentDao.save(studentEntity,classId);
    }

    @Override
    public void delete(SysStudentEntity studentEntity) {
        studentDao.delete(studentEntity);
    }

    @Override
    public void update(SysStudentEntity studentEntity) {
        studentDao.update(studentEntity);
    }

    @Override
    public SysStudentEntity findById(SysStudentEntity studentEntity) {
       return studentDao.findById(studentEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<SysStudentEntity> pageBean) {

        return studentDao.findByPage(key,pageBean);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        studentDao.deleteBatch(Ids);
    }

    @Override
    public PageBean findByClassIdWithPage(Integer classId, Integer attendanceType, Integer number) {
        return studentDao.findByClassIdWithPage(classId,attendanceType,number);
    }

    @Override
    public R getStudentByClassId(Integer classId) {
        return studentDao.getStudentByClassId(classId);
    }
}
