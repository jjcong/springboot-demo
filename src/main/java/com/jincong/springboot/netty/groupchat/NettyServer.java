package com.jincong.springboot.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * NettyServer
 * 测试Netty通信
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/9/1
 */
@Slf4j
public class NettyServer {

    /**
     * 服务器端口号
     */
    public static final int INET_PORT = 6668;

    public static void main(String[] args) throws InterruptedException {

        // 创建两个线程组bossGroup和workerGroup
        // bossGroup仅负责处理连接请求，workerGroup处理真正的业务
        // 这两个线程组都是无限循环
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    // 设置服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    // 设置线程队列得到的连接数量
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置连接状态为长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 设置处理器，真正执行业务的地方
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            // pipeline相当于一个处理器链，可动态增减handler
                            ChannelPipeline pipeline = socketChannel.pipeline();

                            // 解码器
                            pipeline.addLast("decoder", new StringDecoder());
                            // 编码器
                            pipeline.addLast("encoder", new StringEncoder());
                            // 服务端业务处理器
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });

            log.info(">>>>>>>>>>>>>> 服务器 is ready <<<<<<<<<<<<<<<<<<<<<<");
            // 绑定服务器的端口，异步获取结果
            // sync是等待异步操作的结果，然后才会执行下一步
            ChannelFuture future = bootstrap.bind(INET_PORT).sync();

            // 监听关闭通道事件
            future.channel().closeFuture().sync();
        } finally {

            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }


    }

}
