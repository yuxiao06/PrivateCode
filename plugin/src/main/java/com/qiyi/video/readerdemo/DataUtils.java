package com.qiyi.video.readerdemo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;

import java.lang.reflect.Method;
import java.util.HashMap;

public class DataUtils {

    public static String reflectClass = "com.qiyi.video.readerdemo.plugin.reflect.DataProvideInterface";

    public static <T> T createApi(Class<T> tClass) {
        try {
            Class clazz = Class.forName(reflectClass);
            Method method = clazz.getDeclaredMethod("createApi", Class.class);
            T api = (T) method.invoke(null, tClass);
            return api;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HashMap<String, String> getMd5Params() {
        try {
            Class clazz = Class.forName(reflectClass);
            Method method1 = clazz.getDeclaredMethod("getMd5Params");
            HashMap<String, String> hashMap = (HashMap<String, String>) method1.invoke(null);
            return hashMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Resources getPluginResources(Context context) {
        try {
            Class clazz = Class.forName(reflectClass);
            Method method1 = clazz.getDeclaredMethod("getPluginResources", Context.class);
            return (Resources) method1.invoke(null, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String getPlugPath(Context context) {
        try {
            Class clazz = Class.forName(reflectClass);
            Method method1 = clazz.getDeclaredMethod("getPlugPath", Context.class);
            return (String) method1.invoke(null, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static View inflate(int layoutId, Context context) {
        Resources resources = DataUtils.getPluginResources(context);
        if (resources == null)
            return null;
        XmlPullParser parser = resources.getLayout(layoutId);
        View view = LayoutInflater.from(context).inflate(parser, null);
        return view;
    }
}
