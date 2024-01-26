package dev.corestone.legitjustwarps.warpstuff;

import dev.corestone.legitjustwarps.LegitJustWarps;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
public class WarpsConfig {


    private LegitJustWarps plugin;
    private File file;
    private YamlConfiguration config;
    private HashMap<String, Location> warpsList = new HashMap<>();
    private ArrayList<String> warpNames = new ArrayList<>();

    public WarpsConfig(LegitJustWarps plugin){
        this.plugin = plugin;
        load();
        setWarpsList();
    }
    public void load(){
        file = new File(plugin.getDataFolder(), "warps.yml");

        if(!file.exists()){
            plugin.saveResource("warps.yml", false);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void save(){
        try {
            config.save(file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void setWarpsList(){
        if(config.getConfigurationSection("warps") == null)return;
        for(String path : config.getConfigurationSection("warps").getKeys(false)){
            warpsList.put(path, config.getLocation("warps."+path+".location"));
            warpNames.add(path);
        }
    }
    public Location getWarp(String name){
        return warpsList.getOrDefault(name, null);
    }

    public ArrayList<String> getWarpNames(){
        return warpNames;
    }

    public void addWarp(String name, Location location){
        config.set("warps."+name+".location", location);
        warpNames.add(name);
        warpsList.put(name, location);
        save();
    }

    public void removeWarp(String name){
        config.set("warps."+name, null);
        warpNames.remove(name);
        warpsList.remove(name);
        save();
    }
}
