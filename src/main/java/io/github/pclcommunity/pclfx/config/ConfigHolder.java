package io.github.pclcommunity.pclfx.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.pclcommunity.pclfx.util.Log;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigHolder {
    private static final String configFilePath = "./PCLFX/config.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static Config config;

    static {
        loadConfig();
    }

    public static void loadConfig() {
        Log.LOGGER.info("Loading config");
        File file = new File(configFilePath);
        if (!file.exists()) {
            config = new Config();
            saveConfig();
            return;
        }

        try (FileReader reader = new FileReader(file)) {
            config = gson.fromJson(reader, Config.class);
        } catch (IOException e) {
            Log.LOGGER.error("Error loading config from file", e);
        }
    }

    public static void saveConfig() {
        Log.LOGGER.info("Saving config");
        File file = new File(configFilePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(config, writer);
        } catch (IOException e) {
            Log.LOGGER.error("Error saving config to file", e);
        }
    }

    public static Config config() {
        return config;
    }
}