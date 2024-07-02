package com.hello.websocket.controller;

import com.hello.websocket.websocket.MyWebSocket;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {
    /**
     * 测试服务端向客户端发送消息
     *
     * @param vo
     */
    @PostMapping("sendMsg")
    public void sendMsg(@RequestBody PersonVO vo) {
        // 这里接受到前端发来的数据后调用websocket,模拟从服务器向客户端发送消息。
        MyWebSocket.sendMessage(vo, vo.getId());
    }

    @GetMapping("status")
    public String status() {
        return "ok";
    }
}
