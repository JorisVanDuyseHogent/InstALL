package com.jorisduyse.install.domain;

import javafx.stage.DirectoryChooser;

public class LoadFiles {
    private static DirectoryChooser directoryChooser = new DirectoryChooser();
    private static String programName;

    public static DirectoryChooser getDirectoryChooser() {
        return directoryChooser;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
