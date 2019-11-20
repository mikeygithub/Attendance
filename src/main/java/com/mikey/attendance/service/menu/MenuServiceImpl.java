package com.mikey.attendance.service.menu;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.menu.MenuDao;
import com.mikey.attendance.model.SysMenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: MSOSLE
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-17 10:53
 * @Describe：
 **/
@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuDao menuDao;

    @Override
    public void save(SysMenuEntity sysMenuEntity) {
        menuDao.save(sysMenuEntity);
    }

    @Override
    public void delete(SysMenuEntity sysMenuEntity) {

    }

    @Override
    public void update(SysMenuEntity sysMenuEntity) {

    }

//    @Override
//    public InstructorEntity findById(SysMenuEntity sysMenuEntity) {
//        return null;
//    }

    @Override
    public PageBean findByPage(String key, PageBean<SysMenuEntity> pageBean) {
        return null;
    }

    @Override
    public void deleteBatch(String[] Ids) {

    }

    @Override
    public List<SysMenuEntity> getMenuByRoleType(String roleType) {
        return menuDao.getMenuByRoleType(roleType);
    }
}
