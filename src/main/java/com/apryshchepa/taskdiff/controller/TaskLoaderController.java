package com.apryshchepa.taskdiff.controller;

import com.apryshchepa.taskdiff.model.Task;
import com.apryshchepa.taskdiff.service.ScheduleService;
import com.apryshchepa.taskdiff.service.TaskLoader;
import com.apryshchepa.taskdiff.service.WindowsTaskLoader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @FXML
    private TableView<Task> snapshotView;
    @FXML
    private TableColumn<Task, String> snapImageNameColumn;
    @FXML
    private TableColumn<Task, String> snapPidColumn;
    @FXML
    private TableColumn<Task, String> snapSessionNameColumn;
    @FXML
    private TableColumn<Task, String> snapSessionIdColumn;
    @FXML
    private TableColumn<Task, String> snapMemUsageColumn;

    private TaskLoader taskLoader;

    private ScheduleService scheduleService;

    public TaskLoaderController() {
        //TODO: whether check if this is Window or delegate creation
        this(new ScheduleService(), new WindowsTaskLoader());
    }

    private TaskLoaderController(ScheduleService scheduleService, TaskLoader taskLoader) {
        this.scheduleService = scheduleService;
        this.taskLoader = taskLoader;
    }

    @FXML
    public void initialize() {
        initLiveTableColumns();
        initSnapshotTableColumns();
        scheduleService.start(() -> reload(liveView), 1000);
    }

    private void initSnapshotTableColumns() {
        snapImageNameColumn.setCellValueFactory(new PropertyValueFactory<>("imageName"));
        snapPidColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));
        snapSessionNameColumn.setCellValueFactory(new PropertyValueFactory<>("sessionName"));
        snapSessionIdColumn.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        snapMemUsageColumn.setCellValueFactory(new PropertyValueFactory<>("memUsage"));
    }

    private void initLiveTableColumns() {
        imageNameColumn.setCellValueFactory(new PropertyValueFactory<>("imageName"));
        pidColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));
        sessionNameColumn.setCellValueFactory(new PropertyValueFactory<>("sessionName"));
        sessionIdColumn.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        memUsageColumn.setCellValueFactory(new PropertyValueFactory<>("memUsage"));
    }

    public void snapshot() {
        reload(snapshotView);
    }

    private void reload(TableView<Task> view) {
        List<Task> tasks = taskLoader.load();
        view.setItems(FXCollections.observableArrayList(tasks));
    }
}
