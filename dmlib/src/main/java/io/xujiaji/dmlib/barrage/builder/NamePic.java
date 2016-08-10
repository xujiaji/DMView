package io.xujiaji.dmlib.barrage.builder;

public class NamePic extends Builder {
    public Name picUrl(String url) {
        Builder._picUrl = url;
        return new Name();
    }

    public Pic name(String name) {
        Builder._name = name;
        return new Pic();
    }

}