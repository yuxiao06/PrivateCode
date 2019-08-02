package com.qiyi.video.readerdemo.plugin;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.qiyi.video.readerdemo.plugin.reflect.DataProvideInterface;
import com.qiyi.video.readerdemo.plugin.utils.PlugResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginActivity extends Activity {
    private Object UIPresenter;
    private Class UIPresenterClazz;
    private String layout;
    private String presenter;
    private String apkPath;
    private Resources resources;
    private Resources.Theme theme;
    private ClassLoader classLoader;
    private View containerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            copyApk();
            initData();
            setContentView(containerView);
            UIPresenter = UIPresenterClazz.newInstance();
            Method method = UIPresenterClazz.getDeclaredMethod("onCreate", View.class);
            method.invoke(UIPresenter, containerView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initData() throws ClassNotFoundException {
        apkPath = DataProvideInterface.getPlugPath(this);
        setTheme(android.R.style.Theme_Translucent_NoTitleBar);
        layout = PluginConfig.getLayoutName(this.getClass().getName());
        presenter = PluginConfig.getPresenter(this.getClass().getName());
        resources = PlugResourceUtils.getPluginResources(this, apkPath);
        String dexPathD = getFilesDir().getPath() + PluginConfig.optimizedDirectory;
        File file = new File(dexPathD);
        if (!file.exists()) {
            file.mkdirs();
        }
        classLoader = new DexClassLoader(apkPath, dexPathD, null, PlugResourceUtils.class.getClassLoader());
        UIPresenterClazz = classLoader.loadClass(presenter);
        theme = resources.newTheme();
        theme.setTo(super.getTheme());
        containerView = PlugResourceUtils.getPlugView(layout, "layout", getPackageName(), this, resources);
    }


    public void copyApk() {
        InputStream is = null;
        FileOutputStream output = null;
        try {
            is = getAssets().open("patch.apk");
            String dir = getFilesDir() + "/plugin/";
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdirs();
            }
            apkPath = DataProvideInterface.getPlugPath(this);
            output = new FileOutputStream(new File(apkPath));
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buf)) != -1) {
                output.write(buf, 0, bytesRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Resources getResources() {
        return resources == null ? super.getResources() : resources;
    }

    @Override
    public AssetManager getAssets() {
        return resources == null ? super.getAssets() : resources.getAssets();
    }

    public ClassLoader getClassLoader() {
        return classLoader == null ? super.getClassLoader() : classLoader;
    }

    @Override
    public Resources.Theme getTheme() {
        return theme == null ? super.getTheme() : theme;
    }

}
