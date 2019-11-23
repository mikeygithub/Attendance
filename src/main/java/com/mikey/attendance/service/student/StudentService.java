package com.mikey.attendance.service.student;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.model.SysStudentEntity;
import com.mikey.attendance.vo.PageViewTransferVo;

import java.util.List;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-04 00:09
 * @Describe：
 **/
public interface StudentService {
    /**
     * 添加
     */
    public void save(SysStudentEntity studentEntity,Integer classId);

    /**
     * 删除
     */
    public void delete(SysStudentEntity studentEntity);

    /**
     * 修改
     */
    public void update(SysStudentEntity studentEntity);

    /**
     * 查询
     *
     * @return
     */
    public SysStudentEntity findById(SysStudentEntity studentEntity);

    /**
     * 查询
     *
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<SysStudentEntity> pageBean);

    /**
     * 批量删除
     *
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

    PageBean findByClassIdWithPage(Integer classId, Integer attendanceType, Integer number);

    List<PageViewTransferVo> getStudentByClassId(Integer classId);
}
