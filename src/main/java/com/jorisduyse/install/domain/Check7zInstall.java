package com.jorisduyse.install.domain;

public class Check7zInstall {
    public static boolean is7zipInstalled() {
        try {
            Process process = Runtime.getRuntime().exec("cd C:\\Program Files\\7-Zip");
            Process run7zip = Runtime.getRuntime().exec(".\\7z.exe");
            int code = run7zip.waitFor();
            return code == 0;
        } catch (Exception e) {
            return false;
        }
    }
}
