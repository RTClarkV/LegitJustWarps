package dev.corestone.legitjustwarps.warpstuff;

import dev.corestone.legitjustwarps.LegitJustWarps;
import dev.corestone.legitjustwarps.utile.Colorize;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WarpToCommand implements Listener, CommandExecutor, TabExecutor {

    private LegitJustWarps plugin;
    private WarpsConfig warpData;
    public WarpToCommand(LegitJustWarps plugin, WarpsConfig warpConfig) {
        this.plugin = plugin;
        this.warpData = warpConfig;
        plugin.getCommand("warp").setExecutor(this);
        plugin.getCommand("warp").setTabCompleter(this);
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

        String arg = args[0];

        if(!warpData.getWarpNames().contains(arg)){
            player.sendMessage(Colorize.format("&c The warp " + "&b" + arg + "&c does not exist."));
            return true;
        }
        if(warpData.getWarp(arg) == null){
            player.sendMessage(Colorize.format("&cHmmm... we have the name but the location does not exist!"));
            return false;
        }
        player.sendMessage(Colorize.format("&f Warping to &b" + arg + "&f."));
        player.teleport(warpData.getWarp(arg));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1){
            return warpData.getWarpNames();
        }
        return new ArrayList<>(); //null
    }
}
