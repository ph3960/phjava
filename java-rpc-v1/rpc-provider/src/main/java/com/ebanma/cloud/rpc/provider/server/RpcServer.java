package com.ebanma.cloud.rpc.provider.server;

import com.ebanma.cloud.rpc.provider.server.handler.RpcServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author : PeiHang
 * @create 2023/5/31 17:07
 */
@Component
public class RpcServer  implements DisposableBean {


    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;

    @Autowired
    private RpcServerHandler rpcServerHandler;

    public void start(String ip, Integer port) {
        try {
            // 1.创建Netty线程组
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();
            // 2.创建Netty服务端启动助手
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 3.设置参数
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 添加String类型编解码器
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            // 添加业务处理类
                            pipeline.addLast(rpcServerHandler);
                        }
                    });
            // 4.绑定端口，等待客户端连接
            ChannelFuture sync = serverBootstrap.bind(ip, port).sync();
            System.out.println("【自定义RPC框架-服务端启动成功】" + ip + ":" + port);
            sync.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bossGroup != null) {
                bossGroup.shutdownGracefully();
            }
            if (workerGroup != null) {
                workerGroup.shutdownGracefully();
            }
        }
    }

    @Override
    public void destroy() throws Exception {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
    }
}
