package com.mikey.attendance.action.colleges;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.model.SysCollegeEntity;
import com.mikey.attendance.service.colleges.CollegesService;
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
public class CollegesAction extends ActionSupport implements ModelDriven<SysCollegeEntity> {

    @Autowired
    private CollegesService collegesService;
    //日志
    private static Logger logger = Logger.getLogger(SysCollegeEntity.class);
    //模型驱动
    private SysCollegeEntity collegesEntity = new SysCollegeEntity();
    //
    private PageBean<SysCollegeEntity> pageBean = new PageBean<>();
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

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        collegesService.save(collegesEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        collegesService.delete(collegesEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        collegesService.update(collegesEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {

        SysCollegeEntity byId = collegesService.findById(collegesEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = collegesService.findByPage(key, new PageBean<SysCollegeEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {


        String[] id = ids.split(",");


        collegesService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    @Override
    public SysCollegeEntity getModel() {
        return collegesEntity;
    }

    public SysCollegeEntity getCollegesEntity() {
        return collegesEntity;
    }

    public void setCollegesEntity(SysCollegeEntity collegesEntity) {
        this.collegesEntity = collegesEntity;
    }

    public PageBean<SysCollegeEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<SysCollegeEntity> pageBean) {
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
/////////////////////////////////////////

}
