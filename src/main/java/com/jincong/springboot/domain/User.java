package com.jincong.springboot.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int id;

    private int userId;

    private String userName;

    private String password;

    private String remark;

    private Date createTime;

    private Date lastUpdateTime;
}
