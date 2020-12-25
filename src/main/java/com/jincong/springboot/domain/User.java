package com.jincong.springboot.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.jincong.springboot.config.SexEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "t_user")
public class User {

    // @Id表示该字段对应数据库表的主键id
    // @GeneratedValue中strategy表示使用数据库自带的主键生成策略.
    // @GeneratedValue中generator配置为"JDBC",在数据插入完毕之后,会自动将主键id填充到实体类中.类似普通mapper.xml中配置的selectKey标签
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    @Column(name = "user_code")
    private String userCode;

    private String userName;

    private String password;

    private SexEnum sex;

    private String remark;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    // 字段添加填充内容
    private Date lastUpdateTime;
}
