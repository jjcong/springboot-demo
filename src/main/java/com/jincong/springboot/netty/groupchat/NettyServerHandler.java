package com.jincong.springboot.netty.groupchat;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

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
     * 定义全局事件执行器，单例模式
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【客户端】" + channel.remoteAddress() + "上线了 " + simpleDateFormat.format(new Date()) + "\n");
        channelGroup.add(channel);
        log.info("客户端 {} 上线了 \n", ctx.channel().remoteAddress() );
        System.out.println();

        // ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        // 将客户端下线事件推送给所有在线的客户端
        channelGroup.writeAndFlush("【客户端】" + channel.remoteAddress() + "下线了 " + "\n");
        log.info("{} 下线了 ", ctx.channel().remoteAddress());
        System.out.println();

        log.info("当前还有{}人在线 ", channelGroup.size());
        System.out.println();
        ctx.fireChannelInactive();
    }


    /**
     * 读取实际数据
     * @param ctx 上下文对象，包含管道pipeline、通道channel、地址的等
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Channel channel = ctx.channel();
        // pipeline可以理解为一个责任链模式
        ChannelPipeline pipeline = channel.pipeline();


        channelGroup.forEach(ch -> {
            String sendMsg;
            // 不是当前的channel则转发消息
            if(ch != channel) {
                sendMsg = "【客户端】" + channel.remoteAddress() + "发送了消息： " + msg + "\n";
            } else {
                sendMsg = "【自己】" + channel.remoteAddress() + "发送了消息： " + msg + "\n";
            }
            ch.writeAndFlush(Unpooled.copiedBuffer(sendMsg, CharsetUtil.UTF_8));
        });



   /*     // 将msg转为ByteBuf。注意，此处时Netty自带的ByteBuf而不是NIO的ByteBuffer
        ByteBuf buf = (ByteBuf) msg;

        log.info("客户端发送的消息是：{}", buf.toString(CharsetUtil.UTF_8));
        log.info("客户端地址是: {}", channel.remoteAddress());*/
    }



    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {

        // 将数据写入缓存并刷新
        // ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, 客户端", CharsetUtil.UTF_8));

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
