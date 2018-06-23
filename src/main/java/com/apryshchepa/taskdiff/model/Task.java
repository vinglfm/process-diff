package com.apryshchepa.taskdiff.model;

public final class Task {
    private String imageName;
    private String pid;
    private String sessionName;
    private String sessionId;
    private String memUsage;

    public Task(String imageName, String pid, String sessionName, String sessionId, String memUsage) {
        this.imageName = imageName;
        this.pid = pid;
        this.sessionName = sessionName;
        this.sessionId = sessionId;
        this.memUsage = memUsage;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMemUsage() {
        return memUsage;
    }

    public void setMemUsage(String memUsage) {
        this.memUsage = memUsage;
    }
}
