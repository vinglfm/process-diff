package com.apryshchepa.taskdiff.model;

import java.util.Objects;

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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(imageName, task.imageName) &&
                Objects.equals(pid, task.pid) &&
                Objects.equals(sessionName, task.sessionName) &&
                Objects.equals(sessionId, task.sessionId) &&
                Objects.equals(memUsage, task.memUsage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageName, pid, sessionName, sessionId, memUsage);
    }
}
