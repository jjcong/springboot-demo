package com.jincong.springboot.vo;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @NotBlank(message="用户名不能为空")
    @Size(min = 1,max = 10,message = "姓名长度必须为1到10")
    private String userName;

    /**
     * 用户密码
     */
    @NotBlank(message="用户密码不能为空")
    @Length(min = 6, max = 8, message = "密码长度为6-8位。")
    @Pattern(regexp = "[a-zA-Z]*", message = "密码不合法")
    private String password;

    /**
     * 备注
     */
    private String remark;
}
