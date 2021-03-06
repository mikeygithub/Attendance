package com.mikey.attendance.dao.course;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.model.SysCourseEntity;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:09
 * @Describe：
 **/
public interface CourseDao {
    /**
     * 添加
     */
    public void save(SysCourseEntity sysCourseEntity,String courseClass);

    /**
     * 删除
     */
    public void delete(SysCourseEntity sysCourseEntity);

    /**
     * 修改
     */
    public void update(SysCourseEntity sysCourseEntity, String courseClasses);

    /**
     * 查询
     *
     * @return
     */
    public SysCourseEntity findById(SysCourseEntity sysCourseEntity);

    /**
     * 查询
     *
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key,Integer teacherId, PageBean<SysCourseEntity> pageBean);

    /**
     * 批量删除
     *
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
