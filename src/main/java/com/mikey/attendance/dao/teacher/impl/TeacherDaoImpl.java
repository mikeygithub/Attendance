package com.mikey.attendance.dao.teacher.impl;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.teacher.TeacherDao;
import com.mikey.attendance.model.SysCollegeEntity;
import com.mikey.attendance.model.SysTeacherEntity;
import com.mikey.attendance.model.SysUserEntity;
import com.mikey.attendance.util.SysConstant;
import com.mikey.attendance.vo.R;
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
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(SysTeacherEntity sysTeacherEntity,Integer sex) {
        sessionFactory.getCurrentSession().save(sysTeacherEntity);
        //登入用户
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setLoginAccount(sysTeacherEntity.getTeacherCode());
        sysUserEntity.setLoginPassword(SysConstant.DEFAULT_PASSWORD);
        sysUserEntity.setRoleType(SysConstant.USER_TYPE_TEACHER);
        sysUserEntity.setUserSex(sex);
        Integer save = (Integer) sessionFactory.getCurrentSession().save(sysUserEntity);

        //保存教师
        sysTeacherEntity.setUserId(save);
        sessionFactory.getCurrentSession().save(sysTeacherEntity);
    }

    @Override
    public void delete(SysTeacherEntity sysTeacherEntity) {
        SysTeacherEntity byId = findById(sysTeacherEntity);
        sessionFactory.getCurrentSession().delete(sysTeacherEntity);
        SysUserEntity user = (SysUserEntity) sessionFactory.getCurrentSession().createCriteria(SysUserEntity.class).add(Restrictions.eq("userId", byId.getUserId())).list().get(0);
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void update(SysTeacherEntity sysTeacherEntity) {
        sessionFactory.getCurrentSession().update(sysTeacherEntity);
    }

    @Override
    public SysTeacherEntity findById(SysTeacherEntity sysTeacherEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysTeacherEntity.class);

        List list = criteria.add(
                Restrictions.or(Restrictions.eq("teacherId", sysTeacherEntity.getTeacherId())))
                .setFirstResult(0)
                .setMaxResults(1).list();

        session.close();

        return list!=null&&list.size()>0? (SysTeacherEntity) list.get(0) :null;

    }

    @Override
    public PageBean findByPage(String key, PageBean<SysTeacherEntity> pageBean) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysTeacherEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("teacherName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("teacherCode", key, MatchMode.ANYWHERE))))
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
        List<SysTeacherEntity> list = new ArrayList<>();

        for (String id : Ids) {
            SysTeacherEntity collegesEntity = new SysTeacherEntity();
            collegesEntity.setTeacherId(Integer.parseInt(id));
            list.add(collegesEntity);
        }
        hibernateTemplate.deleteAll(list);
    }
}
