package fr.sad.earthskyitems.items;

import fr.sad.earthskyitems.armor.ArmorEquipEvent;
import fr.sad.earthskyitems.manager.CustomItem;
import fr.sad.earthskyitems.utils.ItemCreator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class CustomFour extends CustomItem {

    public CustomFour() {
        super("pickaxe_ore", new ItemCreator(Material.DIAMOND_PICKAXE).setName("§6Pioche en acier").setUnbreakable(true).getItem());
    }

    @Override
    public void onBreak(BlockBreakEvent event) {
        if(event.getPlayer().getItemInHand() == null) return;

        if(isSimilar(event.getPlayer().getItemInHand())){
            Block block = event.getBlock();
            Location clone = new Location(block.getWorld(), block.getLocation().getBlockX() + 0.5, block.getLocation().getBlockY(), block.getLocation().getBlockZ() + 0.5);

            if (block.getType() == Material.IRON_ORE) {
                block.setType(Material.AIR);
                block.getState().update();
                block.getWorld().dropItemNaturally(clone, new ItemStack(Material.IRON_INGOT));
                ((ExperienceOrb) block.getWorld().spawn(clone, (Class) ExperienceOrb.class)).setExperience(3);
            } else if (block.getType() == Material.GOLD_ORE) {
                block.setType(Material.AIR);
                block.getState().update();
                ((ExperienceOrb) block.getWorld().spawn(clone, (Class) ExperienceOrb.class)).setExperience(3);
                block.getWorld().dropItemNaturally(clone, new ItemStack(Material.GOLD_INGOT));
            } else if (block.getType() == Material.GRAVEL) {
                block.setType(Material.AIR);
                block.getState().update();
                block.getWorld().dropItemNaturally(clone, new ItemStack(Material.FLINT));
            }
        }
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {

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
