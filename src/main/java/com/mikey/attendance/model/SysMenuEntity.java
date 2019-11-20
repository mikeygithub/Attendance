package com.mikey.attendance.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName hibernate_generate
 * @Author 麦奇
 * @Email biaogejiushibiao@outlook.com
 * @Date 11/20/19 11:37 AM
 * @Version 1.0
 * @Description:
 **/

@Entity
@Table(name = "sys_menu", schema = "attendance", catalog = "")
public class SysMenuEntity {
    private int id;
    private String title;
    private String icon;
    private String href;
    private Byte spread;
    private String roleType;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "href")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Basic
    @Column(name = "spread")
    public Byte getSpread() {
        return spread;
    }

    public void setSpread(Byte spread) {
        this.spread = spread;
    }

    @Basic
    @Column(name = "roleType")
    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysMenuEntity that = (SysMenuEntity) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(href, that.href) &&
                Objects.equals(spread, that.spread) &&
                Objects.equals(roleType, that.roleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, icon, href, spread, roleType);
    }
}
