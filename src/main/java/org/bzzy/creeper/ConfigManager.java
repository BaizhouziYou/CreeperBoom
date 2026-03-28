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
                config = new ModConfig();
                save();
                return;
            }

            String json = Files.readString(CONFIG_PATH, StandardCharsets.UTF_8);
            ModConfig loaded = GSON.fromJson(json, ModConfig.class);

            // 创建默认配置作为基础
            ModConfig defaultConfig = new ModConfig();

            if (loaded == null) {
                config = defaultConfig;
            } else {
                // 合并配置：保留旧配置的值，缺失的字段使用默认值
                config = new ModConfig();
                config.dropChance = loaded.dropChance;
                config.preventBlockDamage = loaded.preventBlockDamage;
            }

            // 确保值在合理范围内
            config.dropChance = MathHelper.clamp(config.dropChance, 0.0D, 1.0D);

            // 保存完整的配置（确保新字段被写入）
            save();
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
}