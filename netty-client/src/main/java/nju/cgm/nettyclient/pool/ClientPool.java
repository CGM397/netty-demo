package nju.cgm.nettyclient.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Bright Chan
 * @date: 2021/5/7 9:59
 * @description: ClientPool
 */
public class ClientPool {
    private static ExecutorService ClientPool = new ThreadPoolExecutor(
            2,
            3,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());

    public static ExecutorService getClientPool() {
        return ClientPool;
    }
}
