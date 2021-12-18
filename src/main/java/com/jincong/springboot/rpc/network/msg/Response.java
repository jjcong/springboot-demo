package com.jincong.springboot.rpc.network.msg;

import io.netty.channel.Channel;
import lombok.Data;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/12/18
 */
@Data
public class Response {


    /**
     * 连接通道
     */
    private transient Channel channel;

    /**
     * 请求Id
     */
    private String requestId;

    /**
     * 返回结果
     */
    private Object result;

}
