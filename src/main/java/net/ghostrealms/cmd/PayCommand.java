/*
Copyright (c) 2015 River Marmorstein

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

 */

package net.ghostrealms.cmd;

import net.ghostrealms.UUIDLib;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by River on 1/25/2015.
 */
public class PayCommand implements CommandExecutor {
    
    private Economy econ = null;
    
    public PayCommand(Economy econ) {
        this.econ = econ;
    }
    
    @Override
    public boolean onCommand(CommandSender cmdsender, Command cmd, String label, String[] args) {
        if(args.length != 2) {
            cmdsender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.RED + "Oops. Wrong Usage!");
            cmdsender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.YELLOW + "/pay <user> <amount>");
            return false;
        } else {
            OfflinePlayer sender = (OfflinePlayer) cmdsender;
            OfflinePlayer receiver = Bukkit.getOfflinePlayer(UUIDLib.getID(args[0]));
            if(!econ.hasAccount(receiver)) {
                cmdsender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.YELLOW + "That user does not have an account.");
                return false;
            }
            if(!econ.has(sender, Double.parseDouble(args[1]))) {
                cmdsender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.RED + "Insufficient funds.");
                return false;
            }
            econ.depositPlayer(receiver, Double.parseDouble(args[1]));
            econ.withdrawPlayer(sender, Double.parseDouble(args[1]));
            cmdsender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.YELLOW + "You sent " + ChatColor.GREEN + "$"
            + args[1] + ChatColor.YELLOW + " to " + receiver.getName());
            if(receiver.isOnline()) {
                Player pl = Bukkit.getPlayer(receiver.getUniqueId());
                pl.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.YELLOW + "You Received " + ChatColor.GREEN + "$"
                + args[1] + ChatColor.YELLOW + " from " + sender.getName());
            }
            return true;
        }
    }
}
