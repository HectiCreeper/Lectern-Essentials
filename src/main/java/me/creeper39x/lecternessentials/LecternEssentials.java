package me.creeper39x.lecternessentials;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Lectern;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.ItemStack;

public class LecternEssentials extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        Player player = event.getPlayer();

        if (clickedBlock != null && clickedBlock.getType() == Material.LECTERN) {
            BlockState state = clickedBlock.getState();
            if (state instanceof Lectern lectern) {
                ItemStack book = lectern.getInventory().getItem(0);
                if (book != null && book.getType() == Material.WRITTEN_BOOK) {
                    player.openBook(book);
                    event.setCancelled(true); // Prevent the lectern GUI from opening
                }
            }
        }
    }
}