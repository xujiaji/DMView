package io.xujiaji.dmlib.barrage.builder;

public class Msg extends Builder {
    public Builder msg(String msg) {
        Builder._msg = msg;
        return this;
    }
}