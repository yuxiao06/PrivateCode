package com.qiyi.video.readerdemo.dispatcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qiyi.video.readerdemo.MainActivity;
import com.qiyi.video.readerdemo.R;
import com.qiyi.video.readerdemo.dispatcher.task.DispatchTest1;
import com.qiyi.video.readerdemo.dispatcher.task.DispatchTest2;
import com.qiyi.video.readerdemo.dispatcher.task.DispatchTest3;

public class DispatcherActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String DISPATCHER_TAG = "Dispatcher";

    TextView resultTv;
    Button startBt;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_dispatcher);

        resultTv = findViewById(R.id.tv_result);
        startBt = findViewById(R.id.bt_start_task);

        startBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start_task:
                resultTv.setText(System.currentTimeMillis() + "   任务已启动，请查看log输出  tag: ReaderDemo_Dispatcher");
                startDispatcher();
                break;
        }
    }

    private void startDispatcher() {
        TaskDispatcher.init(this);
        TaskDispatcher dispatcherFirst = TaskDispatcher.createInstance();
        dispatcherFirst
                .addTask(new DispatchTest1())
                .addTask(new DispatchTest2())
                .addTask(new DispatchTest3())
                .start();
        Log.i(MainActivity.READER_DEMO_TAG + DispatcherActivity.DISPATCHER_TAG,  "dispatcherFirst start run");

        dispatcherFirst.await();
        Log.i(MainActivity.READER_DEMO_TAG + DispatcherActivity.DISPATCHER_TAG,  "dispatcherFirst all finish");
    }
}
