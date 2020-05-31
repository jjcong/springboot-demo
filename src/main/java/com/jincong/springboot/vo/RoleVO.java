package com.jincong.springboot.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RoleVO {

    private Integer id;


    private Integer roleId;

    private String roleName;

    private String remark;

    private UserVO userVO;
}
