package com.wenkrang.faClip.module.FaInterface;

import java.util.HashMap;
import java.util.Map;

public class FaIntfContext {
    private final Map<String, Object> data = new HashMap<>();

    public <T> void set(String key, T value) {
        data.put(key, value);
    }

    public <T> T get(String key) {
        return (T) data.get(key);
    }
}
