package com.apryshchepa.taskdiff.controller;

import com.apryshchepa.taskdiff.model.Task;
import com.apryshchepa.taskdiff.service.ScheduleService;
import com.apryshchepa.taskdiff.service.Status;
import com.apryshchepa.taskdiff.service.TaskLoadService;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class TaskLoaderController {
    private static final int PERIOD = 2000;

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

    private final ScheduleService scheduleService;
    private final TaskLoadService taskLoadService;

    public TaskLoaderController() {
        //TODO: whether check if this is Window or delegate creation
        this(new ScheduleService(), new TaskLoadService());
    }

    private TaskLoaderController(ScheduleService scheduleService, TaskLoadService taskLoadService) {
        this.scheduleService = scheduleService;
        this.taskLoadService = taskLoadService;
    }

    @FXML
    public void initialize() {
        initLiveTableColumns();
        initSnapshotTableColumns();
        scheduleLiveView();
        initRowFactory();
    }

    private void initRowFactory() {
        this.liveView.setRowFactory(tableView -> {
            TableRow<Task> row = new TableRow<>();
            ObjectBinding<Status> contains = Bindings.createObjectBinding(() -> {
                if (row.getItem() != null) {
                    Integer pid = row.getItem().getPid();
                    return this.taskLoadService.statuses().getOrDefault(pid, Status.NEW);
                }
                return Status.NEW;
            }, row.itemProperty());
            row.styleProperty().bind(Bindings.when(contains.isEqualTo(Status.CHANGED))
                    .then("-fx-background-color: grey;")
                    .otherwise(Bindings.when(contains.isEqualTo(Status.NEW))
                            .then("-fx-background-color: green;")
                            .otherwise("")));
            return row;
        });
    }

    private void scheduleLiveView() {
        this.scheduleService.start(() -> {
            List<Task> tasks = this.taskLoadService.load();
            this.liveView.setItems(FXCollections.observableArrayList(tasks));
        }, PERIOD);
    }

    private void initSnapshotTableColumns() {
        this.snapImageNameColumn.setCellValueFactory(new PropertyValueFactory<>("imageName"));
        this.snapPidColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));
        this.snapSessionNameColumn.setCellValueFactory(new PropertyValueFactory<>("sessionName"));
        this.snapSessionIdColumn.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        this.snapMemUsageColumn.setCellValueFactory(new PropertyValueFactory<>("memUsage"));
    }

    private void initLiveTableColumns() {
        this.imageNameColumn.setCellValueFactory(new PropertyValueFactory<>("imageName"));
        this.pidColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));
        this.sessionNameColumn.setCellValueFactory(new PropertyValueFactory<>("sessionName"));
        this.sessionIdColumn.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        this.memUsageColumn.setCellValueFactory(new PropertyValueFactory<>("memUsage"));
    }

    public void snapshot() {
        List<Task> snapshot = this.taskLoadService.snapshot();
        this.snapshotView.setItems(FXCollections.observableArrayList(snapshot));
        this.liveView.refresh();
    }
}
