package com.qiyi.video.readerdemo.plugin.reflect;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;


public class DataProvideInterface {

    public static String getPlugPath(Context context) {
        return context.getFilesDir() + "/plugin/patch.apk";
    }

    public static Resources getPluginResources(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageArchiveInfo(getPlugPath(context), PackageManager.GET_ACTIVITIES);
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            applicationInfo.sourceDir = getPlugPath(context);
            applicationInfo.publicSourceDir = getPlugPath(context);
            Resources resources = packageManager.getResourcesForApplication(applicationInfo);
            return resources;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
