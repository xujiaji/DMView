package io.xujiaji.dmlib.barrage.builder;

public class All extends Builder {

    public NameMsg picUrl(String url) {
        Builder._picUrl = url;
        return new NameMsg();
    }

    public PicMsg name(String name) {
        Builder._name = name;
        return new PicMsg();
    }

    public NamePic msg(String msg) {
        Builder._msg = msg;
        return new NamePic();
    }
}