package org.bzzy.creeper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.math.MathHelper;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("creeper.json");

    private static ModConfig config = new ModConfig();

    private ConfigManager() {
    }

    public static void load() {
        try {
            if (Files.notExists(CONFIG_PATH)) {
                save();
                return;
            }

            String json = Files.readString(CONFIG_PATH, StandardCharsets.UTF_8);
            ModConfig loaded = GSON.fromJson(json, ModConfig.class);

            if (loaded == null) {
                loaded = new ModConfig();
            }

            loaded.dropChance = MathHelper.clamp(loaded.dropChance, 0.0D, 1.0D);
            config = loaded;
        } catch (Exception e) {
            config = new ModConfig();
            try {
                save();
            } catch (IOException ignored) {
            }
        }
    }

    public static void save() throws IOException {
        if (CONFIG_PATH.getParent() != null) {
            Files.createDirectories(CONFIG_PATH.getParent());
        }
        Files.writeString(CONFIG_PATH, GSON.toJson(config), StandardCharsets.UTF_8);
    }

    public static ModConfig get() {
        return config;
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}