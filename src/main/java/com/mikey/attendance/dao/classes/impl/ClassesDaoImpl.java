package com.mikey.attendance.dao.classes.impl;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.classes.ClassesDao;
import com.mikey.attendance.model.BizCouOfClaEntity;
import com.mikey.attendance.model.SysClassesEntity;
import com.mikey.attendance.vo.ClassesTransferVo;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    /**
     * 新增查询全部
     * 携带课程id查询已选和未选
     * @param courseId
     * @return
     */
    @Override
    public R getClasses(Integer courseId) {

        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(SysClassesEntity.class);

        List<ClassesTransferVo> list = new ArrayList<>();
        //result
        List<SysClassesEntity> allClass = new ArrayList<>();
        //已经选择的class
        List<SysClassesEntity> selectClass = new ArrayList<>();
        //all
        Set<ClassesTransferVo> resultClass = new HashSet<>();


        if (courseId==null||courseId.toString()=="") {
            allClass=criteria.list();
        }else {
            allClass=criteria.list();
            Criteria crt = session.createCriteria(BizCouOfClaEntity.class);
            List<BizCouOfClaEntity> cou_of_cla = crt.add(Restrictions.eq("courseId", courseId)).list();

            for(BizCouOfClaEntity bizCouOfClaEntity:cou_of_cla){
                //查询班级
                SysClassesEntity sysClassesEntity = hibernateTemplate.get(SysClassesEntity.class, bizCouOfClaEntity.getClassesId());
                selectClass.add(sysClassesEntity);
                //将总的移除
                allClass.remove(sysClassesEntity);
            }

        }
        for(SysClassesEntity s:selectClass){
            resultClass.add(new ClassesTransferVo().setValue(s.getClassesId()+"").setTitle(s.getClassesName()).setChecked(false).setDisabled(false));
        }
        for(SysClassesEntity s:allClass){
            resultClass.add(new ClassesTransferVo().setValue(s.getClassesId()+"").setTitle(s.getClassesName()).setChecked(true).setDisabled(false));
        }

        session.close();

        return R.ok().put("classes",resultClass);
    }

    /**
     * 通过课程获取班级
     * @param courseId
     * @return
     */
    @Override
    public R getClassesByCourseId(Integer courseId) {

        Session session = sessionFactory.openSession();

        //result
        List<SysClassesEntity> allClass = new ArrayList<>();
            Criteria crt = session.createCriteria(BizCouOfClaEntity.class);
            List<BizCouOfClaEntity> cou_of_cla = crt.add(Restrictions.eq("courseId", courseId)).list();

            for(BizCouOfClaEntity bizCouOfClaEntity:cou_of_cla){
                //查询班级
                SysClassesEntity sysClassesEntity = hibernateTemplate.get(SysClassesEntity.class, bizCouOfClaEntity.getClassesId());
                allClass.add(sysClassesEntity);
           }

        session.close();

        return R.ok().put("classes",allClass);
    }
}
