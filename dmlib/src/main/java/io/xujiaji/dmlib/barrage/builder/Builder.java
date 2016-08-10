package io.xujiaji.dmlib.barrage.builder;

import io.xujiaji.dmlib.barrage.Show;

public class Builder {
    protected static String _picUrl;
    protected static String _name;
    protected static String _msg;

    public All call() {
        return new All();
    }

    public static void clear() {
        _picUrl = null;
        _name = null;
        _msg = null;
    }

    public static String get_picUrl() {
        return _picUrl;
    }

    public static String get_name() {
        return _name;
    }

    public static String get_msg() {
        return _msg;
    }

    public void show() {
        Show.show();
    }
}