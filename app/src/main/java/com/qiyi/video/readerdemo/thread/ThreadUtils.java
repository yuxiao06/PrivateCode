package com.qiyi.video.readerdemo.thread;

import android.os.Process;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaoqin on 2016/7/27.
 * 多线程工具类
 * <p>
 * 使用该类时，需要注意区分各个线程池的作用，尽量使用优先级低的线程池
 */

public class ThreadUtils {
    //串行保存书签，每次只保留最新的消息，清除队列
    private static DispatchQueue bookMarkQueue = new DispatchQueue("bookMarkQueue");
    //串行保存阅读时间
    private static DispatchQueue readTimeQueue = new DispatchQueue("readTimeQueue");

    private static DispatchQueue playTimeQueue = new DispatchQueue("playTimeQueue");
    //串行保存章节阅读时间
    private static DispatchQueue chapterReadTimeQueue = new DispatchQueue("chapterReadTimeQueue");
    //串行发送埋点信息
    private static DispatchQueue pingbackQueue = new DispatchQueue("pingbackQueue");

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int KEEP_ALIVE_SECONDS = 10;

    //后台线程池：负责优先级较低的任务（如与UI交互无关的工作，用户不可见，不感知的工作），后台优先级
    private static final Executor mBackgroundExecutor;

    //网络请求线程池：负责网络请求专用线程池，一般来说网络请求会与UI相关，所以优先级较高，如果是优先级较低也可使用后台线程池
    private static final Executor mNetWorkExecutor;
    //立即执行线程池：线程池没有大小，所有加入的线程都会立即执行(谨慎使用)
    private static final ExecutorService mImmediateExecutor;
    //cpu密集型线程池
    private static final ThreadPoolExecutor sCPUThreadPoolExecutor;

    static {
        mBackgroundExecutor = Executors.newFixedThreadPool(CORE_POOL_SIZE, new PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND, "BackgroundExecutor"));

        mNetWorkExecutor = Executors.newFixedThreadPool(CORE_POOL_SIZE, new PriorityThreadFactory(Process.THREAD_PRIORITY_DEFAULT, "NetWorkExecutor"));
//        mNetWorkExecutor = AsyncTask.THREAD_POOL_EXECUTOR;

        mImmediateExecutor = Executors.newCachedThreadPool(new PriorityThreadFactory(Process.THREAD_PRIORITY_DEFAULT,
                "ImmediateExecutor"));

        sCPUThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, CORE_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    public static Executor getBackgroundExecutor() {
        return mBackgroundExecutor;
    }

    public static Executor getNetWorkExecutor() {
        return mNetWorkExecutor;
    }

    public static ExecutorService getImmediateExecutor() {
        return mImmediateExecutor;
    }

    public static ExecutorService getSingleThreadExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    public static ThreadPoolExecutor getCPUThreadPoolExecutor() {
        return sCPUThreadPoolExecutor;
    }

    public static DispatchQueue getBookMarkQueue() {
        return bookMarkQueue;
    }

    public static DispatchQueue getReadTimeQueue() {
        return readTimeQueue;
    }

    public static DispatchQueue getPlayTimeQueue() {
        return playTimeQueue;
    }

    public static DispatchQueue getChapterReadTimeQueue() {
        return chapterReadTimeQueue;
    }


    public static DispatchQueue getPingbackQueue() {
        return pingbackQueue;
    }

    public static void execute(final Runnable runnable, boolean async) {
        if (async) {
            ThreadUtils.getImmediateExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    runnable.run();
                }
            });
        } else {
            runnable.run();
        }
    }
}
