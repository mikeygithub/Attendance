package com.mikey.attendance.dao.attendance;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.model.BusAttendanceEntity;
import com.mikey.attendance.model.SysCollegeEntity;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:09
 * @Describe：
 **/
public interface AttendanceDao {
    /**
     * 添加
     */
    public void save(BusAttendanceEntity busAttendanceEntity);

    /**
     * 删除
     */
    public void delete(BusAttendanceEntity busAttendanceEntity);

    /**
     * 修改
     */
    public void update(BusAttendanceEntity busAttendanceEntity);

    /**
     * 查询
     *
     * @return
     */
    public BusAttendanceEntity findById(BusAttendanceEntity busAttendanceEntity);

    /**
     * 查询
     *
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<BusAttendanceEntity> pageBean);

    /**
     * 批量删除
     *
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
