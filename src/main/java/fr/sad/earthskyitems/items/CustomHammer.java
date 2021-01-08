package fr.sad.earthskyitems.items;

import com.bgsoftware.superiorskyblock.api.island.IslandPrivilege;
import fr.sad.earthskyitems.armor.ArmorEquipEvent;
import fr.sad.earthskyitems.manager.CustomItem;
import fr.sad.earthskyitems.utils.HammerUtils;
import fr.sad.earthskyitems.utils.ItemCreator;
import fr.sad.earthskyitems.utils.PermissionUtils;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class CustomHammer extends CustomItem {

    public CustomHammer() {
        super("hammer", new ItemCreator(Material.GOLD_PICKAXE).setUnbreakable(true).setName("§eHammer").setLores("", "§7Ce marteau vous permet de casser", "§7en 3x3").getItem());
    }

    @Override
    public void onBreak(BlockBreakEvent event) {
        if(PermissionUtils.canBuild(event.getPlayer(), event.getBlock().getLocation(), IslandPrivilege.getByName("BREAK"))){
            if(isSimilar(event.getPlayer().getItemInHand())){
                HammerUtils.destroy(event.getPlayer(), event.getPlayer().getItemInHand(), event.getBlock());
            }
        }
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {

    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        if(event.getBlockFace() == null) return;

        HammerUtils.playerRelatives.put(event.getPlayer().getUniqueId(), event.getBlockFace());
    }

    @Override
    public void onEquip(ArmorEquipEvent event) {

    }

    @Override
    public void onDeath(PlayerDeathEvent event) {

    }

    @Override
    public void onSpawn(PlayerRespawnEvent event) {

    }

    @Override
    public void onFish(PlayerFishEvent event) {

    }
}
