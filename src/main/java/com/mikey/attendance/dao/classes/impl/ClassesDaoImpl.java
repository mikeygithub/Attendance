package com.mikey.attendance.dao.classes.impl;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.dao.classes.ClassesDao;
import com.mikey.attendance.model.BizCouOfClaEntity;
import com.mikey.attendance.model.BizStuOfClaEntity;
import com.mikey.attendance.model.SysClassesEntity;
import com.mikey.attendance.vo.PageViewTransferVo;
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

import java.util.*;

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
    public void save(SysClassesEntity classesEntity,String studentIds) {
        Integer save = (Integer) sessionFactory.getCurrentSession().save(classesEntity);
        //保存中间表
        String[] split = studentIds.split(",");
        Arrays.stream(split).forEach(v->{
                BizStuOfClaEntity boc = new BizStuOfClaEntity();
                boc.setStuId(Integer.parseInt(v));
                boc.setClaId(save);
                sessionFactory.getCurrentSession().save(boc);
        });
    }

    @Override
    public void delete(SysClassesEntity classesEntity) {
        sessionFactory.getCurrentSession().delete(classesEntity);
        //删除中间表
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BizStuOfClaEntity.class);
        criteria.add(Restrictions.eq("claId",classesEntity.getClassesId())).list().forEach(v->{
            sessionFactory.getCurrentSession().delete(v);
        });
    }

    /**
     * 更新
     * @param classesEntity
     * @param stuIds
     */
    @Override
    public void update(SysClassesEntity classesEntity, String stuIds) {
        sessionFactory.getCurrentSession().update(classesEntity);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BizStuOfClaEntity.class);
        criteria.add(Restrictions.eq("claId",classesEntity.getClassesId())).list().forEach(v->{
            sessionFactory.getCurrentSession().delete(v);
        });
        String[] split = stuIds.split(",");
        Arrays.stream(split).forEach(v->{
            BizStuOfClaEntity boc = new BizStuOfClaEntity();
            boc.setStuId(Integer.parseInt(v));
            boc.setClaId(classesEntity.getClassesId());
            sessionFactory.getCurrentSession().save(boc);
        });
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

        List<PageViewTransferVo> allClass = new ArrayList<>();
        //已经选择的class
        List<Integer> selectClass = new ArrayList<>();
        //all
        criteria.list().forEach(v->{
            allClass.add(new PageViewTransferVo().setValue(String.valueOf(((SysClassesEntity)v).getClassesId())).setTitle(((SysClassesEntity)v).getClassesName()).setChecked(false).setDisabled(false));
        });

        session.createCriteria(BizCouOfClaEntity.class)
                .add(Restrictions.eq("courseId", courseId)).list()
                .forEach(v->{
                selectClass.add(((BizCouOfClaEntity)v).getClassesId());
                });


        session.close();

        return R.ok().put("classes",allClass).put("select",selectClass);
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
