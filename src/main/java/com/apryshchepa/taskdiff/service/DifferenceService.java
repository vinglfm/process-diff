package com.apryshchepa.taskdiff.service;

import com.apryshchepa.taskdiff.model.Task;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DifferenceService {

    public Map<Integer, Status> compare(List<Task> liveTasks, List<Task> snapshotTasks) {
        Map<Integer, Task> snapshotMap = snapshotTasks.stream().collect(Collectors.toMap(Task::getPid, Function.identity()));

        return liveTasks.stream().map(liveTask -> {
            Task snapshotTask = snapshotMap.get(liveTask.getPid());
            Status status;
            if (snapshotTask == null) {
                status = Status.NEW;
            } else if (liveTask.equals(snapshotTask)) {
                status = Status.NOT_CHANGED;
            } else {
                status = Status.CHANGED;
            }
            return new SimpleEntry<>(liveTask.getPid(), status);
        }).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
    }
}
