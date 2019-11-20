package com.mikey.attendance.dao.menu.impl;

import com.mikey.attendance.dao.menu.MenuDao;
import com.mikey.attendance.model.SysMenuEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Program: MSOSLE
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-17 10:43
 * @Describe：
 **/
@Component
public class MenuDaoImpl implements MenuDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    public List<SysMenuEntity> getMenuByRoleType(String roleType){

        System.out.println("角色类型："+roleType);

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysMenuEntity.class);

        List list = criteria.add(Restrictions.eq("roleType", roleType)).list();

        System.out.println("获取的菜单："+list);

        return list;

    }


    @Override
    public void save(SysMenuEntity sysMenuEntity) {

    }

    @Override
    public void delete(SysMenuEntity sysMenuEntity) {

    }

    @Override
    public void update(SysMenuEntity sysMenuEntity) {

    }

    @Override
    public SysMenuEntity findById(SysMenuEntity sysMenuEntity) {
        return null;
    }
}
