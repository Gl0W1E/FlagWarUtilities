package dev.glowie.flagWarUtilities.utils;

import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;

import java.util.ArrayList;
import java.util.List;

public class ChunkUtils {

    public static void processChunks(TownBlock townBlock, Town conqueror) {

        if (ConfigUtils.isOptionFour() && townBlock.isOutpost()) {
            ChunkUtils.getConnectedTownBlocks(townBlock).forEach(tb -> {
                tb.setTown(conqueror);
                tb.save();
            });
        }
        if (ConfigUtils.isOptionThree() && townBlock.isHomeBlock()) {
            townBlock.getTownOrNull().getTownBlocks().forEach(tb -> {
                tb.setTown(conqueror);
                tb.save();
            });
        }
        if (ConfigUtils.isOptionTwo() && townBlock.isHomeBlock()) {
            ChunkUtils.getConnectedTownBlocks(townBlock).forEach(tb -> {
                tb.setTown(conqueror);
                tb.save();
            });
        }
        if (ConfigUtils.isOptionOne() && townBlock.isHomeBlock() && townBlock.getTownOrNull().isCapital()) {
            Nation n = townBlock.getTownOrNull().getNationOrNull();
            n.getTowns().forEach(t -> {
                try {
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
            if (tb.hasTownBlock() && !tbList.contains(tb.getTownBlockOrNull())) {
                tbList.add(tb.getTownBlockOrNull());
                addChunksToList(tb.getTownBlockOrNull(), tbList);
            }
        });
    }

}
