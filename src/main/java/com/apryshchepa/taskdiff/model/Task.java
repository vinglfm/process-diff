package com.apryshchepa.taskdiff.model;

public final class Task {
    private String elem;

    public Task() {
        this("");
    }

    public Task(String elem) {
        this.elem = elem;
    }

    public String getElem() {
        return elem;
    }

    public void setElem(String elem) {
        this.elem = elem;
    }
}
