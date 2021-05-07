package nju.cgm.nettyclient.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import nju.cgm.nettyclient.config.ClientChannelInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: Bright Chan
 * @date: 2021/5/5 18:01
 * @description: NettyClient
 */

@PropertySource({"classpath:netty-config.properties"})
@Component
public class NettyClient {

    private Logger logger = LoggerFactory.getLogger(NettyClient.class);

    @Value("${netty_server_host}")
    private String host;

    @Value("${netty_server_port}")
    private int port;

    @Resource
    private ClientChannelInitializer clientChannelInitializer;

    public void run() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.TCP_NODELAY, true);
            b.handler(clientChannelInitializer);

            ChannelFuture f = b.connect(host, port).sync();

            // 发送消息
            f.channel().writeAndFlush("hello netty!");
            // 等待连接被关闭
            f.channel().closeFuture().sync();
        }
        catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        finally {
            workerGroup.shutdownGracefully();
        }
    }
}
