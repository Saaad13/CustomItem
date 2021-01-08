package fr.sad.earthskyitems.items;

import fr.sad.earthskyitems.armor.ArmorEquipEvent;
import fr.sad.earthskyitems.manager.CustomItem;
import fr.sad.earthskyitems.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class CustomPickaxe extends CustomItem {

    public CustomPickaxe() {
        super("spawner_pickaxe", 5, new ItemCreator(Material.GOLD_PICKAXE).setName("§ePioche à Spawner").setLores("", "§7Cette pioche permet de récupérer", "§7les spawners.").setUnbreakable(true).getItem());
    }

    @Override
    public void onBreak(BlockBreakEvent event) {
        if(isSimilar(event.getPlayer().getItemInHand())){
            if(event.getBlock().getType() == Material.MOB_SPAWNER){
                CreatureSpawner creatureSpawner = (CreatureSpawner) event.getBlock().getState();
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation().add(0, 1, 0), new ItemCreator(Material.MOB_SPAWNER).setName("§eSpawner à " + creatureSpawner.getCreatureTypeName()).getItem());
                removeDurability(event.getPlayer(), event.getPlayer().getItemInHand());
            }
        }
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        ItemStack itemStack = event.getItemInHand();
        if(itemStack.getType() == Material.MOB_SPAWNER && itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName() && itemStack.getItemMeta().getDisplayName().startsWith("§e")){
            EntityType entityType = EntityType.fromName(itemStack.getItemMeta().getDisplayName().replaceAll("§eSpawner à ", ""));
            BlockState blockState = event.getBlockPlaced().getState();
            CreatureSpawner creatureSpawner = (CreatureSpawner) blockState;
            creatureSpawner.setSpawnedType(entityType);
            blockState.update();
        }
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {

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
