package com.mikey.attendance.action.attendance;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.model.BizAttendanceEntity;
import com.mikey.attendance.service.attendance.AttendanceService;
import com.mikey.attendance.vo.R;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-05 09:24
 * @Describe：
 **/
public class AttendanceAction extends ActionSupport implements ModelDriven<BizAttendanceEntity> {

    @Autowired
    private AttendanceService attendanceService;
    //日志
    private static Logger logger = Logger.getLogger(BizAttendanceEntity.class);
    //模型驱动
    private BizAttendanceEntity bizAttendanceEntity = new BizAttendanceEntity();
    //
    private PageBean<BizAttendanceEntity> pageBean = new PageBean<>();
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
    /**
     * 批量接收要保存的数据
     */
//    private ArrayList<BizAttendanceEntity> bizAttendanceEntities = new ArrayList<>();
    private String bizAttendanceEntities;


    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        attendanceService.save(bizAttendanceEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 批量保存
     * @return
     */
    public String saveByBatch() throws IOException {

        final ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);

        List<BizAttendanceEntity> list = mapper.readValue(bizAttendanceEntities, new TypeReference<List<BizAttendanceEntity>>(){});

        attendanceService.saveByBatch((ArrayList<BizAttendanceEntity>) list);

        r = R.ok();

        return SUCCESS;
    }
    /**
     * 删除
     */
    public String delete() {

        attendanceService.delete(bizAttendanceEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        attendanceService.update(bizAttendanceEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {

        BizAttendanceEntity byId = attendanceService.findById(bizAttendanceEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = attendanceService.findByPage(key, new PageBean<BizAttendanceEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {


        String[] id = ids.split(",");


        attendanceService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    @Override
    public BizAttendanceEntity getModel() {
        return bizAttendanceEntity;
    }

    public BizAttendanceEntity getBizAttendanceEntity() {
        return bizAttendanceEntity;
    }

    public void setBizAttendanceEntity(BizAttendanceEntity bizAttendanceEntity) {
        this.bizAttendanceEntity = bizAttendanceEntity;
    }

    public void setPageBean(PageBean<BizAttendanceEntity> pageBean) {
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

    public String getBizAttendanceEntities() {
        return bizAttendanceEntities;
    }

    public void setBizAttendanceEntities(String bizAttendanceEntities) {
        this.bizAttendanceEntities = bizAttendanceEntities;
    }
}
