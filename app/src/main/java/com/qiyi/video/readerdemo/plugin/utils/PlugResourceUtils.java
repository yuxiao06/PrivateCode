package com.qiyi.video.readerdemo.plugin.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;

import com.qiyi.video.readerdemo.plugin.PluginConfig;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;

import dalvik.system.DexClassLoader;

public class PlugResourceUtils {
    public static Resources getPluginResources(Context context, String apkPath) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            applicationInfo.sourceDir = apkPath;
            applicationInfo.publicSourceDir = apkPath;
            Resources resources = packageManager.getResourcesForApplication(applicationInfo);
            return resources;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static View getPlugView(String viewName, String type, String packageName, Context context, Resources resources) {
        int id = resources.getIdentifier(viewName, type, packageName);
        XmlPullParser xmlPullParser = resources.getLayout(id);
        View view = LayoutInflater.from(context).inflate(xmlPullParser, null);
        return view;
    }

}
