package nju.cgm.nettyclient.config;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: Bright Chan
 * @date: 2021/5/5 17:56
 * @description: NettyClientHandler
 */

@Component
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // read msg from client
        logger.info("client read: " + msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        // close the connection when an exception is raised
        ctx.close();
    }
}
