package com.jorisduyse.install.domain;

import java.io.IOException;

public class DomainController {
    private LoadFiles lf;
    private InstallFiles inf;

    public DomainController() {
        LoadFiles lf = new LoadFiles();
        InstallFiles inf = new InstallFiles();
        this.lf = lf;
        this.inf = inf;

    }

    public String getProgramName() {
        return lf.getProgramName();
    }

    public String getTargetDir() {
        return inf.getTargetDir();
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

    public void installAll() throws IOException {
        inf.installAll();
    }

    public void setTargetDir(String targetDir) {
        inf.setTargetDir(targetDir);
    }

    public void setOriginDir(String originDir) {
        inf.setOriginDir(originDir);
    }
}
