package com.jorisduyse.install.gui;

import com.jorisduyse.install.domain.DomainController;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InstallingScreen extends VBox {
    private DomainController dc;
    private Stage stage;
    private ProgressBar progressBar;


    public InstallingScreen(DomainController dc, Stage stage) {
        this.dc = dc;
        this.stage = stage;
        buildGui();
    }

    private void buildGui() {
        progressBar = new ProgressBar();

        progressBar.setMaxWidth(500);


    }
}
