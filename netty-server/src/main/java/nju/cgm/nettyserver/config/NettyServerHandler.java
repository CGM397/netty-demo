package nju.cgm.nettyserver.config;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: Bright Chan
 * @date: 2021/5/5 15:26
 * @description: NettyServerHandler
 */

@Component
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // read msg from client
        logger.info("server read: " + msg.toString());

        // response to client
        ctx.write("Hi, I have received your msg!");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        // close the connection when an exception is raised
        ctx.close();
    }
}
