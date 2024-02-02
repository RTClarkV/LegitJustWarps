package dev.corestone.legitjustwarps.warpstuff;

import dev.corestone.legitjustwarps.LegitJustWarps;
import dev.corestone.legitjustwarps.utile.Colorize;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SetWarp implements CommandExecutor {

    private LegitJustWarps plugin;
    private WarpsConfig warpData;

    public SetWarp(LegitJustWarps plugin, WarpsConfig warpConfig) {
        this.plugin = plugin;
        warpData = warpConfig;
        plugin.getCommand("setwarp").setExecutor(this);
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

        if(name.equals(""))return true;
        if (warpData.getWarpNames().contains(name)) {
            player.sendMessage(Colorize.format("&c A warp with that name already exists, dude."));
            return true;
        }
        warpData.addWarp(name, player.getLocation());
        player.sendMessage(Colorize.format("&fSet the warp " +"&b"+ name + "&f here."));
        return true;
    }


}
