package com.jincong.springboot.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 用户查询VO
 *
 * @author j_cong
 * @version V1.0
 * @date 2019/08/21
 */
@Data
@ToString
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
