package nju.cgm.nettyserver.init;

import nju.cgm.nettyserver.pool.ServerPool;
import nju.cgm.nettyserver.server.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: Bright Chan
 * @date: 2021/5/5 19:10
 * @description: InitialRunner
 */

@Component
public class InitialRunner implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(InitialRunner.class);

    @Resource
    private NettyServer nettyServer;

    public void run(ApplicationArguments args) {
        logger.info("====== netty server initializing...... ======");
        ServerPool.getServerPool().execute(() -> nettyServer.run());
        logger.info("====== initialize netty server succeed ======");
    }
}
