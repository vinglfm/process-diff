package com.apryshchepa.taskdiff.model;

import lombok.Data;

@Data
public final class Task {
    private Integer pid;
    private String imageName;
    private String sessionName;
    private String sessionId;
    private String memUsage;

    public Task(Integer pid, String imageName, String sessionName, String sessionId, String memUsage) {
        this.pid = pid;
        this.imageName = imageName;
        this.sessionName = sessionName;
        this.sessionId = sessionId;
        this.memUsage = memUsage;
    }
}
