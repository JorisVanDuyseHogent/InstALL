package com.jorisduyse.install.gui;

import com.jorisduyse.install.domain.Check7zInstall;
import com.jorisduyse.install.domain.DomainController;
import com.jorisduyse.install.domain.LoadFiles;

import javafx.application.Platform;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class StartupScreen extends GridPane {
    private Stage stage;
    private File selectedDir;
    private InstallScreen installScreen;
    private DomainController dc;

    private Label lblTitelStartup;
    private Text txtInfo;

    private Button btnLoadZip;
    private Button btnLoadDir;
    private Button btnSettings;
    private Button btnQuit;

    public StartupScreen(Stage stage, DomainController dc) {
        this.stage = stage;
        this.dc = dc;
        buildGui();
    }

    private void buildGui() {
        this.setAlignment(Pos.CENTER);

        // ------------------ Settings for all javaFx objects and load them ------------------

        lblTitelStartup = new Label();
        lblTitelStartup.setText("InstAll"); //TODO ResourceController.getTranslation("Title")
        lblTitelStartup.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(lblTitelStartup, 0, 0);

        txtInfo = new Text();
        txtInfo.setText("This application will install programs that do not come in the form of an installer, " +
                "\nbut rather as a zip, or directory."); //TODO ResourceController.getTranslation("Info")
        this.add(txtInfo, 0, 1);

        btnLoadZip = new Button();
        btnLoadZip.setText("Load zip");
        btnLoadZip.setMaxWidth(500);
        this.add(btnLoadZip, 0,2);

        btnLoadDir = new Button();
        btnLoadDir.setText("Load directory");
        btnLoadDir.setMaxWidth(500);
        this.add(btnLoadDir, 0,3);

        btnSettings = new Button();
        btnSettings.setText("Settings");
        btnSettings.setMaxWidth(500);
        this.add(btnSettings, 0,4);

        btnQuit = new Button();
        btnQuit.setText("Quit");
        btnQuit.setMaxWidth(500);
        this.add(btnQuit,0,5);

        // ------------------------------------ All javaFX actions ------------------------------------
        btnLoadZip.setOnMouseClicked(evt -> {
            if (Check7zInstall.is7zipInstalled()) {
                System.err.println("7zip located!");
                try {
                    Process process = Runtime.getRuntime().exec("C:\\Program Files\\7-Zip\\7zip.exe");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else System.err.println("7zip not installed!");

        });

        btnLoadDir.setOnMouseClicked(evt -> {
            selectedDir = LoadFiles.getDirectoryChooser().showDialog(stage);

            dc.setProgramName(selectedDir.getName());
            dc.setOriginDir(selectedDir.toString());


            if (selectedDir != null) {
                System.err.println("Directory selected: " + selectedDir.toString());
                installScreen = new InstallScreen(StartupScreen.this, dc);
                Scene scene = new Scene(installScreen, 500, 400);
                Stage stage = (Stage) (this.getScene().getWindow());
                stage.setScene(scene);
                stage.show();
            } else {
                System.err.println("No Directory selected!");
            }


        });

        btnQuit.setOnMouseClicked(evt -> {
            Platform.exit();
        });
    }


}
