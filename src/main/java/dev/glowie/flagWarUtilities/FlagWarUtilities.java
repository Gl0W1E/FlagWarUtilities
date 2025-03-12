package dev.glowie.flagWarUtilities;

import dev.glowie.flagWarUtilities.listeners.ChunkConquerListener;
import dev.glowie.flagWarUtilities.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlagWarUtilities extends JavaPlugin {
    public static FlagWarUtilities instance;

    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        getServer().getPluginManager().registerEvents(new ChunkConquerListener(), this);
        if (ConfigUtils.isOptionOne()) {
            Bukkit.getLogger().info("1");
        }
        if (ConfigUtils.isOptionTwo()) {
            Bukkit.getLogger().info("2");
        }
        if (ConfigUtils.isOptionThree()) {
            Bukkit.getLogger().info("3");
        }
        if (ConfigUtils.isOptionFour()) {
            Bukkit.getLogger().info("4");
        }

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
