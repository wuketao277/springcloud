package com.hello.nettyserver;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 定义一个channel组，管理所有的channel
     * GlobalEventExecutor.INSTANCE是全局的事件执行器，是一个单例
     */
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 表示连接建立，一旦连接，第一个被执行
     * 将当前channel加入到 channelGroup
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户加入聊天的信息推送给其他在线的客户端
        //该方法会将channelGroup 中所有的channel 遍历，并发送消息,我们不需要自己遍历
        channels.writeAndFlush(sdf.format(new Date()) + " [客户端]" + channel.remoteAddress() + " 加入聊天\n");
        channels.add(channel);
    }

    /**
     * 表示channel 处于活动上线,提示 xx上线
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel()
                .remoteAddress() + " 在[ " + sdf.format(new Date()) + " ] 上线了~");
    }

    /**
     * 表示channel 处于离线,提示 xx离线
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel()
                .remoteAddress() + "在 " + sdf.format(new Date()) + " 离线了~");
    }

    /**
     * 断开连接，将XX客户离开信息推送给当前在线的客户
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush("[客户端]" + channel.remoteAddress() + "在 【" + sdf.format(new Date()) + "】 离开\n");
        System.out.println("移除通道" + channel.hashCode() + ",当前通道总数：" + channels.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //获取当前通道channel
        Channel channel = ctx.channel();
        //这时我们遍历channels,根据不同的情况，返回不同的不同消息
        channels.forEach(c -> {
            if (channel != c) {//不是当前的channel，直接打印消息
                //把当前通道的消息转发给其他通道了
                c.writeAndFlush("[客户]" + channel.remoteAddress() + "在 【" + sdf.format(new Date()) + "】 发送了消息:" + msg + " \n");
            } else {
                c.writeAndFlush("【自己】在 【" + sdf.format(new Date()) + "】 发送了消息" + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭通道
        ctx.close();
        System.out.println("在 【" + sdf.format(new Date()) + "】 关闭通道，通道总数：" + channels.size());
    }
}
