package com.graduation.design.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author xuweizhi
 * @date 2018/11/16 13:55
 */
@Entity
@Data
public class NavItem {

    /**
     * 左侧导航栏Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    /**
     * 导航栏名称
     */
    private String navName;

    /**
     * 链接地址
     */
    private String link;

    /**
     * 链接样式
     */
    private String icon;

    /**
     * 是否被激活
     */
    private Boolean isActive;


}
