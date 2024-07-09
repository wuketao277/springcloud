package com.hello.nettyserver;

/**
 * Netty服务端应用程序启动类
 */
public class NettyServerApplication {


    public static void main(String[] args) {
        try {
            new NettyServer(7888).run();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
