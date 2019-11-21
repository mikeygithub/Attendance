package com.mikey.attendance.vo;

import java.io.Serializable;

/**
 * @ProjectName Attendance
 * @Author 麦奇
 * @Email biaogejiushibiao@outlook.com
 * @Date 11/21/19 10:57 AM
 * @Version 1.0
 * @Description: 用于课程添加的时候所需要的JSON数据格式
 **/

public class ClassesTransferVo implements Serializable {

    private String value;
    private String title;
    private Boolean disabled;
    private Boolean checked;

    public String getValue() {
        return value;
    }

    public ClassesTransferVo setValue(String value) {
        this.value = value;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ClassesTransferVo setTitle(String title) {
        this.title = title;
        return this;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public ClassesTransferVo setDisabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public Boolean getChecked() {
        return checked;
    }

    public ClassesTransferVo setChecked(Boolean checked) {
        this.checked = checked;
        return this;
    }
}
