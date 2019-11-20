package com.mikey.attendance.action.menu;

import com.mikey.attendance.common.PageBean;
import com.mikey.attendance.model.SysMenuEntity;
import com.mikey.attendance.service.menu.MenuService;
import com.mikey.attendance.vo.R;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-05 09:24
 * @Describe：
 **/
public class MenuAction extends ActionSupport implements ModelDriven<SysMenuEntity> {

    @Autowired
    private MenuService menuService;
    //日志
    private static Logger logger = Logger.getLogger(SysMenuEntity.class);
    //模型驱动
    private SysMenuEntity sysMenuEntity = new SysMenuEntity();
    //
    private PageBean<SysMenuEntity> pageBean = new PageBean<>();
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

    private String roleTypes;

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        menuService.save(sysMenuEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        menuService.delete(sysMenuEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        menuService.update(sysMenuEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {


//        menuService.findById(sysMenuEntity);

//        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = menuService.findByPage(key, new PageBean<SysMenuEntity>().setCurrPage(page).setPageSize(limit));


        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {


        String[] id = ids.split(",");


        menuService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 获取菜单
     *
     * @return
     */
    public String getMenuByRoleType() {

        logger.info("roleType：" + roleTypes);

        List<SysMenuEntity> menuByRoleType = menuService.getMenuByRoleType(roleTypes);

        r = R.ok().put("contentManagement", menuByRoleType);

        logger.info("菜单信息：" + r);

        return SUCCESS;

    }


    /////////////////////////////////////////


    public SysMenuEntity getSysMenuEntity() {
        return sysMenuEntity;
    }

    public void setSysMenuEntity(SysMenuEntity sysMenuEntity) {
        this.sysMenuEntity = sysMenuEntity;
    }

    public PageBean<SysMenuEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<SysMenuEntity> pageBean) {
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

    @Override
    public SysMenuEntity getModel() {
        return sysMenuEntity;
    }

    public String getRoleTypes() {
        return roleTypes;
    }

    public void setRoleTypes(String roleTypes) {
        this.roleTypes = roleTypes;
    }
}


