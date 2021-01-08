package fr.sad.earthskyitems.utils;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.island.IslandPrivilege;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PermissionUtils {

    private static WorldGuardPlugin getWorldGuard() {
        final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        if (!(plugin instanceof WorldGuardPlugin)) {
            return null;
        }
        return (WorldGuardPlugin) plugin;
    }

    public static boolean canBuild(Player player, Location location, IslandPrivilege islandPrivilege) {
        if(getWorldGuard().canBuild(player, location)){
            Island island = SuperiorSkyblockAPI.getIslandAt(location);

            if(island == null){
                return true;
            }

            if (island.hasPermission(SuperiorSkyblockAPI.getPlayer(player.getUniqueId()), islandPrivilege)){
                return true;
            }
            return false;
        }
        return false;
    }
}
