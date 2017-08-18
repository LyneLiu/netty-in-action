package com.lyne.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nn_liu
 * @Created 2017-08-18-16:33
 */

public class EchoOutbountHandler2 extends ChannelOutboundHandlerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(EchoOutbountHandler2.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println(">>>>>EchoOutbountHandler2.write");
        // 执行下一个OutboundHandler
        super.write(ctx, msg, promise);
    }


}
