package com.jincong.springboot.vo;

import lombok.Data;

@Data
public class QueryUserVO {
    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 备注
     */
    private String remark;
}
