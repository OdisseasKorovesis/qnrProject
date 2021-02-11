package com.odkor.myQnrProject.Utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils {

    public static String getExceptionStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
