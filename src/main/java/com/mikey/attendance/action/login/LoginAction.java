package com.mikey.attendance.action.login;

import com.mikey.attendance.model.SysUserEntity;
import com.mikey.attendance.service.login.LoginService;
import com.mikey.attendance.vo.R;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Program: YoungVolunteer
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-06-01 23:11
 * @Describe：
 **/
public class LoginAction extends ActionSupport implements ModelDriven<SysUserEntity> {

    private static Logger logger = Logger.getLogger(SysUserEntity.class);

    @Autowired
    private LoginService loginService;

    private SysUserEntity sysUserEntity = new SysUserEntity();

    private String newPassword;

    private R r = new R();

    /**
     * 用户登入
     * @return
     */
    public String userLogin(){

        logger.info("message:用户登入:"+sysUserEntity);

        r = loginService.userLogin(sysUserEntity);

        logger.info(r);

        return SUCCESS;
    }

    /**
     * 修改密码
     * @return
     */
    public String updatePassword(){

        logger.info("修改密码："+sysUserEntity+"\t"+newPassword);

        r = loginService.updatePassword(sysUserEntity,newPassword);

        return SUCCESS;
    }

    /**
     * 退出登入
     * @return
     */
    public String logout(){

        System.out.println("用户退出");

        return SUCCESS;
    }



    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    @Override
    public SysUserEntity getModel() {
        return sysUserEntity;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
