package com.apryshchepa.taskdiff.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Task {
    private Integer pid;
    private String imageName;
    private String sessionName;
    private String sessionId;
    private String memUsage;
}
