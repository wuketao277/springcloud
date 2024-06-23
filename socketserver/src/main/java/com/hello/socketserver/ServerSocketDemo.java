package com.hello.socketserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/**
 * @author wuketao
 * @date 2024/6/22
 * @Description
 */
public class ServerSocketDemo extends Thread {
    private static Scanner scanner = new Scanner(System.in);
    private ServerSocket serverSocket;

    public ServerSocketDemo(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("服务端已就绪。");
        //设置20s内无客户端连接，则抛出SocketTimeoutException异常
//        serverSocket.setSoTimeout(2000000);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket server = serverSocket.accept();
                System.out.println("服务端接收到客户端请求，服务端线程id:" + Thread.currentThread().getId());

                //当服务端监听到客户端的连接后才会执行以下代码
                System.out.println("客户端地址：" + server.getRemoteSocketAddress());

                //监听来自客户端的消息
                DataInputStream dis = new DataInputStream(server.getInputStream());
                System.out.println("客户端发送信息：" + dis.readUTF());

                //通过socket向客户端发送信息
                DataOutputStream dos = new DataOutputStream(server.getOutputStream());
                dos.writeUTF("我是服务端，您已连接到：" + server.getLocalSocketAddress());
                server.close();
                System.out.println("-------------------------------");
            } catch (SocketTimeoutException e) {
                System.out.println("20s内无客户端连接，正在关闭服务端监听服务");
                continue;
            } catch (IOException e) {
                e.printStackTrace();
//                break;
            }


        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Socket服务器启动");
            System.out.print("请输入服务端监听端口：");
            Integer port = scanner.nextInt();
            // 启动服务端监听线程
            Thread t1 = new ServerSocketDemo(port);
            t1.run();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
