package com.jincong.springboot.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 心跳检测服务端
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/10/3
 */
@Slf4j
public class HeartBeatServer {

    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            // IdleStateHandler的readerIdleTime参数指定超过3s的话没收到客户端的连接，会触发
                            // IdleStateEvent事件并且交给下一个handler处理
                            // 下一个handler必须实现userEventTriggered方法处理对应的事件
                            pipeline.addLast(new IdleStateHandler(3, 0, 0, TimeUnit.SECONDS));

                            pipeline.addLast(new HeartBeatHandler());
                        }
                    });

            log.info("Netty Server start >>>>>>>>>>>>>>>>>>>");
            ChannelFuture channelFuture = bootstrap.bind(9000).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

}


