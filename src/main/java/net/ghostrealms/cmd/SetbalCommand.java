package net.ghostrealms.cmd;

import net.ghostrealms.UUIDLib;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by River on 2/22/2015.
 * Administrative command for setting player balances* 
 */

public class SetbalCommand implements CommandExecutor {
    
    private Economy econ = null;
    private final String pre = ChatColor.GRAY + "[Realms] ";
    
    public SetbalCommand(Economy econ) {
        this.econ = econ;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        double amount = 0;
        boolean addMode = false;
        boolean subMode = false;
        boolean setMode = true;
        
        if(args.length != 2) {
            sender.sendMessage(pre + ChatColor.RED + "Invalid Arguments.. Try again.");
            return false;
        }
        if(args[1].startsWith("+")) {
            setMode = false;
            addMode = true;
            args[1].replace("+", "");
        } else if(args[1].startsWith("-")) {
            setMode = false;
            subMode = true;
            args[1].replace("-", "");
        }
        try {
            amount = Double.parseDouble(args[1]);
        } catch (RuntimeException ex) {
            sender.sendMessage(pre + ChatColor.GOLD + "Please specify a number for the balance!");
            return false;
        }
        
        UUID target = UUIDLib.getID(args[0]);
        if(target == null) {
            sender.sendMessage(pre + ChatColor.GOLD + "That player does not exist.");
            return false;
        }
        OfflinePlayer t = Bukkit.getOfflinePlayer(target);
        if(t == null || !t.hasPlayedBefore()) {
            sender.sendMessage(pre + ChatColor.GOLD + "That player does not exist.");
            return false;
        }
        
        if(setMode) {
            double set = econ.getBalance(t) - amount;
            econ.withdrawPlayer(t, set);
            if(econ.getBalance(t) == amount) {
                sender.sendMessage(pre + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "'s balance has been set to "
                    + ChatColor.GREEN + prettyPrint(amount));
                return true;
            }
        } else if(addMode) {
            econ.depositPlayer(t, amount);
            sender.sendMessage(pre + ChatColor.YELLOW + "You added " + ChatColor.GREEN + prettyPrint(amount) + ChatColor.YELLOW + 
                " to " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "'s account.");
        } else if(subMode) {
            
        }
        
        
        return false;
    }


    public String prettyPrint(double num) {
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        return econ.format(num).replace(".00", "");
    }
}
