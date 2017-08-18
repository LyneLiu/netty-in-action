package com.lyne.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nn_liu
 * @Created 2017-08-18-16:33
 */

public class EchoInbountHandler1 extends ChannelInboundHandlerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(EchoInbountHandler1.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(">>>>>EchoInbountHandler1.channelRead: ctx :" + ctx);

        // 通知执行下一个InboundHandler
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println(">>>>>EchoInbountHandler1.channelReadComplete");
        ctx.flush();
    }

}
