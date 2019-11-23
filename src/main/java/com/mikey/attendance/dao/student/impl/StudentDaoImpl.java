package com.mikey.attendance.dao.student.impl;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.student.StudentDao;
import com.mikey.attendance.model.BizStuOfClaEntity;
import com.mikey.attendance.model.SysStudentEntity;
import com.mikey.attendance.util.SysConstant;
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
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(SysStudentEntity studentEntity,Integer classId) {
        Integer save = (Integer) sessionFactory.getCurrentSession().save(studentEntity);
        BizStuOfClaEntity bizStuOfClaEntity = new BizStuOfClaEntity();
        bizStuOfClaEntity.setClaId(classId);
        bizStuOfClaEntity.setStuId(save);
        sessionFactory.getCurrentSession().save(bizStuOfClaEntity);
    }

    @Override
    public void delete(SysStudentEntity studentEntity) {
        sessionFactory.getCurrentSession().delete(studentEntity);
    }

    @Override
    public void update(SysStudentEntity studentEntity) {
        sessionFactory.getCurrentSession().update(studentEntity);
    }

    @Override
    public SysStudentEntity findById(SysStudentEntity studentEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysStudentEntity.class);

        List list = criteria.add(
                Restrictions.or(Restrictions.eq("studentId", studentEntity.getStudentId()
                )))
                .setFirstResult(0)
                .setMaxResults(1).list();

        session.close();

        return list!=null&&list.size()>0? (SysStudentEntity) list.get(0) :null;

    }

    @Override
    public PageBean findByPage(String key, PageBean<SysStudentEntity> pageBean) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysStudentEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("studentCode", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("studentName", key, MatchMode.ANYWHERE))))
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
        List<SysStudentEntity> list = new ArrayList<>();

        for (String id : Ids) {

            SysStudentEntity studentEntity = new SysStudentEntity();
            studentEntity.setStudentId(Integer.parseInt(id));
            list.add(studentEntity);

        }
        hibernateTemplate.deleteAll(list);
    }

    /**
     * 获取全部学生
     * @param classId
     * @param attendanceType
     * @param number
     * @return
     */
    @Override
    public PageBean findByClassIdWithPage(Integer classId, Integer attendanceType, Integer number) {
        //班级id为classId的所有学生
        List<SysStudentEntity> allStu = new ArrayList<>();

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(BizStuOfClaEntity.class);

        List<BizStuOfClaEntity> claOfList = criteria.add(Restrictions.eq("claId", classId)).list();

        claOfList.forEach(v->{
            allStu.add((SysStudentEntity) sessionFactory.getCurrentSession().get(SysStudentEntity.class,v.getStuId()));
        });

        PageBean<SysStudentEntity> pageBean = new PageBean<>();
        //随机抽取
        if (SysConstant.EXTRACT_TYPE_RANDOM.equals(attendanceType)&&number!=allStu.size()){
            List<SysStudentEntity> extract = extract(allStu, number);
            pageBean.setRows(extract).setTotal(extract.size());
        }else {//全部抽取
            pageBean.setRows(allStu).setTotal(allStu.size());
        }
        return pageBean;
    }

    /**
     * 随机抽取
     * @param allStu
     * @param number
     * @return
     */
    private List<SysStudentEntity> extract(List<SysStudentEntity> allStu, Integer number){
        //返回结果
        List<SysStudentEntity> studentList = new ArrayList<>();
        //抽取
        for (int i = 0; i < (number>allStu.size()?allStu.size():number); i++) {
            //获取0-allStu.length的随机数
            int random = (int)(Math.random()*allStu.size());
            //添加学生,随机获取下标为random的学生并且把它添加到要传回页面的list里面
            studentList.add(allStu.get(random));
            //将本次已经抽取过的学生从预抽取的list里面移除
            allStu.remove(random);
        }
        return studentList;
    }
}
