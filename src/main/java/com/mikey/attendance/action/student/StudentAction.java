package com.mikey.attendance.action.student;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.model.SysStudentEntity;
import com.mikey.attendance.service.student.StudentService;
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
public class StudentAction extends ActionSupport implements ModelDriven<SysStudentEntity> {

    @Autowired
    private StudentService studentService;
    //日志
    private static Logger logger = Logger.getLogger(SysStudentEntity.class);
    //模型驱动
    private SysStudentEntity studentEntity = new SysStudentEntity();
    //
    private PageBean<SysStudentEntity> pageBean = new PageBean<>();
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
    //抽取类型
    private Integer attendanceType;
    //抽取数量
    private Integer number;
    //sex
    private Integer studentSex;
    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        studentService.save(studentEntity,studentSex);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        studentService.delete(studentEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        studentService.update(studentEntity, studentSex);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {

        SysStudentEntity byId = studentService.findById(studentEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = studentService.findByPage(key, new PageBean<SysStudentEntity>().setCurrPage(page).setPageSize(limit));


        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 抽取学生
     * @return
     */
    public String findByClassIdWithPage() {

        PageBean byPage = studentService.findByClassIdWithPage(classId,attendanceType,number);

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }
    /**
     * 批量删除
     */
    public String deleteBatch() {


        String[] id = ids.split(",");


        studentService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 获取数据给班级穿梭框
     * @return
     */
    public String getStudentByClassId(){

        r = studentService.getStudentByClassId(classId);

        return SUCCESS;
    }
    /////////////////////////////////////////


    public SysStudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(SysStudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public PageBean<SysStudentEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<SysStudentEntity> pageBean) {
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

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(Integer attendanceType) {
        this.attendanceType = attendanceType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(Integer studentSex) {
        this.studentSex = studentSex;
    }

    @Override
    public SysStudentEntity getModel() {
        return studentEntity;
    }
}
