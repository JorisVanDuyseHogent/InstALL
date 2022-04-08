package com.jorisduyse.install.main;

import com.jorisduyse.install.domain.DomainController;
import com.jorisduyse.install.gui.StartupScreen;
import com.jorisduyse.install.resources.ResourceController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartUp extends Application {
    private String lang;
    private String settings;
    private static Stage stage;

    @Override
    public void start(Stage stage){
        this.stage = stage;
        DomainController dc = new DomainController();
        StartupScreen ss = new StartupScreen(stage, dc);
        Scene scene = new Scene(ss, 500, 400);
        stage.setTitle("InstAll!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }
}