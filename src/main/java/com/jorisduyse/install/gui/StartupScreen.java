package com.jorisduyse.install.gui;

import com.jorisduyse.install.domain.DomainController;
import com.jorisduyse.install.domain.LoadFiles;


import javafx.application.Platform;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class StartupScreen extends GridPane {
    private Stage stage;
    private File selectedDir;
    private DomainController dc;

    private Label lblTitelStartup;
    private Text txtInfo;

    private Button btnLoadZip;
    private Button btnLoadDir;
    private Button btnSettings;
    private Button btnQuit;

    public StartupScreen(DomainController dc, Stage stage) {
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
            int exitCode7z = 1; // by default, we expect 7zip to crash
            try {
                exitCode7z = dc.runCMD("C:\\Program Files\\7-Zip\\7z.exe");
            } catch (IOException e) {
                errorMessage("7z missing", "Failed to find 7zip in program files, please download 7zip and try again.");
            } catch (InterruptedException e) {
                errorMessage("7z test interrupted", "The install check of 7zip was interrupted");
            }
            if (exitCode7z == 0) {
                System.err.println("dependency 7zip located in program files!");

            } else {
                System.err.println("7zip not installed; exit code: " + exitCode7z);
                errorMessage("7zip missing","7zip not installed; exit code: " + exitCode7z);
            }

            FileChooser zipFileChooser = new FileChooser();
            File zipFile = zipFileChooser.showOpenDialog(stage);

            if (zipFile != null && exitCode7z == 0) {
                dc.setProgramName(dc.getZipName(zipFile));
                dc.setOriginDir(zipFile.getAbsolutePath());
                try {
                    dc.unzip(zipFile.getAbsolutePath(), zipFile.getParent() + "\\" + dc.getZipName(zipFile));
                } catch (IOException e) {
                    e.printStackTrace(); //TODO explain error to user
                } catch (InterruptedException e) {
                    e.printStackTrace(); //TODO explain error to user
                }

                System.err.println("Selectid zip file:" + zipFile.getName() + "\nZip file directory: " + dc.getOriginDir() + "\nExtracting to: " + zipFile.getParent());

                ScreenController.changeScreenTo_InstallScreen(dc, (Stage) (this.getScene().getWindow()));
            } else if (zipFile == null) {
                System.err.println("No Zip file selected!");
                errorMessage("No Zip file", "Failed to select a Zip file, please try again or load a directory instead.");
            }
        });

        btnLoadDir.setOnMouseClicked(evt -> {
            selectedDir = LoadFiles.getDirectoryChooser().showDialog(stage);

            if (selectedDir != null) {
                dc.setProgramName(selectedDir.getName());
                dc.setOriginDir(selectedDir.toString());
                System.err.println("Directory selected: " + selectedDir.toString());
                ScreenController.changeScreenTo_InstallScreen(dc, stage);
//                changeScreenToInstallScreen();
            } else {
                System.err.println("No Directory selected!");
                errorMessage("No directory", "Failed to select a directory, please try again or load a zip instead.");
            }


        });

        btnSettings.setOnMouseClicked(evt -> {
            ScreenController.changeScreenTo_SettingsScreen(dc, stage);
        });

        btnQuit.setOnMouseClicked(evt -> {
            Platform.exit();
        });
    }

     private void errorMessage(String ErrorTitle, String ErrorMessage) {
        Alert popup = new Alert(Alert.AlertType.ERROR);
        popup.setTitle(ErrorTitle);
        popup.setHeaderText(ErrorMessage);
        popup.showAndWait();

    }

}
