package com.jincong.springboot.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class RoleVO implements Serializable {

    private static final long serialVersionUID = -1539467743333821805L;
    private Integer id;


    private Integer roleId;

    private String roleName;

    private String remark;

    private String userCode;

}
