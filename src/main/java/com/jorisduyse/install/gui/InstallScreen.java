package com.jorisduyse.install.gui;

import com.jorisduyse.install.domain.DomainController;
import com.jorisduyse.install.domain.LoadFiles;
import com.jorisduyse.install.main.StartUp;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.DirectoryNotEmptyException;
import java.util.ArrayList;
import java.util.List;

public class InstallScreen extends GridPane {
    private DomainController dc;
    private StartupScreen lastScreen;
    private InstallingScreen installingScreen;
    private final String[] INSTALL_DIRECTORIES = { "C:\\Program Files\\",
            "C:\\Program Files (x86)\\",
            "C:\\Users\\Qwert\\AppData\\Local\\"};

    private Label lblTitleSettings;
    private ComboBox<String> cbInstallDir;
    private List<String> stringInstallDirs = new ArrayList<>();
    private Text txtInstallDir;
    private CheckBox cbxStartMenu;
    private CheckBox cbxTaskBar;
    private CheckBox cbxDesktop;
    private CheckBox cbxStartUp;
    private Button btnApply;
    private Button btnGoBack;

    public InstallScreen(StartupScreen lastScreen, DomainController dc) {
        this.lastScreen = lastScreen;
        this.dc = dc;
        buildGui();
    }

    private void buildGui() {
        this.setAlignment(Pos.CENTER);

        // ------------------ Settings for all javaFx objects and load them ------------------

        lblTitleSettings = new Label();
        lblTitleSettings.setText("Install: " + dc.getProgramName());
        lblTitleSettings.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(lblTitleSettings, 0, 0);

        stringInstallDirs.add("Program Files/" +  dc.getProgramName());
        stringInstallDirs.add("Program Files x86/" +  dc.getProgramName());
        stringInstallDirs.add("AppData/Local/" +  dc.getProgramName() + " (non root)");
        stringInstallDirs.add("Custom");

        cbInstallDir = new ComboBox<>();
        cbInstallDir.setItems(FXCollections.observableArrayList(stringInstallDirs));
        cbInstallDir.setPromptText("Select install location");
        cbInstallDir.setMaxWidth(500);
        this.add(cbInstallDir, 0,1);

        txtInstallDir = new Text();
        txtInstallDir.setVisible(false);
        this.add(txtInstallDir, 0, 1);

        cbxStartMenu = new CheckBox("add to Start Menu");
        this.add(cbxStartMenu, 0, 2);

        cbxTaskBar = new CheckBox("add to Task bar");
        this.add(cbxTaskBar, 0, 3);

        cbxDesktop = new CheckBox("create Desktop shortcut");
        this.add(cbxDesktop, 0, 4);

        cbxStartUp = new CheckBox("Launch at startup");
        this.add(cbxStartUp, 0, 5);

        btnApply = new Button();
        btnApply.setText("Install");
        btnApply.setDisable(true);
        this.add(btnApply, 0, 6);

        btnGoBack = new Button();
        btnGoBack.setText("Return");
        btnGoBack.setAlignment(Pos.BOTTOM_CENTER);
        this.add(btnGoBack, 0, 7);



        // ------------------------------------ All javaFX actions ------------------------------------

        cbInstallDir.setOnAction(evt -> {
            int selectedDirIndex = cbInstallDir.getSelectionModel().getSelectedIndex();
            if (selectedDirIndex == 0) {
                dc.setTargetDir(INSTALL_DIRECTORIES[0] +  dc.getProgramName());
                System.err.println("Target dir set for: " + dc.getTargetDir());
                btnApply.setDisable(false);
            } else if (selectedDirIndex == 1) {
                dc.setTargetDir(INSTALL_DIRECTORIES[1] +  dc.getProgramName());
                System.err.println("Target dir set for: " + dc.getTargetDir());
                btnApply.setDisable(false);
            } else if (selectedDirIndex == 2) {
                dc.setTargetDir(INSTALL_DIRECTORIES[2] +  dc.getProgramName());
                System.err.println("Target dir set for: " + dc.getTargetDir());
                btnApply.setDisable(false);
            } else if (selectedDirIndex == 3) {

                File selectedDir = LoadFiles.getDirectoryChooser().showDialog(StartUp.getStage());

                if (selectedDir != null) {
                    dc.setTargetDir(selectedDir.toString() + "\\" +  dc.getProgramName());
                    System.err.println("Directory selected: " + dc.getTargetDir());
                    cbInstallDir.setVisible(false);
                    txtInstallDir.setVisible(true);
                    txtInstallDir.setText(dc.getTargetDir());
                    btnApply.setDisable(false);
                } else {
                    System.err.println("No Directory selected!");
                }
            }
        });

        btnApply.setOnMouseClicked(evt -> {
            applyCheckBoxes();
            try {
                dc.installAll();
                installingScreen = new InstallingScreen(lastScreen, dc);
                Scene scene = new Scene(installingScreen, 500, 400);
                Stage stage = (Stage) (this.getScene().getWindow());
                stage.setScene(scene);
                stage.show();

            } catch (DirectoryNotEmptyException de) {
                // create error message
                errorMessage("TargetDir not empty", "Install directory (" + dc.getTargetDir() + ") is not empty!");

            } catch (AccessDeniedException ae) {
                // create error message
                errorMessage("Access Denied", "You have no access to this folder, restart application as Admin");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        btnGoBack.setOnMouseClicked(evt -> {
            Stage stage = (Stage) InstallScreen.this.getScene().getWindow();
            stage.setScene(lastScreen.getScene());
        });
    }

    private void applyCheckBoxes() {
        Boolean[] installSettings = { cbxStartMenu.isSelected(), cbxTaskBar.isSelected(), cbxDesktop.isSelected(), cbxStartUp.isSelected() };
        dc.setInstallFilesBool(installSettings);
    }

    private void errorMessage(String ErrorTitle, String ErrorMessage) {
        Alert popup = new Alert(Alert.AlertType.ERROR);
        popup.setTitle(ErrorTitle);
        popup.setHeaderText(ErrorMessage);
        popup.showAndWait();

    }
}
