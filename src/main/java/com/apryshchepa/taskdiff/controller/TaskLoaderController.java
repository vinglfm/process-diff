package com.apryshchepa.taskdiff.controller;

import com.apryshchepa.taskdiff.model.Task;
import com.apryshchepa.taskdiff.parser.TaskParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskLoaderController {

    @FXML
    private TableView<Task> liveView;
    @FXML
    private TableColumn<Task, String> imageNameColumn;
    @FXML
    private TableColumn<Task, String> pidColumn;
    @FXML
    private TableColumn<Task, String> sessionNameColumn;
    @FXML
    private TableColumn<Task, String> sessionIdColumn;
    @FXML
    private TableColumn<Task, String> memUsageColumn;

    private ObservableList<Task> itemList;

    private TaskParser taskParser;

    public TaskLoaderController() {
        this(new TaskParser());
    }

    private TaskLoaderController(TaskParser taskParser) {
        this.taskParser = taskParser;
    }

    @FXML
    public void initialize() {
        imageNameColumn.setCellValueFactory(new PropertyValueFactory<>("imageName"));
        pidColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));
        sessionNameColumn.setCellValueFactory(new PropertyValueFactory<>("sessionName"));
        sessionIdColumn.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        memUsageColumn.setCellValueFactory(new PropertyValueFactory<>("memUsage"));

        List<Task> tasks = load();
        itemList = FXCollections.observableArrayList(tasks);
        liveView.setItems(itemList);
    }

    private List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try (InputStream inputStream = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh").getInputStream();
             BufferedReader input = new BufferedReader(new InputStreamReader(inputStream))) {
            String task;
            while ((task = input.readLine()) != null) {
                tasks.add(taskParser.parse(task));
            }
        } catch (IOException exp) {
            System.err.println(exp.getMessage());
            tasks = Collections.emptyList();
        }
        return tasks;
    }
}
