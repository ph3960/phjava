package com.ebanma.cloud.rpc.consumer.client;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.ebanma.cloud.rpc.consumer.client.handler.RpcClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author : PeiHang
 * @create 2023/5/31 17:23
 */
public class RpcClient {


    private EventLoopGroup group;
    private Channel channel;
    private String ip;
    private Integer port;

    private RpcClientHandler rpcClientHandler = new RpcClientHandler();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public RpcClient(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
        init();
    }

    public void init() {
        try {
            //1. 创建客户端线程组
            group = new NioEventLoopGroup();
            //2. 创建客户端启动助手
            Bootstrap bootstrap = new Bootstrap();
            //3. 设置参数
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 添加String类型的编解码器
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            // 添加业务处理类
                            pipeline.addLast(rpcClientHandler);
                        }
                    });
            //4. 连接Netty服务端
            Channel channel = bootstrap.connect(ip, port).sync().channel();
        } catch (Exception e) {
            e.printStackTrace();
            close();
        }
    }

    public void close() {
        if (channel != null) {
            channel.close();
        }
        if (group != null) {
            group.shutdownGracefully();
        }
    }

    public Object send(String msg) throws ExecutionException, InterruptedException {
        rpcClientHandler.setRequestMsg(msg);
        Future submit = executorService.submit(rpcClientHandler);
        return submit.get();
    }

}
