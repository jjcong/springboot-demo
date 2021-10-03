package com.jincong.springboot.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/10/3
 */
@Slf4j
public class HeartBeatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)  {
        log.info(" client received :" + msg);
        if ("idle close".equals(msg)) {
            System.out.println(" 服务端关闭连接，客户端也关闭");
            ctx.channel().closeFuture();
        }
    }
}
