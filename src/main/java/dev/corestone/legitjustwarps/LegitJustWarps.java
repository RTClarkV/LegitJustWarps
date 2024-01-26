package dev.corestone.legitjustwarps;

import dev.corestone.legitjustwarps.warpstuff.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class LegitJustWarps extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        WarpsConfig warpConfig = new WarpsConfig(this);
        new WarpToCommand(this, warpConfig);
        new DeleteWarp(this, warpConfig);
        new SetWarp(this, warpConfig);
//        new WarpsList(this, warpConfig);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
