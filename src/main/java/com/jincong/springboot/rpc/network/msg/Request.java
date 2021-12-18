package com.jincong.springboot.rpc.network.msg;

import io.netty.channel.Channel;
import lombok.Data;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/12/18
 */
@Data
public class Request {


    /**
     * 请求id
     */
    private String requestId;
    /**
     * 连接通道
     */
    private transient Channel channel;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数类型
     */
    private Class[] paramTypes;

    /**
     * 参数
     */
    private Object[] args;

    /**
     * 接口
     */
    private String nozzle;

    /**
     * 服务实现类
     */
    private String ref;

    /**
     * 服务别名
     */
    private String alias;
}
