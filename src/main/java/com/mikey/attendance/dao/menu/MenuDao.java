package com.mikey.attendance.dao.menu;

import com.mikey.attendance.model.SysMenuEntity;

import java.util.List;

/**
 * @Program: MSOSLE
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-17 10:47
 * @Describe：
 **/
public interface MenuDao {

    List<SysMenuEntity> getMenuByRoleType(String roleType);

    /**
     * 添加
     */
    public void save(SysMenuEntity sysMenuEntity);

    /**
     * 删除
     */
    public void delete(SysMenuEntity sysMenuEntity);

    /**
     * 修改
     */
    public void update(SysMenuEntity sysMenuEntity);

    /**
     * 查询
     *
     * @return
     */
    public SysMenuEntity findById(SysMenuEntity sysMenuEntity);

}
