package com.lyne.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nn_liu
 * @Created 2017-08-18-16:33
 */

public class EchoInbountHandler2 extends ChannelInboundHandlerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(EchoInbountHandler2.class);

    @Override
    // 读取Client发送的信息，并打印出来
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(">>>>>EchoInbountHandler2.channelRead: ctx :" + ctx);
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        String resultStr = new String(result1);
        System.out.println("Echo Server receive:" + resultStr);
        result.release();

        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println(">>>>>EchoInbountHandler2.channelReadComplete");
        ctx.flush();
    }

}
