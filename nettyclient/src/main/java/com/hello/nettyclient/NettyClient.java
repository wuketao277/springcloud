package com.hello.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * Netty客户端
 */
public class NettyClient {
    private final String host;
    private final int port;
    private String nickName = "匿名";

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //得到pipeline
                            ChannelPipeline pipeline = ch.pipeline();
                            //加入相关handler的解码器
                            pipeline.addLast("decoder", new StringDecoder());
                            //加入相关handler的编码器
                            pipeline.addLast("encoder", new StringEncoder());
                            //加入自定义的handler
                            pipeline.addLast(new NettyClientHandler());
                        }
                    });
            //连接服务器返回通道
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();

            if (channelFuture.isSuccess()) {
                System.out.println("本地ip:" + channel.localAddress() + ",连接服务器ip: " + channel.remoteAddress() + " 成功");

                // 无线循环监听客户端输入，将输入发送给服务器
                Scanner scanner = new Scanner(System.in);
                System.out.println("请先设置昵称：");
                this.nickName = scanner.nextLine();
                System.out.println("您的昵称是" + this.nickName);
                System.out.println("说点什么？");
                // 发送消息
                while (scanner.hasNextLine()) {
                    channel.writeAndFlush(this.nickName + ":" + scanner.nextLine());
                    System.out.println("说点什么？");
                }

                //给关闭监听进行通道
                channel.closeFuture().sync();
            } else {
                System.out.println("本地ip:" + channel.localAddress() + ",连接服务器ip: " + channel.remoteAddress() + " 失败");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}
