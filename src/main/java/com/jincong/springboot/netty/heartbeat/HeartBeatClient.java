package com.jincong.springboot.netty.heartbeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Netty超时客户端
 * @author j_cong
 * @version V1.0
 * @date 2021/10/3
 */
@Slf4j
public class HeartBeatClient {

    public static void main(String[] args) {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap().group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            pipeline.addLast(new HeartBeatClientHandler());
                        }
                    });

            log.info("Netty Client start >>>>>>>>>>>>>>>>");

            Channel channel = bootstrap.connect("127.0.0.1", 9000).sync().channel();

            String text = "Heartbeat Packet";

            Random random = new Random();

            while (channel.isActive()) {
                int num = random.nextInt(10);
                TimeUnit.SECONDS.sleep(num);
                channel.writeAndFlush(text);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }



    }
}
