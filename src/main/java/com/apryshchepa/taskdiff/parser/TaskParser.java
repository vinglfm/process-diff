package com.apryshchepa.taskdiff.parser;

import com.apryshchepa.taskdiff.model.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TaskParser {
    private static final int NAME = 1;
    private static final int PID = 2;
    private static final int SESSION_NAME = 3;
    private static final int SESSION_ID = 4;
    private static final int MEM_USAGE = 5;
    private final Pattern pattern = Pattern.compile("\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"");

    public Task parse(final String line) {
        Matcher matcher = this.pattern.matcher(line);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Not a valid format");
        }
        String name = matcher.group(NAME);
        Integer pid = Integer.parseInt(matcher.group(PID));
        String sessionName = matcher.group(SESSION_NAME);
        String sessionId = matcher.group(SESSION_ID);
        String memUsage = matcher.group(MEM_USAGE);
        return new Task(pid, name, sessionName, sessionId, memUsage);
    }
}
