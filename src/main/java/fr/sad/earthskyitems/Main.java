package fr.sad.earthskyitems;

import fr.sad.earthskyitems.armor.ArmorListener;
import fr.sad.earthskyitems.commands.CommandItems;
import fr.sad.earthskyitems.listener.CustomItemListener;
import fr.sad.earthskyitems.manager.CustomItemManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Main extends JavaPlugin {

    private CustomItemManager customItemManager;

    public void onEnable() {

        (customItemManager = new CustomItemManager()).register();

        Bukkit.getPluginManager().registerEvents(new CustomItemListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ArmorListener(), this);

        getCommand("items").setExecutor(new CommandItems(this));
    }
}
