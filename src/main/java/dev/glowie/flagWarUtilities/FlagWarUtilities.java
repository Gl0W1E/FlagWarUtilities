package dev.glowie.flagWarUtilities;

import org.bukkit.plugin.java.JavaPlugin;

public final class FlagWarUtilities extends JavaPlugin {
    public static FlagWarUtilities instance;

    @Override
    public void onEnable() {
        instance = this;
        loadConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {
        instance.getConfig().options().copyDefaults(false);
        instance.saveDefaultConfig();
    }
}
