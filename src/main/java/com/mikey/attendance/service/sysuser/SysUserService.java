package com.mikey.attendance.service.sysuser;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.model.SysUserEntity;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-03 23:46
 * @Describe：
 **/
public interface SysUserService {

    /**
     * 添加
     * @param sysUserEntity
     */
    public void saveUser(SysUserEntity sysUserEntity);

    /**
     * 删除
     * @param sysUserEntity
     */
    public void deleteUser(SysUserEntity sysUserEntity);

    /**
     * 修改
     * @param sysUserEntity
     */
    public void updateUser(SysUserEntity sysUserEntity);

    /**
     * 查询
     * @param sysUserEntity
     * @return
     */
    public SysUserEntity findOneUser(SysUserEntity sysUserEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findAllUser(String key, PageBean<SysUserEntity> pageBean);
    /**
     * 批量删除
     * @param userIds
     */
    public void deleteAllUser(String[] userIds);

}
