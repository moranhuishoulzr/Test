package com.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-04-10 16:52
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int)(System.currentTimeMillis()/1000L+2208988800L));

        final ChannelFuture f = ctx.writeAndFlush(time);
        //f.addListener(ChannelFutureListener.CLOSE);//预定义的监听器
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                assert f == future;
                ctx.close();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
