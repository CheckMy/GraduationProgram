package com.graduation.design.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 首页导航栏
 *
 * @author xuweizhi
 * @date 2018/11/16 13:35
 */
@Entity
@Data
public class Nav {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "COMMENT '页头导航栏Id'")
    private Long navId;

    /**
     * 导航栏名称
     */
    private String navName;

    /**
     * 链接地址
     */
    private String link;

    /**
     *链接样式
     */
    private String icon;

    /**
     *是否被激活
     */
    private Boolean isActive;

    /**
     *是显示子类
     */
    private Boolean isShowChild;

    /**
     *创建者
     */
    private User user;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *创建时间
     */
    private Date updateTime;

    private Set<NavItem> navItems;
}
