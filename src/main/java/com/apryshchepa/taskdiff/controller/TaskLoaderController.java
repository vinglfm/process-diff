package com.apryshchepa.taskdiff.controller;

import com.apryshchepa.taskdiff.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TaskLoaderController {

    private String loadCommand;

    @FXML
    private TableView<Task> liveView;

    private ObservableList<Task> itemList;

    @FXML
    public void initialize() {
        List<Task> tasks = load();
        itemList = FXCollections.observableArrayList(tasks);
        liveView.setItems(itemList);
    }

    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try (InputStream inputStream = Runtime.getRuntime().exec("tasklist").getInputStream();
             BufferedReader input = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = input.readLine()) != null) {
                tasks.add(new Task(line));
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return tasks;
    }
}
