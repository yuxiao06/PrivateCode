package com.qiyi.video.readerdemo.dispatcher.task;

import android.os.SystemClock;
import android.util.Log;

import com.qiyi.video.readerdemo.MainActivity;
import com.qiyi.video.readerdemo.dispatcher.DispatcherActivity;
import com.qiyi.video.readerdemo.dispatcher.Task;

public class DispatchTest3 extends Task {
    /*@Override
    public List<Class<? extends Task>> dependsOn() {
        List<Class<? extends Task>> task = new ArrayList<>();
        return task;
    }*/

    @Override
    public void run() {
        Log.i(MainActivity.READER_DEMO_TAG + DispatcherActivity.DISPATCHER_TAG,  "DispatchTest3 content running");
        SystemClock.sleep(1000);
    }
}
