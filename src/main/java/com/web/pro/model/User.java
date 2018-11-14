package com.web.pro.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String address;

    private String phoneNumber;

    @NotNull
    private Integer role;

    private Date createTime;

    private String apartment;

    public User(@NotNull String username, @NotNull String password, String address, String phoneNumber, @NotNull Integer role, Date createTime, String apartment) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.createTime = createTime;
        this.apartment = apartment;
    }
}