package com.jincong.springboot.vo;

import lombok.Data;

/**
 * 用户查询VO
 *
 * @author  j_cong
 * @date    2019/08/21
 * @version V1.0
 */
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
