package com.apryshchepa.taskdiff.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@Service
public class TaskLoader {

    @Value("${task.load.command}")
    private String loadCommand;

    public List<String> load() {
        System.out.print(loadCommand);
        try (InputStream inputStream = Runtime.getRuntime().exec(loadCommand).getInputStream();
             BufferedReader input = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
//
//        Process p = Runtime.getRuntime().exec
//                (System.getenv("windir") + "\\system32\\" + "tasklist.exe");

        return Collections.emptyList();
    }

}
