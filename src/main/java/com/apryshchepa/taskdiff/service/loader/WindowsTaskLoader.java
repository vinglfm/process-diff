package com.apryshchepa.taskdiff.service.loader;

import com.apryshchepa.taskdiff.model.Task;
import com.apryshchepa.taskdiff.parser.TaskParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WindowsTaskLoader implements TaskLoader {
    private static final Logger LOG = LoggerFactory.getLogger(WindowsTaskLoader.class);

    private static final String EXEC = "tasklist.exe /fo csv /nh";

    private TaskParser taskParser;

    public WindowsTaskLoader() {
        this(new TaskParser());
    }

    private WindowsTaskLoader(TaskParser taskParser) {
        this.taskParser = taskParser;
    }

    @Override
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try (InputStream inputStream = Runtime.getRuntime().exec(EXEC).getInputStream();
             BufferedReader input = new BufferedReader(new InputStreamReader(inputStream))) {
            String task;
            while ((task = input.readLine()) != null) {
                tasks.add(this.taskParser.parse(task));
            }
        } catch (IOException exp) {
            LOG.error(exp.getMessage());
            tasks = Collections.emptyList();
        }
        LOG.info("Loaded {} tasks", tasks.size());
        return tasks;
    }
}
