package net.ghostrealms.cmd;

import net.ghostrealms.RealmsCommands;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.md_5.bungee.api.ChatColor.GOLD;
import static net.md_5.bungee.api.ChatColor.GRAY;
import static net.md_5.bungee.api.ChatColor.YELLOW;

/**
 * Created by River on 8/11/2016.
 */
public class VersionCommand implements CommandExecutor {

    private final RealmsCommands plugin;

    public VersionCommand(RealmsCommands plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        p.spigot().sendMessage(
                new ComponentBuilder("[Realms] ").color(GRAY).append("RealmsCommands: ").color(YELLOW)
                        .append("Version: " + plugin.getDescription().getVersion()).color(GOLD)
                        .create());
        return true;
    }
}
