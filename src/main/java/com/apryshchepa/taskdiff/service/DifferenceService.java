package com.apryshchepa.taskdiff.service;

import com.apryshchepa.taskdiff.model.Task;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DifferenceService {

    public Map<Integer, Status> compare(List<Task> liveTasks, List<Task> snapshotTasks) {
        Map<Integer, Task> liveMap = liveTasks.stream().collect(Collectors.toMap(Task::getPid, Function.identity()));

        return snapshotTasks.stream().map(task -> {
            Task liveTask = liveMap.get(task.getPid());
            Status status;
            if (liveTask == null) {
                status = Status.NEW;
            } else if (task.equals(liveTask)) {
                status = Status.NOT_CHANGED;
            } else {
                status = Status.CHANGED;
            }
            return new SimpleEntry<>(task.getPid(), status);
        }).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
    }
}
