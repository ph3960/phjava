package com.ebanma.cloud.rpc.consumer.client.handler;
import java.util.concurrent.Callable;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/**
*
* @Author : PeiHang
* @create 2023/5/31 17:32
*/


public class RpcClientHandler extends SimpleChannelInboundHandler<String> implements Callable {

    private ChannelHandlerContext context;
    private String requestMsg;
    private String responseMsg;

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg;
    }

    // 接收消息
    // 通道读取就绪事件
    @Override
    protected synchronized void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        responseMsg = s;
        notify();
    }

    // 通道连接就绪事件
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public synchronized Object call() throws Exception {
        // 发送消息到服务端
        context.writeAndFlush(requestMsg);
        wait();
        return responseMsg;
    }
}