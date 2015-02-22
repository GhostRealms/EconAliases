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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by River on 1/25/2015.
 */
public class BalanceCommand implements CommandExecutor {
    
    private Economy econ = null;
    private final String pre = ChatColor.GRAY + "[Realms] ";
    
    public BalanceCommand(Economy econ) {
        this.econ = econ;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length <= 0) {
            OfflinePlayer player = (OfflinePlayer) sender;
            sender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.YELLOW + "Your Balance: " + ChatColor.GREEN+ prettyPrint(econ.getBalance(player)));
            return false;
        }
        for(String s : args) {
            UUID id = UUIDLib.getID(s);
            if(id  == null) {
                sender.sendMessage(pre + ChatColor.GOLD + s + ChatColor.YELLOW + " does not have an account!");
                continue;
            }
            OfflinePlayer pl = Bukkit.getOfflinePlayer(id);
            if(pl == null || !pl.hasPlayedBefore()) {
                sender.sendMessage(pre + ChatColor.GOLD + s + ChatColor.YELLOW + " does not have an account!");
                continue;
            }
            sender.sendMessage(pre + ChatColor.GOLD + pl.getName() + ChatColor.YELLOW + "'s balance: " +
                    ChatColor.GREEN + prettyPrint(econ.getBalance(pl)));
        }
        return true;
    }

    public String prettyPrint(double num) {
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        return econ.format(num).replace(".00", "");
    }
        
}

