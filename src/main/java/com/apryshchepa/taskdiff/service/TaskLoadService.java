package com.apryshchepa.taskdiff.service;

import com.apryshchepa.taskdiff.model.Task;
import com.apryshchepa.taskdiff.service.loader.TaskLoader;
import com.apryshchepa.taskdiff.service.loader.WindowsTaskLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TaskLoadService {
    private static final Logger LOG = LoggerFactory.getLogger(TaskLoadService.class);

    private final TaskLoader taskLoader;
    private final DifferenceService differenceService;

    private List<Task> snapshotList = Collections.emptyList();
    private List<Task> liveList = Collections.emptyList();
    private Map<Integer, Status> taskStatuses = Collections.emptyMap();

    public TaskLoadService() {
        this(new WindowsTaskLoader(), new DifferenceService());
    }

    private TaskLoadService(TaskLoader taskLoader, DifferenceService differenceService) {
        this.taskLoader = taskLoader;
        this.differenceService = differenceService;
    }

    public Map<Integer, Status> statuses() {
        return taskStatuses;
    }

    public List<Task> load() {
        LOG.info("Loading live tasks");
        this.liveList = this.taskLoader.load();
        this.taskStatuses = this.differenceService.compare(this.liveList, this.snapshotList);
        return liveList;
    }

    public List<Task> snapshot() {
        LOG.info("Capturing snapshot");
        this.snapshotList = this.liveList;
        this.taskStatuses = this.differenceService.compare(this.liveList, this.snapshotList);
        return snapshotList;
    }
}
