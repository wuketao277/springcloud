package com.hello.websocket.websocket;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 访问地址 ws://localhost:9000/websocket/{userId}
 */
@ServerEndpoint(value = "/websocket/{userId}")
@Component
@Slf4j
public class MyWebSocket {
    /**
     * 定义一个静态map，用来存放映射userId -> websocket实例
     * 后续需要从服务端向客户端推送消息是，就是通过userId找到websocket实例，在通过websocket实例薪资操作
     */
    private static ConcurrentHashMap<String, MyWebSocket> webSocketMap = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, MyWebSocket> getWebSocketMap() {
        return webSocketMap;
    }

    public static void setWebSocketMap(ConcurrentHashMap<String, MyWebSocket> webSocketMap) {
        MyWebSocket.webSocketMap = webSocketMap;
    }

    //实例一个session，这个session是websocket的session
    private Session session;

    /**
     * 用于主动向客户端发送消息
     */
    public static void sendMessage(Object message, String userId) {
        MyWebSocket webSocket = webSocketMap.get(userId);
        if (webSocket != null) {
            try {
                // 向客户端发送消息
                webSocket.session.getBasicRemote().sendText(JSONObject.toJSONString(message));
                log.info("【websocket消息】发送消息成功,用户=" + userId + ",消息内容" + message.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //前端请求时一个websocket时
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        // 将当前websocket实例放入到类静态map中
        webSocketMap.put(userId, this);
        // 向客户端发送链接成功的消息
        sendMessage("CONNECT_SUCCESS", userId);
        log.info("【websocket消息】有新的连接,连接id" + userId);
    }

    //前端关闭时一个websocket时
    @OnClose
    public void onClose(@PathParam("userId") String userId) {
        // 链接关闭的时候，移除userId对应的websocket
        webSocketMap.remove(userId);
        log.info("【websocket消息】连接断开,总数:" + webSocketMap.size());
    }

    //前端向后端发送消息
    @OnMessage
    public void onMessage(String message) {
        if (!message.equals("ping")) {
            log.info("【websocket消息】收到客户端发来的消息:" + message);
        }
    }
}
