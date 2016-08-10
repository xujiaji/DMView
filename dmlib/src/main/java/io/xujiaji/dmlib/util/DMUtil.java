package io.xujiaji.dmlib.util;

/**
 * Created by jiana on 16-8-10.
 */
public class DMUtil {
    public static <T> T checkNotNull(T reference, String err) {
        if (reference == null) {
            throw new NullPointerException(err);
        }
        return reference;
    }

}
