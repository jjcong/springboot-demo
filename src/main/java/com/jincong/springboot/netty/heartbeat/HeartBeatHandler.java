package com.jincong.springboot.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/10/3
 */
@Slf4j
public class HeartBeatHandler extends SimpleChannelInboundHandler<String> {

    public static final String HEARTBEAT_PACKET = "Heartbeat Packet";

    private int readIdleTimes = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {

        log.info(" ====== > [server] message received : {}", msg);
        if (HEARTBEAT_PACKET.equals(msg)) {
            ctx.channel().writeAndFlush("ok");
        } else {
            log.info(" 其他信息处理 ...");
        }

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        IdleStateEvent event = ((IdleStateEvent) evt);

        String eventType;

        switch (event.state()) {
            case READER_IDLE:
                eventType = "读空闲";
                readIdleTimes++;
                break;
            case WRITER_IDLE:
                eventType = "写空闲";
                // 不处理
                break;
            case ALL_IDLE:
                eventType = "读写空闲";
                // 不处理
                break;

            default:
                eventType = "状态错误";

        }

        log.info("服务器: {}, 发生超时事件：{} ", ctx.channel().remoteAddress(), eventType);

        if (readIdleTimes > 3) {
            log.info(" [server]读空闲超过3次，关闭连接，释放更多资源");
            ctx.channel().writeAndFlush("idle close");
            ctx.channel().close();
        }


    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("=== " + ctx.channel().remoteAddress() + " is active ===");
    }
}
