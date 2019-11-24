package com.mikey.attendance.service.teacher;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.model.SysCollegeEntity;
import com.mikey.attendance.model.SysTeacherEntity;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:09
 * @Describe：
 **/
public interface TeacherService {
    /**
     * 添加
     */

    void save(SysTeacherEntity sysTeacherEntity, Integer sex);

    /**
     * 删除
     */
    public void delete(SysTeacherEntity sysTeacherEntity);

    /**
     * 修改
     */
    public void update(SysTeacherEntity sysTeacherEntity);

    /**
     * 查询
     *
     * @return
     */
    public SysTeacherEntity findById(SysTeacherEntity sysTeacherEntity);

    /**
     * 查询
     *
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<SysTeacherEntity> pageBean);

    /**
     * 批量删除
     *
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
