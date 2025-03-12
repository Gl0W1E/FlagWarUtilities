package dev.glowie.flagWarUtilities.listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import dev.glowie.flagWarUtilities.utils.ChunkUtils;
import io.github.townyadvanced.flagwar.events.CellWonEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChunkConquerListener implements Listener {

    @EventHandler
    public void onConquer(CellWonEvent event) {
        Location l = event.getCellUnderAttack().getFlagBaseBlock().getLocation();
        TownBlock tbAttacked = TownyAPI.getInstance().getTownBlock(l);
        Resident rAttacker = TownyAPI.getInstance().getResident(event.getCellUnderAttack().getNameOfFlagOwner());
        Town tAttacker = rAttacker.getTownOrNull();
        Bukkit.getLogger().info("listener triggered");
        ChunkUtils.processChunks(tbAttacked, tAttacker);
    }

}
