package fr.sad.earthskyitems.listener;

import com.bgsoftware.superiorskyblock.api.island.IslandPrivilege;
import fr.sad.earthskyitems.Main;
import fr.sad.earthskyitems.armor.ArmorEquipEvent;
import fr.sad.earthskyitems.utils.PermissionUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class CustomItemListener implements Listener{

    private final Main main;

    public CustomItemListener(Main main){
        this.main = main;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBreak(BlockBreakEvent event){
        if(PermissionUtils.canBuild(event.getPlayer(), event.getBlock().getLocation(), IslandPrivilege.getByName("BREAK")) && !event.isCancelled()){
            main.getCustomItemManager().getCustomItemSet().forEach(customItem -> customItem.onBreak(event));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlace(BlockPlaceEvent event){
        if(PermissionUtils.canBuild(event.getPlayer(), event.getBlockPlaced().getLocation(), IslandPrivilege.getByName("BUILD")) && !event.isCancelled()){
            main.getCustomItemManager().getCustomItemSet().forEach(customItem -> customItem.onPlace(event));
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        main.getCustomItemManager().getCustomItemSet().forEach(customItem -> customItem.onInteract(event));
    }

    @EventHandler
    public void onFish(PlayerFishEvent event){
        main.getCustomItemManager().getCustomItemSet().forEach(customItem -> customItem.onFish(event));
    }

    @EventHandler
    public void onEquip(ArmorEquipEvent event){
        main.getCustomItemManager().getCustomItemSet().forEach(customItem -> customItem.onEquip(event));
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        main.getCustomItemManager().getCustomItemSet().forEach(customItem -> customItem.onDeath(event));
    }

    @EventHandler
    public void onSpawn(PlayerRespawnEvent event){
        main.getCustomItemManager().getCustomItemSet().forEach(customItem -> customItem.onSpawn(event));
    }

    @EventHandler
    public void onRepair(PrepareAnvilEvent event){
        if(main.getCustomItemManager().isCustomItem(event.getResult())){
            event.setResult(new ItemStack(Material.AIR));
        }
    }
}
