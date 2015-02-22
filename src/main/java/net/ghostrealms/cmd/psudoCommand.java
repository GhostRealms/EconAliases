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
public class psudoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        String cmd = StringUtils.join(args, " ", 1, args.length);
        Player sender = Bukkit.getPlayerExact(args[0]);
        if(!commandSender.isOp()) {
            commandSender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.GOLD + "You are not an operator..");
            return false;
        }
        if(!sender.isOnline()) {
            commandSender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.RED + "That player is not online..");
            return false;
        }
        sender.performCommand(cmd);
        commandSender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.YELLOW + "Executed for " + ChatColor.GOLD +
        sender.getName() + ChatColor.YELLOW + ":");
        commandSender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.AQUA + cmd);
        return true;
    }
}
