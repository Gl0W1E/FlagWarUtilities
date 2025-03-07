package dev.glowie.flagWarUtilities.utils;

import dev.glowie.flagWarUtilities.FlagWarUtilities;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUtils {

    public static FileConfiguration getConfig() {
        return FlagWarUtilities.instance.getConfig();
    }

    public static boolean isOptionOne() {
        return getConfig().getBoolean("capture_capital_homeblock_transfer_other_towns");
    }

    public static boolean isOptionTwo() {
        return getConfig().getBoolean("capture_homeblock_transfer_connected_chunks");
    }

    public static boolean isOptionThree() {
        return getConfig().getBoolean("capture_homeblock_transfer_all_chunks");
    }

    public static boolean isOptionFour() {
        return getConfig().getBoolean("capture_outpost_transfer_connected_chunks");
    }

}
