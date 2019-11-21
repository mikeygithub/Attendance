package com.mikey.attendance.action.classes;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.model.SysClassesEntity;
import com.mikey.attendance.service.classes.ClassesService;
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
public class ClassesAction extends ActionSupport implements ModelDriven<SysClassesEntity> {

    @Autowired
    private ClassesService classesService;
    //日志
    private static Logger logger = Logger.getLogger(SysClassesEntity.class);
    //模型驱动
    private SysClassesEntity classesEntity = new SysClassesEntity();
    //
    private PageBean<SysClassesEntity> pageBean = new PageBean<>();
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
    //课程id
    private String courseId;

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        classesService.save(classesEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        classesService.delete(classesEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        classesService.update(classesEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {


        SysClassesEntity byId = classesService.findById(classesEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = classesService.findByPage(key, new PageBean<SysClassesEntity>().setCurrPage(page).setPageSize(limit));


        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {


        String[] id = ids.split(",");


        classesService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 获取班级信息提供给课程的添加
     * @return
     */
    public String getClasses(){

        r = classesService.getClasses(courseId);

        return SUCCESS;
    }

    /////////////////////////////////////////


    public SysClassesEntity getClassesEntity() {
        return classesEntity;
    }

    public void setClassesEntity(SysClassesEntity classesEntity) {
        this.classesEntity = classesEntity;
    }

    public PageBean<SysClassesEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<SysClassesEntity> pageBean) {
        this.pageBean = pageBean;
    }

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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public SysClassesEntity getModel() {
        return classesEntity;
    }
}
