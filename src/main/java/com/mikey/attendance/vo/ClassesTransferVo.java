package com.mikey.attendance.vo;

/**
 * @ProjectName Attendance
 * @Author 麦奇
 * @Email biaogejiushibiao@outlook.com
 * @Date 11/21/19 10:57 AM
 * @Version 1.0
 * @Description: 用于课程添加的时候所需要的JSON数据格式
 **/

public class ClassesTransferVo {

    private String value;
    private String title;
    private Boolean disabled;
    private Boolean checked;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
