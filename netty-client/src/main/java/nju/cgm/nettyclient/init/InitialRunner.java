package nju.cgm.nettyclient.init;

import nju.cgm.nettyclient.client.NettyClient;
import nju.cgm.nettyclient.pool.ClientPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: Bright Chan
 * @date: 2021/5/5 19:14
 * @description: InitialRunner
 */

@Component
public class InitialRunner implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(InitialRunner.class);

    @Resource
    private NettyClient nettyClient;

    public void run(ApplicationArguments args) {
        logger.info("====== netty client initializing...... ======");
        ClientPool.getClientPool().execute(() -> nettyClient.run());
        logger.info("====== initialize netty client succeed ======");
    }
}
