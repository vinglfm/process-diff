package com.apryshchepa.taskdiff;

import com.apryshchepa.taskdiff.config.AppContextConfig;
import com.apryshchepa.taskdiff.fxml.SpringFXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppContextConfig.class);
        Parent root = new SpringFXMLLoader<Parent>(context).load(getClass().getClassLoader().getResource("application.fxml"));

        primaryStage.setTitle("ProDiff");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
