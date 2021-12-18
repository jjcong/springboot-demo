package com.jincong.springboot.rpc.domain.domain;

import lombok.Data;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥
 */

@Data
public class RpcProviderConfig {

    /**
     * 接口
     */
    private String nozzle;

    /**
     * 映射
     */
    private String ref;

    /**
     * 别名
     */
    private String alias;
    /**
     * ip
     */
    private String host;

    /**
     * 端口
     */
    private int port;


}
