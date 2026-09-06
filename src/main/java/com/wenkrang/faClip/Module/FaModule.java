package com.wenkrang.faClip.module;

public interface FaModule {
    void auto();
    void close();
    void status();

    enum Status {
        READY,
        STARTING,
        STOPPED
    }
}
