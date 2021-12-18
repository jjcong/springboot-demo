package com.jincong.springboot.rpc.config;

import lombok.Data;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/12/18
 */
@Data
public class ProviderConfig {

    /**
     * 接口
     */
    protected String nozzle;

    /**
     * 映射
     */
    protected String ref;

    /**
     * 别名
     */
    protected String alias;
}
