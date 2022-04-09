package com.jorisduyse.install.gui;

import com.jorisduyse.install.domain.DomainController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SettingsScreen extends GridPane {
    private DomainController dc;
    private Stage stage;

    private Label lblTitleSettings;
    private Button btnGoBack;

    public SettingsScreen(DomainController dc, Stage stage) {
        this.dc = dc;
        this.stage = stage;
        buildGui();
    }

    private void buildGui() {
        this.setAlignment(Pos.CENTER);

        lblTitleSettings = new Label();
        lblTitleSettings.setText("InstAll Settings");
        lblTitleSettings.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(lblTitleSettings, 0, 0);

        btnGoBack = new Button();
        btnGoBack.setText("Return");
        this.add(btnGoBack,0,1);

        btnGoBack.setOnMouseClicked(evt -> {
            ScreenController.changeScreenTo_StartupScreen(dc, stage);
        });

    }
}
