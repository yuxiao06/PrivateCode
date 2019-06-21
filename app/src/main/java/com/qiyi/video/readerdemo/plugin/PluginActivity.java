package com.qiyi.video.readerdemo.plugin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qiyi.video.readerdemo.plugin.reflect.DataProvideInterface;
import com.qiyi.video.readerdemo.plugin.utils.PlugResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

public class PluginActivity extends AppCompatActivity {
    private Object UIPresenter;
    private Class UIPresenterClazz;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            InputStream is = getAssets().open("patch.apk");
            String dir = getFilesDir() + "/plugin/";
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdirs();
            }
            String filePath = DataProvideInterface.getPlugPath(this);
            FileOutputStream output = null;
            try {
                output = new FileOutputStream(new File(filePath));
                byte[] buf = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buf)) != -1) {
                    output.write(buf, 0, bytesRead);
                }
            } catch (Exception e) {

            } finally {
                is.close();
                output.close();
            }

            String layout = PluginConfig.getLayoutName(this.getClass().getName());
            String presenter = PluginConfig.getPresenter(this.getClass().getName());
            View view = PlugResourceUtils.getPlugView(layout, "layout", getPackageName(), this, filePath);
            setContentView(view);
            UIPresenterClazz = PlugResourceUtils.loadClass(presenter, this, filePath);
            UIPresenter = UIPresenterClazz.newInstance();
            Method method = UIPresenterClazz.getDeclaredMethod("onCreate", View.class);
            method.invoke(UIPresenter, view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
