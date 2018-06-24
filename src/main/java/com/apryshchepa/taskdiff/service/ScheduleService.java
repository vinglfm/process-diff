package com.apryshchepa.taskdiff.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void start(Runnable task, long period) {
        scheduler.scheduleAtFixedRate(task, 0, period, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        scheduler.shutdownNow();
    }
}
