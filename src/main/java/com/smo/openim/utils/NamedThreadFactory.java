package com.smo.openim.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jianjunyang on 15/3/25.
 */
public class NamedThreadFactory implements ThreadFactory {
    private final ThreadGroup threadGroup;
    private final String prefix;
    private final boolean isDaemon;

    private final String threadNamePrefix = "im";

    private final AtomicInteger sequence = new AtomicInteger(0);

    public NamedThreadFactory(String threadNamePrefix) {
        this(threadNamePrefix, false);
    }

    public NamedThreadFactory(String threadNamePrefix, boolean isDaemon) {
        SecurityManager sm = System.getSecurityManager();
        this.threadGroup = (sm == null) ? Thread.currentThread().getThreadGroup() : sm.getThreadGroup();
        this.prefix = threadNamePrefix + "-thread-";
        this.isDaemon = isDaemon;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        final String name = prefix + sequence.getAndIncrement();
        Thread thread = new Thread(threadGroup, runnable, name, 0);
        thread.setDaemon(isDaemon);
        return thread;

    }
}
