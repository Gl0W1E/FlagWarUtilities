package dev.glowie.flagWarUtilities.utils;

import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class ChunkUtils {

    public static void processChunks(TownBlock townBlock, Town conqueror) {

        if (ConfigUtils.isOptionFour() && townBlock.isOutpost()) {
            Bukkit.getLogger().info("opt 4");
            ChunkUtils.getConnectedTownBlocks(townBlock).forEach(tb -> {
                Bukkit.getLogger().info("looped opt 4");
                tb.setTown(conqueror);
                tb.save();
            });
        }
        if (ConfigUtils.isOptionThree() && townBlock.isHomeBlock()) {
            Bukkit.getLogger().info("opt 3");
            townBlock.getTownOrNull().getTownBlocks().forEach(tb -> {
                Bukkit.getLogger().info("looped opt 3");
                tb.setTown(conqueror);
                tb.save();
            });
        }
        if (ConfigUtils.isOptionTwo() && townBlock.isHomeBlock()) {
            Bukkit.getLogger().info("opt 2");
            ChunkUtils.getConnectedTownBlocks(townBlock).forEach(tb -> {
                Bukkit.getLogger().info("looped opt 2");
                tb.setTown(conqueror);
                tb.save();
            });
        }
        if (ConfigUtils.isOptionOne() && townBlock.isHomeBlock() && townBlock.getTownOrNull().isCapital()) {
            Bukkit.getLogger().info("opt 1");
            Nation n = townBlock.getTownOrNull().getNationOrNull();
            n.getTowns().forEach(t -> {
                try {
                    Bukkit.getLogger().info("looped opt 1");
                    t.setNation(conqueror.getNationOrNull());
                    t.save();
                } catch (AlreadyRegisteredException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }

    public static List<TownBlock> getConnectedTownBlocks(TownBlock townBlock) {
        List<TownBlock> tbList = new ArrayList<>();
        addChunksToList(townBlock, tbList);
        return tbList;
    }

    public static void addChunksToList(TownBlock initialTB, List<TownBlock> tbList) {
        initialTB.getWorldCoord().getCardinallyAdjacentWorldCoords(false).forEach(tb -> {
            Bukkit.getLogger().info("looping addchunks");
            if (tb.hasTownBlock() && !tbList.contains(tb.getTownBlockOrNull()) && tb.getTownOrNull() == initialTB.getTownOrNull()) {
                tbList.add(tb.getTownBlockOrNull());
                Bukkit.getLogger().info("added another tb");
                addChunksToList(tb.getTownBlockOrNull(), tbList);
            }
        });
    }

}
