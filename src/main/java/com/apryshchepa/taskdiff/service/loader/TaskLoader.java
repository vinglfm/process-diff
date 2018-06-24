package com.apryshchepa.taskdiff.service.loader;

import com.apryshchepa.taskdiff.model.Task;

import java.util.List;

public interface TaskLoader {
    List<Task> load();
}
