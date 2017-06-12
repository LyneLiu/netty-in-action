package com.lyne.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by nn_liu on 2017/6/12.
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Usage: "+EchoServer.class.getSimpleName()+" <port>");
        }

        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }

    /**
     * 引导过程的大致步骤：
     * 1、创建一个ServerBootstrap的实例以引导和绑定服务器；
     * 2、创建并分配一个NioEventLoopGroup实例以进行事件的处理，如接受新连接以及读/写数据；
     * 3、指定服务器绑定的本地的InetSocketAddress；
     * 4、使用一个EchoServerHandler的实例初始化每一个新的Channel；
     * 5、调用ServerBootstrap.bind()方法绑定服务器
     */
    private void start() {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture future = bootstrap.bind().sync();
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }
}
