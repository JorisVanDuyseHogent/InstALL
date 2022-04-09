package com.jorisduyse.install.domain;

import java.io.File;
import java.io.IOException;

public class DomainController {
    private LoadFiles lf;
    private InstallFiles inf;
    private RunCMD rsc;
    private Unzip uz;

    public DomainController() {
        LoadFiles lf = new LoadFiles();
        InstallFiles inf = new InstallFiles();
        RunCMD rsc = new RunCMD();
        Unzip uz = new Unzip();

        this.lf = lf;
        this.inf = inf;
        this.rsc = rsc;
        this.uz = uz;

    }

    public String getProgramName() {
        return lf.getProgramName();
    }

    public String getTargetDir() {
        return inf.getTargetDir();
    }

    public String getOriginDir() {
        return inf.getOriginDir();
    }

    public void setProgramName(String programName) {
        lf.setProgramName(programName);
    }

    public void setInstallFilesBool(Boolean[] installSettings) {
        inf.setStartMenuBool(installSettings[0]);
        inf.setTaskBarBool(installSettings[1]);
        inf.setDesktopBool(installSettings[2]);
        inf.setStartUpBool(installSettings[3]);

        for (boolean bool : installSettings) {
            System.err.println(bool);

        }
    }

    public void installAll() throws IOException, InterruptedException {
        inf.installAll();
    }

    public void setTargetDir(String targetDir) {
        inf.setTargetDir(targetDir);
    }

    public void setOriginDir(String originDir) {
        inf.setOriginDir(originDir);
    }

    public int runCMD(String command) throws IOException, InterruptedException {
        return RunCMD.runCMD(command);
    }

    public void unzip(String zipDir, String targetDir) throws IOException, InterruptedException {
        uz.unzip(zipDir, targetDir);
    }

    public String getZipName(File zipFile) {
        return uz.getZipName(zipFile);
    }

}
