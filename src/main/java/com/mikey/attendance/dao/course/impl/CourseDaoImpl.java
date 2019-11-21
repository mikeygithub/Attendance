package com.mikey.attendance.dao.course.impl;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.course.CourseDao;
import com.mikey.attendance.model.BizCouOfClaEntity;
import com.mikey.attendance.model.SysCollegeEntity;
import com.mikey.attendance.model.SysCourseEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
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
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(SysCourseEntity sysCourseEntity,String courseClass) {
        Integer save = (Integer) sessionFactory.getCurrentSession().save(sysCourseEntity);
        String[] ids = courseClass.split(",");
        for (String id:ids){
            BizCouOfClaEntity bizCouOfClaEntity = new BizCouOfClaEntity();
            bizCouOfClaEntity.setCourseId(save);
            bizCouOfClaEntity.setClassesId(Integer.parseInt(id));
            sessionFactory.getCurrentSession().save(bizCouOfClaEntity);
        }
    }

    @Override
    public void delete(SysCourseEntity sysCourseEntity) {
        sessionFactory.getCurrentSession().delete(sysCourseEntity);
    }

    @Override
    public void update(SysCourseEntity sysCourseEntity) {
        sessionFactory.getCurrentSession().update(sysCourseEntity);
    }

    @Override
    public SysCourseEntity findById(SysCourseEntity sysCourseEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysCourseEntity.class);

        List list = criteria.add(
                Restrictions.or(Restrictions.eq("courseId", sysCourseEntity.getCourseId())))
                .setFirstResult(0)
                .setMaxResults(1).list();

        session.close();

        return list!=null&&list.size()>0? (SysCourseEntity) list.get(0) :null;

    }

    @Override
    public PageBean findByPage(String key, PageBean<SysCourseEntity> pageBean) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysCollegeEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("courseCode", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("courseName", key, MatchMode.ANYWHERE))))
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
        List<SysCollegeEntity> list = new ArrayList<>();

        for (String id : Ids) {
            SysCollegeEntity collegesEntity = new SysCollegeEntity();
            collegesEntity.setCollegeId(Integer.parseInt(id));
            list.add(collegesEntity);
        }
        hibernateTemplate.deleteAll(list);
    }
}
