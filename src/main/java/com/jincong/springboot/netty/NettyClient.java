package com.jincong.springboot.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * NettyClient
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/9/2
 */
@Slf4j
public class NettyClient {

    /**
     * 服务器地址
     */
    public static final String INET_HOST = "127.0.0.1";

    /**
     * 服务器端口号
     */
    public static final int INET_PORT = 6668;

    public static void main(String[] args) throws InterruptedException {
        // 客户端事件组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new NettyClientHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect(INET_HOST, INET_PORT).sync();


            log.info("=============== {} 客户端启动 ===============", channelFuture.channel().localAddress());

            // 客户端输入内容
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                channelFuture.channel().writeAndFlush(string);
            }
            // 监听通道的关闭事件
            channelFuture.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }
    }
}
