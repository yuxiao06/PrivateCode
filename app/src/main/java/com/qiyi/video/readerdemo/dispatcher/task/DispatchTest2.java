package com.qiyi.video.readerdemo.dispatcher.task;

import android.os.SystemClock;
import android.util.Log;

import com.qiyi.video.readerdemo.MainActivity;
import com.qiyi.video.readerdemo.dispatcher.DispatcherActivity;
import com.qiyi.video.readerdemo.dispatcher.Task;

import java.util.ArrayList;
import java.util.List;

public class DispatchTest2 extends Task {
    @Override
    public List<Class<? extends Task>> dependsOn() {
        List<Class<? extends Task>> task = new ArrayList<>();
        task.add(DispatchTest1.class);
        return task;
    }

    @Override
    public void run() {
        Log.i(MainActivity.READER_DEMO_TAG + DispatcherActivity.DISPATCHER_TAG,  "DispatchTest2 content running");
        SystemClock.sleep(1000);
    }
}

