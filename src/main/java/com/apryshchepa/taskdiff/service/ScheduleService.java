package com.apryshchepa.taskdiff.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class ScheduleService {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleService.class);

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    public void start(final Runnable task, long period) {
        LOG.info("Starting schedule service for {} period", period);
        this.scheduler.scheduleAtFixedRate(task, 0, period, TimeUnit.MILLISECONDS);
    }
}
