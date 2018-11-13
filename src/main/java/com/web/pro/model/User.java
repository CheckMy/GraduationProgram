package com.web.pro.model;

import lombok.Data;

import java.util.Date;

/**
 * @author xuweizhi
 * @date 2018/11/13 17:29
 */
@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private String address;

    private String phoneNumber;

    private Integer role;

    private Date createTime;

    private String apartment;

}
