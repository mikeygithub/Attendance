package com.mikey.attendance.util;

/**
 * @ProjectName Attendance
 * @Author 麦奇
 * @Email biaogejiushibiao@outlook.com
 * @Date 11/21/19 9:35 AM
 * @Version 1.0
 * @Description:系统常量
 * **/

public class SysConstant {

    //全部抽取
    public static final Integer EXTRACT_TYPE_ALL = 0;
    //随机抽取
    public static final Integer EXTRACT_TYPE_RANDOM = 1;
    /**
     * 缺勤类型
     * "0" title="正常" checked="checked">
     * "1" title="迟到">
     * "2" title="早退">
     * "3" title="请假">
     * "4" title="旷课">
     */
    public static final Integer ATTENDANCE_TYPE_NORMAL = 0;
    public static final Integer ATTENDANCE_TYPE_LATER = 1;
    public static final Integer ATTENDANCE_TYPE_LEAVE_EARLY = 2;
    public static final Integer ATTENDANCE_TYPE_LEAVE = 3;
    public static final Integer ATTENDANCE_TYPE_TRUANCY = 4;

    /**
     * 默认登入密码
     */
    public static final String DEFAULT_PASSWORD = "123456";
    /**
     * 用户类型1：管理员,2:教师,3：学生
     */
    public static final Integer USER_TYPE_STUDENT = 3;
    public static final Integer USER_TYPE_TEACHER = 2;
    public static final Integer USER_TYPE_ADMIN = 1;

    public static final Integer SEX_MAN = 1;
    public static final Integer SEX_WOMAN = 2;
}
