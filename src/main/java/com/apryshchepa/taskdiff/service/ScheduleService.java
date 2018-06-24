package com.apryshchepa.taskdiff.service;

import java.util.concurrent.*;

public class ScheduleService {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    public void start(Runnable task, long period) {
        this.scheduler.scheduleAtFixedRate(task, 0, period, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        scheduler.shutdownNow();
    }
}
