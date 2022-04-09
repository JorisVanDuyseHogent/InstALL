package com.jorisduyse.install.domain;

import java.io.IOException;

public class RunCMD {
    public static int runCMD(String command) throws IOException, InterruptedException {
        Process cmdProcess = Runtime.getRuntime().exec(command);
        cmdProcess.getOutputStream().close();
        int exitCode = cmdProcess.waitFor();
        return exitCode;
    }


}