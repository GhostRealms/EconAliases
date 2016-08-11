package net.ghostrealms.cmd;

import mkremins.fanciful.FancyMessage;
import net.ghostrealms.RealmsCommands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
        sender.sendMessage(new FancyMessage("[Realms] ").color(ChatColor.GRAY).then("EconAlias & RealmsCommand Plugin:").color(ChatColor.YELLOW)
        .then("Version " + plugin.getDescription().getVersion()).color(org.bukkit.ChatColor.GOLD).toJSONString());
        return true;
    }
}
