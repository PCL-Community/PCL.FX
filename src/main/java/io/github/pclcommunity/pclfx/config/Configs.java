package io.github.pclcommunity.pclfx.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.pclcommunity.pclfx.util.Log;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configs {
    private static final String configFilePath = "./PCLFX/config.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static PCLFXConfig config;

    static {
        loadConfig();
    }

    public static void loadConfig() {
        Log.LOGGER.info("加载配置文件...");
        File file = new File(configFilePath);
        if (!file.exists()) {
            config = new PCLFXConfig();
            saveConfig();
            return;
        }

        try (FileReader reader = new FileReader(file)) {
            config = gson.fromJson(reader, PCLFXConfig.class);
        } catch (IOException e) {
            Log.LOGGER.error("加载配置文件时出现错误", e);
            throw new RuntimeException(e);
        }
    }

    public static void saveConfig() {
        Log.LOGGER.info("保存配置文件...");
        File file = new File(configFilePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(config, writer);
        } catch (IOException e) {
            Log.LOGGER.error("保存配置文件时发送错误", e);
            throw new RuntimeException(e);
        }
    }

    public static PCLFXConfig config() {
        return config;
    }
}