package com.qiyi.video.readerdemo.dispatcher;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.util.Log;

import com.qiyi.video.readerdemo.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 启动器调用类
 */

public class TaskDispatcher {
    private static final int WAITTIME = 10000;
    private static Context sContext;
    private List<Future> mFutures = new ArrayList<>();
    private static volatile boolean sHasInit;
    private List<Task> mAllTasks = new ArrayList<>();
    private List<Class<? extends Task>> mClsAllTasks = new ArrayList<>();
    private volatile List<Task> mMainThreadTasks = new ArrayList<>();
    private CountDownLatch mCountDownLatch;
    private AtomicInteger mNeedWaitCount = new AtomicInteger();//保存需要Wait的Task的数量
    private List<Task> mNeedWaitTasks = new ArrayList<>();//调用了await的时候还没结束的且需要等待的Task
    private volatile List<Class<? extends Task>> mFinishedTasks = new ArrayList<>(100);//已经结束了的Task
    private HashMap<Class<? extends Task>, ArrayList<Task>> mDependedHashMap = new HashMap<>();

    private TaskDispatcher() {
    }

    public static void init(Context context) {
        if (context != null) {
            sContext = context;
            sHasInit = true;
        }
    }

    /**
     * 注意：每次获取的都是新对象
     *
     * @return
     */
    public static TaskDispatcher createInstance() {
        if (!sHasInit) {
            throw new RuntimeException("must call TaskDispatcher.init first");
        }
        return new TaskDispatcher();
    }

    public TaskDispatcher addTask(Task task) {
        if (task != null) {
            collectDepends(task);
            mAllTasks.add(task);
            mClsAllTasks.add(task.getClass());
            // 非主线程且需要wait的，主线程不需要CountDownLatch也是同步的
            if (ifNeedWait(task)) {
                mNeedWaitTasks.add(task);
                mNeedWaitCount.getAndIncrement();
            }
        }
        return this;
    }

    private void collectDepends(Task task) {
        if (task.dependsOn() != null && task.dependsOn().size() > 0) {
            for (Class<? extends Task> cls : task.dependsOn()) {
                if (mDependedHashMap.get(cls) == null) {
                    mDependedHashMap.put(cls, new ArrayList<Task>());
                }
                mDependedHashMap.get(cls).add(task);
                if (mFinishedTasks.contains(cls)) {
                    task.satisfy();
                }
            }
        }
    }

    private boolean ifNeedWait(Task task) {
        return !task.runOnMainThread() && task.needWait();
    }

    @UiThread
    public void start() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new RuntimeException("must be called from UiThread");
        }
        if (mAllTasks.size() > 0) {
            printdMsg();
            mAllTasks = TaskSortUtil.getSortResult(mAllTasks, mClsAllTasks);
            mCountDownLatch = new CountDownLatch(mNeedWaitCount.get());

            sendAndExecuteAsyncTasks();
            executeTaskMain();
        }
    }

    /**
     * 查看被依赖的信息
     */
    private void printdMsg() {
        if (false) {
            for (Class<? extends Task> cls : mDependedHashMap.keySet()) {
                Log.i(MainActivity.READER_DEMO_TAG+DispatcherActivity.DISPATCHER_TAG, "cls " + cls.getSimpleName() + "   " + mDependedHashMap.get(cls).size());
                for (Task task : mDependedHashMap.get(cls)) {
                    Log.i(MainActivity.READER_DEMO_TAG+DispatcherActivity.DISPATCHER_TAG, "cls       " + task.getClass().getSimpleName());
                }
            }
        }
    }

    public void cancel() {
        for (Future future : mFutures) {
            future.cancel(true);
        }
    }

    private void executeTaskMain() {
        for (Task task : mMainThreadTasks) {
            long time = System.currentTimeMillis();
            new DispatchRunnable(task,this).run();
            Log.i(MainActivity.READER_DEMO_TAG+DispatcherActivity.DISPATCHER_TAG,"real main " + task.getClass().getSimpleName() + " cost  " + (System.currentTimeMillis() - time));
        }
    }

    private void sendAndExecuteAsyncTasks() {
        for (Task task : mAllTasks) {
            sendTaskReal(task);
            task.setSend(true);
        }
    }

    /**
     * 通知Children一个前置任务已完成
     *
     * @param launchTask
     */
    public void satisfyChildren(Task launchTask) {
        ArrayList<Task> arrayList = mDependedHashMap.get(launchTask.getClass());
        if (arrayList != null && arrayList.size() > 0) {
            for (Task task : arrayList) {
                task.satisfy();
            }
        }
    }

    public void markTaskDone(Task task) {
        if (ifNeedWait(task)) {
            mFinishedTasks.add(task.getClass());
            mNeedWaitTasks.remove(task);
            mCountDownLatch.countDown();
            mNeedWaitCount.getAndDecrement();
        }
    }

    private void sendTaskReal(final Task task) {
        if (task.runOnMainThread()) {
            mMainThreadTasks.add(task);

            if (task.needCall()) {
                task.setTaskCallBack(new TaskCallBack() {
                    @Override
                    public void call() {
                        satisfyChildren(task);
                        markTaskDone(task);
                    }
                });
            }
        } else {
            // 直接发，是否执行取决于具体线程池
            Future future = task.runOn().submit(new DispatchRunnable(task,this));
            mFutures.add(future);
        }
    }

    @UiThread
    public void await() {
        try {
            for (Task task : mNeedWaitTasks) {
                Log.i(MainActivity.READER_DEMO_TAG+DispatcherActivity.DISPATCHER_TAG, "needWait: " + task.getClass().getSimpleName());
            }

            if (mNeedWaitCount.get() > 0) {
                mCountDownLatch.await(WAITTIME, TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException e) {
        }
    }

    public static Context getContext() {
        return sContext;
    }
}

