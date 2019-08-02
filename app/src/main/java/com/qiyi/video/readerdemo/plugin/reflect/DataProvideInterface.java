package com.qiyi.video.readerdemo.plugin.reflect;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import com.qiyi.video.readerdemo.plugin.utils.PlugResourceUtils;


public class DataProvideInterface {

    public static String getPlugPath(Context context) {
        return context.getFilesDir() + "/plugin/patch.apk";
    }

    public static Resources getPluginResources(Context context) {
        return PlugResourceUtils.getPluginResources(context, getPlugPath(context));
    }
}
