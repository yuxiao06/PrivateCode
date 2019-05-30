package com.qiyi.video.readerdemo.dispatcher;

import android.os.Process;
import android.util.Log;

import com.qiyi.video.readerdemo.MainActivity;

public class DispatchRunnable implements Runnable {
    private Task mTask;
    private TaskDispatcher mTaskDispatcher;

    public DispatchRunnable(Task task,TaskDispatcher dispatcher) {
        this.mTask = task;
        this.mTaskDispatcher = dispatcher;
    }

    @Override
    public void run() {
        //TraceCompat.beginSection(mTask.getClass().getSimpleName());

        Process.setThreadPriority(mTask.priority());

        mTask.waitToSatisfy();

        // 执行Task
        mTask.run();

        Log.i(MainActivity.READER_DEMO_TAG + DispatcherActivity.DISPATCHER_TAG,mTask.getClass().getSimpleName() + " finish");

        if (!mTask.needCall() || !mTask.runOnMainThread()) {
            if(mTaskDispatcher != null){
                mTaskDispatcher.satisfyChildren(mTask);
                mTaskDispatcher.markTaskDone(mTask);
            }
        }

        //TraceCompat.endSection();
    }
}