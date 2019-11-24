package com.mikey.attendance.action.teacher;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.model.SysTeacherEntity;
import com.mikey.attendance.service.teacher.TeacherService;
import com.mikey.attendance.vo.R;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-05 09:24
 * @Describe：
 **/
public class TeacherAction extends ActionSupport implements ModelDriven<SysTeacherEntity> {

    @Autowired
    private TeacherService teacherService;
    //日志
    private static Logger logger = Logger.getLogger(SysTeacherEntity.class);
    //模型驱动
    private SysTeacherEntity sysTeacherEntity = new SysTeacherEntity();
    //
    private PageBean<SysTeacherEntity> pageBean = new PageBean<>();
    //返回集
    private R r = new R();
    //搜索值
    private String key;
    //当前页
    private Integer page;
    //大小
    private Integer limit;
    //批量删除id
    private String ids;
    //班级id
    private Integer classId;
    //sex
    private Integer teacherSex;

    /**
     * 添加
     */
    public String save() {

        teacherService.save(sysTeacherEntity,teacherSex);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        teacherService.delete(sysTeacherEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        teacherService.update(sysTeacherEntity, teacherSex);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {

        SysTeacherEntity byId = teacherService.findById(sysTeacherEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = teacherService.findByPage(key, new PageBean<SysTeacherEntity>().setCurrPage(page).setPageSize(limit));


        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {


        String[] id = ids.split(",");


        teacherService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////


    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public SysTeacherEntity getSysTeacherEntity() {
        return sysTeacherEntity;
    }

    public void setSysTeacherEntity(SysTeacherEntity sysTeacherEntity) {
        this.sysTeacherEntity = sysTeacherEntity;
    }

    public PageBean<SysTeacherEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<SysTeacherEntity> pageBean) {
        this.pageBean = pageBean;
    }

    public Integer getTeacherSex() {
        return teacherSex;
    }

    public void setTeacherSex(Integer teacherSex) {
        this.teacherSex = teacherSex;
    }

    @Override
    public SysTeacherEntity getModel() {
        return sysTeacherEntity;
    }
}
