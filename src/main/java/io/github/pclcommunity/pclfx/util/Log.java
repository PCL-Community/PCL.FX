package io.github.pclcommunity.pclfx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    public static Logger LOGGER;

    private Log() {
    }

    public static void init() {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss"));
        File logPath = new File("./PCLFX/logs/");
        if (!logPath.exists()) {
            logPath.mkdirs();
        }
        String logFile = logPath.getAbsolutePath() + "/" + time + ".log";
        System.setProperty("log.filePath", logFile);
        LOGGER = LoggerFactory.getLogger(Log.class);
    }
}
