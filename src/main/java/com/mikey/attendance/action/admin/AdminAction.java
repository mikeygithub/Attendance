package com.mikey.attendance.action.admin;

import com.mikey.attendance.model.SysAdminEntity;
import com.mikey.attendance.service.admin.AdminService;
import com.mikey.attendance.vo.R;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Program: Ped_Moni
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-04-28 23:53
 * @Describe：
 **/
public class AdminAction extends ActionSupport implements ModelDriven<SysAdminEntity> {

    private static Logger logger = Logger.getLogger(SysAdminEntity.class);

    @Autowired
    private AdminService adminService;

    private SysAdminEntity sysAdminEntity = new SysAdminEntity();

    private String oldPassword;

    private R r = new R();

    @Override
    public SysAdminEntity getModel() {
        return this.sysAdminEntity;
    }

    public String addAdmin(){

        logger.info("添加管理员");

        adminService.saveAdmin(sysAdminEntity);

        r = R.ok();

        return SUCCESS;
    }
    /**
     * 单个查询
     * @return
     */
    public String selectOneAdmin(){

        SysAdminEntity sysAdminEntity1 = adminService.findOneAdmin(sysAdminEntity);

        System.out.println(sysAdminEntity1);

        return SUCCESS;
    }

    public SysAdminEntity getSysAdminEntity() {
        return sysAdminEntity;
    }

    public void setSysAdminEntity(SysAdminEntity sysAdminEntity) {
        this.sysAdminEntity = sysAdminEntity;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
