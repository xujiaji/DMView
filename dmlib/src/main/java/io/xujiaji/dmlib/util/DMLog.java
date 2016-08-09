package io.xujiaji.dmlib.util;

import android.util.Log;

/**
 * Bullet screen Log
 */
public final class DMLog {
    private DMLog() {}
    public static final boolean isClose = false;
    public static void e(String log) {
        if (isClose) return;
        Log.e("DMLog", log);
    }
    public static void i(String log) {
        if (isClose) return;
        Log.i("DMLog", log);
    }
    public static void d(String log) {
        if (isClose) return;
        Log.d("DMLog", log);
    }
    public static void v(String log) {
        if (isClose) return;
        Log.v("DMLog", log);
    }
}
