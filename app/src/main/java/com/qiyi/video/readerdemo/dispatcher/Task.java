package com.qiyi.video.readerdemo.dispatcher;

import android.content.Context;
import android.os.Process;

import com.qiyi.video.readerdemo.thread.ThreadUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public abstract class Task implements ITask {
    protected Context mContext = TaskDispatcher.getContext();
    private volatile boolean mIsSend;// Task是否已经被分发
    private CountDownLatch mDepends = new CountDownLatch(dependsOn() == null ? 0 : dependsOn().size());// 当前Task依赖的Task数量（需要等待被依赖的Task执行完毕才能执行自己），默认没有依赖

    /**
     * 当前Task等待，让依赖的Task先执行
     */
    public void waitToSatisfy() {
        try {
            mDepends.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 依赖的Task执行完一个
     */
    public void satisfy() {
        mDepends.countDown();
    }

    /**
     * 是否需要尽快执行，解决特殊场景的问题：一个Task耗时非常多但是优先级却一般，很有可能开始的时间较晚，
     * 导致最后只是在等它，这种可以早开始。
     *
     * @return
     */
    public boolean needRunAsSoon() {
        return false;
    }

    /**
     * Task的优先级，运行在主线程则不要去改优先级
     *
     * @return
     */
    @Override
    public int priority() {
        return Process.THREAD_PRIORITY_DEFAULT;
    }

    /**
     * 请选择使用 io 密集型 还是 cpu 密集型线程池
     * @return
     */
    @Override
    public ExecutorService runOn() {
        return ThreadUtils.getCPUThreadPoolExecutor();
    }

    /**
     * 异步线程执行的Task是否需要在被调用await的时候等待
     *
     * @return
     */
    @Override
    public boolean needWait() {
        return true;
    }

    /**
     * 当前Task依赖的Task集合（需要等待被依赖的Task执行完毕才能执行自己），默认没有依赖
     *
     * @return
     */
    @Override
    public List<Class<? extends Task>> dependsOn() {
        return null;
    }

    @Override
    public boolean runOnMainThread() {
        return false;
    }

    @Override
    public void setTaskCallBack(TaskCallBack callBack) {}

    @Override
    public boolean needCall() {
        return false;
    }

    public boolean isSend() {
        return mIsSend;
    }

    public void setSend(boolean send) {
        mIsSend = send;
    }
}
