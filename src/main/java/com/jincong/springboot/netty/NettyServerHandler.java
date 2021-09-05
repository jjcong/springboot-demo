package com.jincong.springboot.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * NettyServerHandler
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/9/1
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * 读取实际数据
     * @param ctx 上下文对象，包含管道pipeline、通道channel、地址的等
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("服务器读取线程， channel={}", ctx.channel());
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = channel.pipeline();

        // 将msg转为ByteBuf。注意，此处时Netty自带的ByteBuf而不是NIO的ByteBuffer
        ByteBuf buf = (ByteBuf) msg;

        log.info("客户端发送的消息是：{}", buf.toString(CharsetUtil.UTF_8));
        log.info("客户端地址是: {}", channel.remoteAddress());
    }



    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {

        // 将数据写入缓存并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, 客户端", CharsetUtil.UTF_8));

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
