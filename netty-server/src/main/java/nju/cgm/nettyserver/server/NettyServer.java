package nju.cgm.nettyserver.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import nju.cgm.nettyserver.config.ServerChannelInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: Bright Chan
 * @date: 2021/5/5 15:49
 * @description: NettyServer
 */

@PropertySource({"classpath:netty-config.properties"})
@Component
public class NettyServer {

    private Logger logger = LoggerFactory.getLogger(NettyServer.class);

    @Value("${netty_server_host}")
    private String host;

    @Value("${netty_server_port}")
    private int port;

    @Resource
    private ServerChannelInitializer serverChannelInitializer;

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(200);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(serverChannelInitializer)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(host, port).sync();

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        }
        catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
