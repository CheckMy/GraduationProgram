package com.graduation.design.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xuweizhi
 * @date 2018/11/16 13:50
 */
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uId;

    /**
     *用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户住址
     */
    private String address;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 角色权限
     */
    private Integer role;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 部门
     */
    private String apartment;

}
