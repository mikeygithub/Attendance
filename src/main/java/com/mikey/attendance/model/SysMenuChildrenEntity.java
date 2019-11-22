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
@Table(name = "sys_menu_children", schema = "attendance", catalog = "")
public class SysMenuChildrenEntity {
    private int id;
    private String title;
    private String icon;
    private String href;
    private Byte spread;
    private SysMenuEntity fatherMenu;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "icon", nullable = false, length = 255)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "href", nullable = false, length = 255)
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Basic
    @Column(name = "spread", nullable = true)
    public Byte getSpread() {
        return spread;
    }

    public void setSpread(Byte spread) {
        this.spread = spread;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysMenuChildrenEntity that = (SysMenuChildrenEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (href != null ? !href.equals(that.href) : that.href != null) return false;
        if (spread != null ? !spread.equals(that.spread) : that.spread != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (href != null ? href.hashCode() : 0);
        result = 31 * result + (spread != null ? spread.hashCode() : 0);
        return result;
    }

    @ManyToOne
    public SysMenuEntity getFatherMenu() {
        return fatherMenu;
    }

    public void setFatherMenu(SysMenuEntity fatherMenu) {
        this.fatherMenu = fatherMenu;
    }
}
