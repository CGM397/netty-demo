package nju.cgm.nettyserver.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Bright Chan
 * @date: 2021/5/7 9:59
 * @description: ServerPool
 */
public class ServerPool {
    private static ExecutorService ServerPool = new ThreadPoolExecutor(
            2,
            3,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());

    public static ExecutorService getServerPool() {
        return ServerPool;
    }
}
