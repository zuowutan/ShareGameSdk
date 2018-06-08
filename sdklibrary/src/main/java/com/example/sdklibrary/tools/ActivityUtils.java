package com.example.sdklibrary.tools;

import android.app.Activity;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by tzw on 2018/6/4.
 * 管理Activity工具类
 */

public class ActivityUtils{
        private static volatile ActivityUtils mInstance;

        /**
         * 所有打开的Activity
         */
        private final CopyOnWriteArrayList<Activity> mActivities;

        private ActivityUtils() {
            mActivities = new CopyOnWriteArrayList<>();
        }

        public static ActivityUtils getInstance() {
            if (mInstance == null) {
                synchronized (ActivityUtils.class) {
                    if (mInstance == null) {
                        mInstance = new ActivityUtils();
                    }
                }
            }
            return mInstance;
        }

        /**
         * 添加统一管理
         */
        public void attach(Activity activity) {
            mActivities.add(activity);
        }

        /**
         * 移除解绑 - 防止内存泄漏
         *
         * @param detachActivity
         */
        public synchronized void detach(Activity detachActivity) {
            int size = mActivities.size();
            for (int i = 0; i < size; i++) {
                if (mActivities.get(i) == detachActivity) {
                    mActivities.remove(i);
                    size--;
                    i--;
                }
            }
        }

        /**
         * 根据Activity的类名关闭 Activity
         */
        public void finish(Class<? extends Activity> activityClass) {
            for (int i = 0; i < mActivities.size(); i++) {
                Activity activity = mActivities.get(i);
                if (activity.getClass().getCanonicalName().equals(activityClass.getCanonicalName())) {
                    activity.finish();
                    break;
                }
            }
        }

        /**
         * 退出整个应用
         */
        public void exit() {
            int size = mActivities.size();
            for (int i = 0; i < size; i++) {
                Activity activity = mActivities.get(i);
                activity.finish();
            }
        }

        /**
         * 获取当前的Activity（最前面）
         */
        public Activity getCurrentActivity() {
            return mActivities.get(mActivities.size() - 1);
        }
}
