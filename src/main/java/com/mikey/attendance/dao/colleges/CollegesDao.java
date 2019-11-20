package com.mikey.attendance.dao.colleges;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.model.SysCollegeEntity;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:09
 * @Describe：
 **/
public interface CollegesDao {
    /**
     * 添加
     */
    public void save(SysCollegeEntity collegesEntity);

    /**
     * 删除
     */
    public void delete(SysCollegeEntity collegesEntity);

    /**
     * 修改
     */
    public void update(SysCollegeEntity collegesEntity);

    /**
     * 查询
     *
     * @return
     */
    public SysCollegeEntity findById(SysCollegeEntity collegesEntity);

    /**
     * 查询
     *
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<SysCollegeEntity> pageBean);

    /**
     * 批量删除
     *
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
