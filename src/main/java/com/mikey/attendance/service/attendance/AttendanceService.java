package com.mikey.attendance.service.attendance;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.model.BizAttendanceEntity;

import java.util.ArrayList;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:09
 * @Describe：
 **/
public interface AttendanceService {
    /**
     * 添加
     */
    public void save(BizAttendanceEntity bizAttendanceEntity);

    /**
     * 删除
     */
    public void delete(BizAttendanceEntity bizAttendanceEntity);

    /**
     * 修改
     */
    public void update(BizAttendanceEntity bizAttendanceEntity);

    /**
     * 查询
     *
     * @return
     */
    public BizAttendanceEntity findById(BizAttendanceEntity bizAttendanceEntity);

    /**
     * 查询
     *
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<BizAttendanceEntity> pageBean);

    /**
     * 批量删除
     *
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

    void saveByBatch(ArrayList<BizAttendanceEntity> bizAttendanceEntities);
}
