package com.hello.nettyclient;

public class NettyClientApplication {
    public static void main(String[] args) {
        new NettyClient("127.0.0.1", 7888).run();
    }
}
