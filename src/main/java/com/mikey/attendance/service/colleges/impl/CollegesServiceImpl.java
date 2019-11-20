package com.mikey.attendance.service.colleges.impl;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.colleges.CollegesDao;
import com.mikey.attendance.model.SysCollegeEntity;
import com.mikey.attendance.service.colleges.CollegesService;
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
public class CollegesServiceImpl implements CollegesService {

    @Autowired
    private CollegesDao collegesDao;

    @Override
    public void save(SysCollegeEntity collegesEntity) {

        collegesDao.save(collegesEntity);

    }

    @Override
    public void delete(SysCollegeEntity collegesEntity) {

        collegesDao.delete(collegesEntity);

    }

    @Override
    public void update(SysCollegeEntity collegesEntity) {

        collegesDao.update(collegesEntity);

    }

    @Override
    public SysCollegeEntity findById(SysCollegeEntity collegesEntity) {
       return collegesDao.findById(collegesEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<SysCollegeEntity> pageBean) {
        return collegesDao.findByPage(key,pageBean);
    }

    @Override
    public void deleteBatch(String[] Ids) {
       collegesDao.deleteBatch(Ids);
    }
}
