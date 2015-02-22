package net.ghostrealms.cmd;

import net.ghostrealms.RealmsCommands;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by River on 2/21/2015.
 */

public class sudo implements CommandExecutor {
    
    private RealmsCommands instance;
    
    public sudo(RealmsCommands instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!commandSender.isOp()) {
            commandSender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.GOLD + "You are not an OP.");
            return false;
        }
        String cmd = StringUtils.join(args, " ");
        instance.getServer().dispatchCommand(instance.getServer().getConsoleSender(), cmd);
        commandSender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.GREEN + "Successfully Executed: " + ChatColor.GREEN
        + cmd);
        return true;
    }
}
