package com.mikey.attendance.dao.attendance.impl;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.attendance.AttendanceDao;
import com.mikey.attendance.model.BizAttendanceEntity;
import com.mikey.attendance.model.SysCollegeEntity;
import com.mikey.attendance.model.SysStudentEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:32
 * @Describe：
 **/
@Component
public class AttendanceDaoImpl implements AttendanceDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(BizAttendanceEntity bizAttendanceEntity) {
        sessionFactory.getCurrentSession().save(bizAttendanceEntity);
    }

    @Override
    public void delete(BizAttendanceEntity bizAttendanceEntity) {
        sessionFactory.getCurrentSession().delete(bizAttendanceEntity);
    }

    @Override
    public void update(BizAttendanceEntity bizAttendanceEntity) {
        sessionFactory.getCurrentSession().update(bizAttendanceEntity);
    }

    @Override
    public BizAttendanceEntity findById(BizAttendanceEntity bizAttendanceEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysCollegeEntity.class);

        List list = criteria.add(
                Restrictions.or(Restrictions.eq("collegesId", bizAttendanceEntity.getAttendanceId())))
                .setFirstResult(0)
                .setMaxResults(1).list();

        session.close();

        return list!=null&&list.size()>0? (BizAttendanceEntity) list.get(0) :null;

    }

    @Override
    public PageBean findByPage(String key, PageBean<BizAttendanceEntity> pageBean) {

        ArrayList<BizAttendanceEntity> resultList = new ArrayList<>();

        List<BizAttendanceEntity> list = new ArrayList<>();

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(BizAttendanceEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("attendance_type",key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("attendance_time", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).list();
        } else {
            list = criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults((pageBean.getCurrPage() - 1) * pageBean.getPageSize() + pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        }
        //查询对应的学生
        list.forEach(v->{
            v.setSysStudentEntity((SysStudentEntity) sessionFactory.getCurrentSession().get(SysStudentEntity.class,v.getAttendanceStuId()));
            resultList.add(v);
        });
        pageBean.setRows(resultList);
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

    @Override
    public void saveByBatch(ArrayList<BizAttendanceEntity> bizAttendanceEntities) {
        bizAttendanceEntities.forEach(v->{
            v.setAttendanceTime(new Timestamp(System.currentTimeMillis()));
            sessionFactory.getCurrentSession().save(v);
        });
    }
}
