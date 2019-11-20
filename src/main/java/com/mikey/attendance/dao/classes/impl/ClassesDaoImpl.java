package com.mikey.attendance.dao.classes.impl;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.classes.ClassesDao;
import com.mikey.attendance.model.SysClassesEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:32
 * @Describe：
 **/
@Component
public class ClassesDaoImpl implements ClassesDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(SysClassesEntity classesEntity) {
        sessionFactory.getCurrentSession().save(classesEntity);
    }

    @Override
    public void delete(SysClassesEntity classesEntity) {
        sessionFactory.getCurrentSession().delete(classesEntity);
    }

    @Override
    public void update(SysClassesEntity classesEntity) {
        sessionFactory.getCurrentSession().update(classesEntity);
    }

    @Override
    public SysClassesEntity findById(SysClassesEntity classesEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysClassesEntity.class);

        List list = criteria.add(
                Restrictions.or(Restrictions.eq("classesId", classesEntity.getClassesId())))
                .setFirstResult(0)
                .setMaxResults(1).list();

        session.close();

        return list!=null&&list.size()>0? (SysClassesEntity) list.get(0) :null;

    }

    @Override
    public PageBean findByPage(String key, PageBean<SysClassesEntity> pageBean) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysClassesEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("classesCode", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("classesName", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(
                    criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                            .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()));
        session.close();

        return pageBean;
    }

    @Override
    public void deleteBatch(String[] Ids) {
        List<SysClassesEntity> list = new ArrayList<>();

        for (String id : Ids) {
            SysClassesEntity classesEntity = new SysClassesEntity();
            classesEntity.setClassesId(Integer.parseInt(id));
            list.add(classesEntity);
        }
        hibernateTemplate.deleteAll(list);
    }
}
