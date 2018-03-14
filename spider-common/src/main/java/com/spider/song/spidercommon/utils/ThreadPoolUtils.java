package com.spider.song.spidercommon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:
 * User: songzhengjie
 * Date: 2018-03-13
 * Time: 11:57
 * ========================================
 */
public class ThreadPoolUtils {

    private static Logger logger = LoggerFactory.getLogger("ThreadPoolUtils");

    private static final int corePoolSize = 5;
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final BlockingDeque<Runnable> BLOCKING_DEQUE = new LinkedBlockingDeque<>(100);
    private static final RejectedExecutionHandler rejectedExecutionHandler = (r, executor) -> {
        try {
            executor.getQueue().put(r);
        } catch (InterruptedException e) {
            logger.error("[rejectedExecution]::获取资源出错[r]:{}, executor]:{}",r, executor);
        }

    };

    private static final ExecutorService threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, CPU_COUNT * 2, 5, TimeUnit.SECONDS, BLOCKING_DEQUE, rejectedExecutionHandler);


    public static ExecutorService getInstance(){
        return threadPoolExecutor;
    }

}
