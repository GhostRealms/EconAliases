package net.ghostrealms.cmd.util;

import net.ghostrealms.util.uuid.UUIDLib;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static net.md_5.bungee.api.ChatColor.*;

/**
 * Created by River on 8/11/2016.
 */
public class UUIDCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(args.length != 1) {
            p.spigot().sendMessage(new ComponentBuilder("[Realms] ").color(GRAY)
                    .append("Oops. Wrong Usage!").color(ChatColor.RED)
                    .append(" Usage:").color(ChatColor.YELLOW)
                    .append(" /uuid <username>").color(ChatColor.GOLD)
                    .create());
            return false;
        } else {
            UUID uuid = UUIDLib.getID(args[0]);
            p.spigot().sendMessage(new ComponentBuilder("[Realms] ").color(GRAY)
                    .append(args[0]).color(YELLOW).append("'s UUID: ").color(GOLD)
                    .append(uuid.toString()).color(GREEN)
                    .create());
            return true;
        }
    }
}
