package net.ghostrealms.cmd;

import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by River on 2/21/2015.
 */

public class ChatforCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = Bukkit.getPlayerExact(strings[0]);
        String message = StringUtils.join(strings, " ", 1, strings.length);
        if(!commandSender.isOp()) {
            commandSender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.GOLD + "You are not an operator..");
            return false;
        }
        if(!player.isOnline()) {
            commandSender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.RED + "That player is not online.");
            return false;
        }
        player.chat(message);
        commandSender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.YELLOW + "Sent a chat message for " + ChatColor.AQUA + 
        player.getName());
        return true;
    }
}
