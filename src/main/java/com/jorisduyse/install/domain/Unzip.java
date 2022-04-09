package com.jorisduyse.install.domain;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Unzip {

    public void unzip(String zipDir, String targetDir) throws IOException, InterruptedException {
        System.err.println("Running cmd command: cd C:\\Program Files\\7-Zip\\ & 7z.exe e " + zipDir + " -o" + targetDir  + " -y");
        int exitCode = RunCMD.runCMD("C:\\Program Files\\7-Zip\\7z.exe e " + zipDir + " -o" + targetDir  + " -y");
        if (exitCode != 0) {
            System.err.println("Unable to unzip, exit code: " + exitCode);
        }
    }

    // Get decent naming scheme for loaded zip files
    public String getZipName(File zipFile) {
        String zipFileFullName = zipFile.getName();
        String[] zipFileNameCombination = {};

        try {
            if (zipFileFullName.contains("-")) {
                zipFileNameCombination = zipFileFullName.split("-");
                System.err.println(zipFileNameCombination[0]);
                return zipFileNameCombination[0];
            } else {
                zipFileNameCombination = zipFileFullName.split(".");
                return zipFileNameCombination[0];
            }
        } catch (ArrayIndexOutOfBoundsException ae) {
            return zipFile.getName();
        }
    }
}
