package com.jorisduyse.install.domain;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static java.nio.file.StandardCopyOption.*;

public class InstallFiles {
    private String targetDir;
    private String originDir;

    private boolean startMenuBool;
    private boolean taskBarBool;
    private boolean desktopBool;
    private boolean startUpBool;


    public void setStartMenuBool(boolean startMenuBool) {
        this.startMenuBool = startMenuBool;
    }

    public void setTaskBarBool(boolean taskBarBool) {
        this.taskBarBool = taskBarBool;
    }

    public void setDesktopBool(boolean desktopBool) {
        this.desktopBool = desktopBool;
    }

    public void setStartUpBool(boolean startUpBool) {
        this.startUpBool = startUpBool;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

    public void setOriginDir(String originDir) {
        this.originDir = originDir;
    }

    public String getTargetDir() {
        return targetDir;
    }

    public void installAll() throws IOException {
        moveDirectory(originDir, targetDir);
        createShortCut();
        if (startMenuBool) { addToStart(); }
        if (taskBarBool) { addToTaskbar(); }
        if (desktopBool) { addToDesktop(); }
        if (startUpBool) { enableLaunchAtStartup(); }
    }

    public void moveDirectory(String originDir, String targetDir) throws IOException {
        Files.move(new File(originDir).toPath(), new File(targetDir).toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public void createShortCut() {
        System.err.println("Creating shortcut");

    }

    public void addToStart() {
        System.err.println("Adding to start menu");

    }

    public void addToTaskbar() {
        System.err.println("Adding taskbar shortcut");

    }

    public void addToDesktop() {
        System.err.println("Adding desktop shortcut");

    }

    public void enableLaunchAtStartup() {
        System.err.println("enabling startup");

    }
}
