package io.github.pclcommunity.pclfx;

import io.github.pclcommunity.pclfx.util.Log;

import javax.swing.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.getProperties().putIfAbsent("javafx.animation.pulse", "120");

        Log.init();

        Thread.currentThread().setUncaughtExceptionHandler((t, e) ->
        {
            Log.LOGGER.error("", e);
            StringWriter stringWriter = new StringWriter(512);
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            JOptionPane.showMessageDialog(null, stringWriter.toString(), "锟斤拷烫烫烫", JOptionPane.ERROR_MESSAGE);
        });
        Log.LOGGER.info("Java 版本：{}", System.getProperty("java.version"));
        Log.LOGGER.info("Java 发行商：{}", System.getProperty("java.vendor"));
        Log.LOGGER.info("Java 路径: {}", System.getProperty("java.home"));
        try {
            Log.LOGGER.info("Jar 路径：{}", Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).toAbsolutePath());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        PCLFxApplication.main(args);
    }
}
