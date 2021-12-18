package com.jincong.springboot.rpc.config;

import lombok.Data;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/12/18
 */
@Data
public class ConsumerConfig {

    /**
     * 接口
     */
    protected String nozzle;

    /**
     * 别名
     */
    protected String alias;
}
