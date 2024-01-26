package dev.corestone.legitjustwarps.warpstuff;

import dev.corestone.legitjustwarps.LegitJustWarps;
import dev.corestone.legitjustwarps.utile.Colorize;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DeleteWarp implements CommandExecutor, TabCompleter {
    private LegitJustWarps plugin;
    private WarpsConfig warpsData;
    public DeleteWarp(LegitJustWarps legitJustWarps, WarpsConfig warpConfig) {
        this.plugin = legitJustWarps;
        this.warpsData = warpConfig;
        plugin.getCommand("deletewarp").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player))return true;
        Player player = (Player) sender;
        ArrayList<String> list = new ArrayList<>();
        for(int j = 0; args.length > j; j++){
            list.add(args[j]);
        }
        if(list.isEmpty()) {
            player.sendMessage(Colorize.format("&c Your warp must have a name."));
            return true;
        }

        String name = args[0];

        if(!warpsData.getWarpNames().contains(name)){
            player.sendMessage(Colorize.format("&c That warp does not exist."));
            return true;
        }
        player.sendMessage(Colorize.format("&f Deleting the warp " + "&b" + name +"&f."));
        warpsData.removeWarp(name);
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1){
            return warpsData.getWarpNames();
        }
        return new ArrayList<>();
    }
}
