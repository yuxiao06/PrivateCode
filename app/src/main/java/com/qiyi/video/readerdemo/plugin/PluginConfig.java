package com.qiyi.video.readerdemo.plugin;

import java.util.HashMap;
import java.util.Map;

public class PluginConfig {
    private static final Map<String, ActivityModel> config = new HashMap<>();
    public static final String optimizedDirectory = "/test/";

    public static void putPresenter(String activityName, String layoutName, String presenterName) {
        ActivityModel model = new ActivityModel();
        model.layoutName = layoutName;
        model.presenterName = presenterName;
        config.put(activityName, model);
    }

    public static String getPresenter(String activityName) {
        ActivityModel model = config.get(activityName);
        if (model != null) return model.presenterName;
        return "";
    }

    public static String getLayoutName(String activityName) {
        ActivityModel model = config.get(activityName);
        if (model != null) return model.layoutName;
        return "";
    }

    public static class ActivityModel {
        public String layoutName;
        public String presenterName;
    }
}
