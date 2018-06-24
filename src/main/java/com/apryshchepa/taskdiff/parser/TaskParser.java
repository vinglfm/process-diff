package com.apryshchepa.taskdiff.parser;

import com.apryshchepa.taskdiff.model.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskParser {
    private final Pattern pattern = Pattern.compile("\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"");

    public Task parse(String line) {
        Matcher matcher = this.pattern.matcher(line);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Not a valid format");
        }
        String name = matcher.group(1);
        Integer pid = Integer.parseInt(matcher.group(2));
        String sessionName = matcher.group(3);
        String sessionId = matcher.group(4);
        String memUsage = matcher.group(5);
        return new Task(pid, name, sessionName, sessionId, memUsage);
    }
}
