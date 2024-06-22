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
    public static void main(String[] args) {
        System.out.println("Socket客户端启动");
        Scanner scanner = new Scanner(System.in);
        Integer port = null;
        while (true) {
            try {
                if (null == port) {
                    System.out.print("请输入服务端监听端口：");
                    port = scanner.nextInt();
                }
                System.out.println("是否要链接到服务器 yes/no ?");
                String answer = scanner.next();
                if (null != answer && "yes".equals(answer)) {
                    Socket socket = new Socket("localhost", port);
                    System.out.print("服务器已链接，请输入要发送的内容：");
                    String context = scanner.next();
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF(context);
                    System.out.println("内容已发送\r\n-----------------------------");
                    socket.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());
                break;
            }
        }
    }
}
