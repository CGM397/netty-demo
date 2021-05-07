package nju.cgm.nettyclient.config;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: Bright Chan
 * @date: 2021/5/5 18:32
 * @description: ClientChannelInitializer
 */

@Component
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Resource
    private NettyClientHandler nettyClientHandler;

    @Override
    protected void initChannel(SocketChannel ch) {
        // 添加解编码
        ch.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
        ch.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        ch.pipeline().addLast(nettyClientHandler);
    }
}
