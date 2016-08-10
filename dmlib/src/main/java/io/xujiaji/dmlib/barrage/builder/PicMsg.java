package io.xujiaji.dmlib.barrage.builder;

public class PicMsg extends Builder {
    public Pic msg(String msg) {
        Builder._msg = msg;
        return new Pic();
    }

    public Msg picUrl(String url) {
        Builder._picUrl = url;
        return new Msg();
    }
}