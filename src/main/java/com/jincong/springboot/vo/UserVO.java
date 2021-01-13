package com.jincong.springboot.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户查询VO
 *
 * @author j_cong
 * @version V1.0
 * @date 2019/08/21
 */
@Data
@ToString
public class UserVO implements Serializable {

    private static final long serialVersionUID = 7015590617564929735L;

    private Integer id;

    private String userCode;
    /**
     * 用户名称
     */
    private String userName;

    private String phone;


    private String sex;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 备注
     */
    private String remark;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;

    private List<RoleVO> roleList;
}
