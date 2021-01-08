package fr.sad.earthskyitems.items;

import fr.sad.earthskyitems.armor.ArmorEquipEvent;
import fr.sad.earthskyitems.manager.CustomItem;
import fr.sad.earthskyitems.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class CustomLegendaryChestplate extends CustomItem {

    public CustomLegendaryChestplate() {
        super("legendary_chestplate", new ItemCreator(Material.DIAMOND_CHESTPLATE).setName("§bPlastron Légendaire").setUnbreakable(true).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).getItem());
    }

    @Override
    public void onBreak(BlockBreakEvent event) {

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
