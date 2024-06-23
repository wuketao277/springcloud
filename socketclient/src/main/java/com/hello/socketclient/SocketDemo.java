package com.hello.socketclient;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author wuketao
 * @date 2024/6/22
 * @Description
 */
public class SocketDemo {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Socket客户端启动");
        System.out.print("请输入服务端监听端口：");
        Integer port = scanner.nextInt();
        while (true) {
            try {
                // 从终端询问用户是否要链接到服务器
                System.out.println("是否要链接到服务器 yes/no ?");
                String answer = scanner.next();
                if (null != answer && "yes".equals(answer)) {
                    System.out.print("请输入要发送的内容：");
                    // 从终端读取发送的内容
                    String context = scanner.next();
                    Socket socket = new Socket("localhost", port);
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF(context);
                    System.out.println(String.format("'%s'内容已发送\r\n---------------", context));
                    socket.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());
                break;
            }
        }
    }
}
