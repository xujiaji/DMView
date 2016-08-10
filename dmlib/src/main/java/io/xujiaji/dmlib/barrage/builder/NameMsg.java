package io.xujiaji.dmlib.barrage.builder;

public class NameMsg extends Builder {

    public Name msg(String msg) {
        Builder._msg = msg;
        return new Name();
    }

    public Msg name(String name) {
        Builder._name = name;
        return new Msg();
    }
}
