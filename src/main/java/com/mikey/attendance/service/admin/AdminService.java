package com.mikey.attendance.service.admin;

import com.mikey.attendance.model.SysAdminEntity;

import java.util.List;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:10
 * @Describe：
 **/
public interface AdminService {

    void saveAdmin(SysAdminEntity sysAdminEntity);

    void updateAdmin(SysAdminEntity sysAdminEntity);

    void deleteAdmin(SysAdminEntity sysAdminEntity);

    SysAdminEntity findOneAdmin(SysAdminEntity sysAdminEntity);

    List<SysAdminEntity> findAllAdmin(SysAdminEntity sysAdminEntity);
}
