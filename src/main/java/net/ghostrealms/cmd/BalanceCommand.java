package net.ghostrealms.cmd;

import net.ghostrealms.UUIDLib;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

/**
 * Created by River on 1/25/2015.
 */
public class BalanceCommand implements CommandExecutor {
    
    private Economy econ = null;
    
    public BalanceCommand(Economy econ) {
        this.econ = econ;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 0) {
            UUID id = UUIDLib.getID(sender.getName());
            sender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.YELLOW + "Your Balance: " + ChatColor.GREEN + "$" + econ.getBalance(Bukkit.getOfflinePlayer(id)));
            return true;
        } else {
            for(String s : args) {
                OfflinePlayer user = Bukkit.getOfflinePlayer(UUIDLib.getID(s));
                if(econ.hasAccount(user)) {
                    sender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.GOLD + user.getName() + "'s " + ChatColor.YELLOW + "Balance: " + ChatColor.GREEN + "$" + econ.getBalance(user));
                } else {
                    sender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.RED + user.getName() + " " + ChatColor.DARK_RED + "does not have an account.");
                }
            }
            return true;
        }
    }
    
}
