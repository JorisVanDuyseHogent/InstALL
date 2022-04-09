package com.jorisduyse.install.domain;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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

    public String getOriginDir() {
        return originDir;
    }

    public void installAll() throws IOException, InterruptedException {
        moveDirectory(originDir, targetDir);
        createShortCut();
        if (startMenuBool) { addToStart(); }
        if (taskBarBool) { addToTaskbar(); }
        if (desktopBool) { addToDesktop(); }
        if (startUpBool) { enableLaunchAtStartup(); }
    }

    private void moveDirectory(String originDir, String targetDir) throws IOException {
        Files.move(new File(originDir).toPath(), new File(targetDir).toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    private void createShortCut() throws IOException, InterruptedException {
        RunCMD.runCMD("mklink C:\\Users\\Qwert\\Downloads\\test.lnk C:\\Users\\Qwert\\Downloads\\test.txt");
        System.err.println("Creating shortcut");
    }

    private void searchExecutables() {

    }

    private void addToStart() {
        System.err.println("Adding to start menu");

    }

    private void addToTaskbar() {
        System.err.println("Adding taskbar shortcut");

    }

    private void addToDesktop() {
        System.err.println("Adding desktop shortcut");

    }

    private void enableLaunchAtStartup() {
        System.err.println("enabling startup");

    }
}
