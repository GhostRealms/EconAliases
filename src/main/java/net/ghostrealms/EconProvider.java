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

package net.ghostrealms;

import net.ghostrealms.cmd.BalanceCommand;
import net.ghostrealms.cmd.PayCommand;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by River on 1/25/2015.
 */
public class EconProvider extends JavaPlugin {
    
    public static Economy econ = null;
    
    @Override
    public void onEnable() {
        try {
            setupEconomy();
        } catch (Exception ex) {
            getLogger().severe("unable to setup vault");
            getServer().getPluginManager().disablePlugin(this);
        }
        getCommand("balance").setExecutor(new BalanceCommand(econ));
        getCommand("pay").setExecutor(new PayCommand(econ));
        PluginCommand cmd = getServer().getPluginCommand("pay");
        cmd.setExecutor(new PayCommand(econ));
    }
    
    @Override
    public void onDisable() {
        
    }

    private boolean setupEconomy() throws Exception {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            throw new Exception();
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            throw new Exception();
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
