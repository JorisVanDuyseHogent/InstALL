package com.jorisduyse.install.gui;

import com.jorisduyse.install.domain.DomainController;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

public class InstallingScreen extends VBox {
    private DomainController dc;
    private StartupScreen lastscreen;

    private ProgressBar progressBar;


    public InstallingScreen(StartupScreen lastScreen, DomainController dc) {
        this.lastscreen = lastScreen;
        this.dc = dc;
        buildGui();
    }

    private void buildGui() {
        progressBar = new ProgressBar();

        progressBar.setMaxWidth(500);
    }
}
