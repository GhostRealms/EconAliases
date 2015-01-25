package net.ghostrealms;

import net.ghostrealms.cmd.BalanceCommand;
import net.ghostrealms.cmd.PayCommand;
import net.milkbowl.vault.economy.Economy;
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
