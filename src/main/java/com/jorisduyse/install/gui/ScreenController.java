package com.jorisduyse.install.gui;

import com.jorisduyse.install.domain.DomainController;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ScreenController {

    public static void changeScreenTo_InstallScreen(DomainController dc, Stage stage) {
        InstallScreen is = new InstallScreen(dc, stage);
        Scene scene = new Scene(is, 500, 400);
        setSceneShow(stage, scene);
    }

    public static void changeScreenTo_StartupScreen(DomainController dc, Stage stage) {
        StartupScreen ss = new StartupScreen(dc, stage);
        Scene scene = new Scene(ss, 500, 400);
        setSceneShow(stage, scene);
    }

    public static void changeScreenTo_InstallingScreen(DomainController dc, Stage stage) {
        InstallingScreen ings = new InstallingScreen(dc, stage);
        Scene scene = new Scene(ings, 500, 400);
        setSceneShow(stage, scene);
    }

    public static void changeScreenTo_SettingsScreen(DomainController dc, Stage stage) {
        SettingsScreen sets = new SettingsScreen(dc, stage);
        Scene scene = new Scene(sets, 500, 400);
        setSceneShow(stage, scene);
    }

    public static void setSceneShow(Stage stage, Scene scene) {
        scene.getStylesheets().add("css/dark-theme.css");

        stage.setTitle("InstAll!");
        stage.setScene(scene);
        stage.show();
    }
}
