package com.jincong.springboot.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * t_goods_category
 * @author 
 */
@Data
public class GoodsCategory implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父分类id
     */
    private Integer parentId;

    /**
     * 层级
     */
    private Integer catLevel;

    /**
     * 状态
     */
    private String sts;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;

    private static final long serialVersionUID = 1L;
}