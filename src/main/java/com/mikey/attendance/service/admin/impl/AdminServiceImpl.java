package com.mikey.attendance.service.admin.impl;

import com.mikey.attendance.dao.admin.AdminDao;
import com.mikey.attendance.model.SysAdminEntity;
import com.mikey.attendance.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:10
 * @Describe：
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;
    @Override
    public void saveAdmin(SysAdminEntity sysAdminEntity) {
        adminDao.saveAdmin(sysAdminEntity);
    }

    @Override
    public void updateAdmin(SysAdminEntity sysAdminEntity) {
        adminDao.updateAdmin(sysAdminEntity);
    }

    /**
     * 删除
     * @param sysAdminEntity
     */
    @Override
    public void deleteAdmin(SysAdminEntity sysAdminEntity) {
        adminDao.deleteAdmin(sysAdminEntity);
    }

    /**
     * 通过id查询
     * @param sysAdminEntity
     * @return
     */
    @Override
    public SysAdminEntity findOneAdmin(SysAdminEntity sysAdminEntity) {
        return adminDao.findOneAdmin(sysAdminEntity);
    }

    @Override
    public List<SysAdminEntity> findAllAdmin(SysAdminEntity sysAdminEntity) {

        return adminDao.findAllAdmin(sysAdminEntity);

    }


}
