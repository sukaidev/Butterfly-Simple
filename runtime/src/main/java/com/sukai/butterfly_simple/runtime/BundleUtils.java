package com.sukai.butterfly_simple.runtime;

import android.os.Bundle;

/**
 * Created by sukaidev on 2019/10/19.
 */
public class BundleUtils {

    public static <T> T get(Bundle bundle, String key) {
        return (T) bundle.get(key);
    }

    public static <T> T get(Bundle bundle, String key, Object defaultValue) {
        Object object = bundle.get(key);
        if (object == null) {
            object = defaultValue;
        }
        return (T) object;
    }
}
