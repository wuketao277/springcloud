WebSocket练习项目
WebSocket提供了客户端服务器之间全双工模式，这样服务端可以主动向客户端推送消息。
文件说明：
    1、src->main-resources下的webclient.xml文件中定义了一个简单客户端程序，
        可以建立WebSocket链接，向服务端发消息，接受服务端发来的消息。
    2、src->java->websocket下的MyWebSocket文件中定义了WebSocket相关方法。
    3、src->main->controller下的TestController文件中定义一个接口用来测试从服务端向客户端发送消息。

websocket访问地址：ws://localhost:9000/websocket/{userId}

WebSocket交互的简单流程：
1、客户端JS中创建WebSocket对象，并绑定相关方法。注意使用的是ws协议而不是http协议。
2、服务端监听终结点，当接受到WebSocket请求后，通过唯一标识(可以是userId)和WebSocket实例组成map。
3、当服务端需要向客户端发送消息时，通过唯一标识找到这个对应的WebSocket实例后，通过实例向客户端发送消息。