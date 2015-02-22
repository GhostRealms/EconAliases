package net.ghostrealms.listener;


import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by River on 2/22/2015.
 * Create an economy account on join to prevent errors with balance commands* 
 */
public class PlayerJoinListener implements Listener {
    
    private Economy econ = null;
    
    public PlayerJoinListener(Economy econ) {
        this.econ = econ;
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent ev) {
        Player p = ev.getPlayer();
        if(!econ.hasAccount(p)) {
            econ.createPlayerAccount(p);
            Bukkit.getLogger().info("Created player account for: " + p.getName());
        }
    }
}
