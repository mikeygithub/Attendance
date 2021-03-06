package com.mikey.attendance.dao.classes;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.model.SysClassesEntity;
import com.mikey.attendance.vo.R;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:09
 * @Describe：
 **/
public interface ClassesDao {
    /**
     * 添加
     */
    public void save(SysClassesEntity classesEntity,String studentIds);

    /**
     * 删除
     */
    public void delete(SysClassesEntity classesEntity);

    /**
     * 修改
     */
    public void update(SysClassesEntity classesEntity, String stuIds);

    /**
     * 查询
     *
     * @return
     */
    public SysClassesEntity findById(SysClassesEntity classesEntity);

    /**
     * 查询
     *
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<SysClassesEntity> pageBean);

    /**
     * 批量删除
     *
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

    R getClasses(Integer courseId);

    R getClassesByCourseId(Integer courseId);
}
