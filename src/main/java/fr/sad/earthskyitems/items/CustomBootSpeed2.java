package fr.sad.earthskyitems.items;

import fr.sad.earthskyitems.armor.ArmorEquipEvent;
import fr.sad.earthskyitems.manager.CustomItem;
import fr.sad.earthskyitems.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomBootSpeed2 extends CustomItem {

    public CustomBootSpeed2() {
        super("speed2", new ItemCreator(Material.DIAMOND_BOOTS).setUnbreakable(true).setName("§bBottes d'Astronaute").setLores("", "§7Ces bottes vous permettent", "§7d'obtenir §bspeed II").getItem());
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
        if(event.getNewArmorPiece() == null || event.getNewArmorPiece().getType() == Material.AIR){
            if(isSimilar(event.getOldArmorPiece())){
                event.getPlayer().removePotionEffect(PotionEffectType.SPEED);
            }
        }
        if(event.getNewArmorPiece() != null){
            if(isSimilar(event.getNewArmorPiece())){
                event.getPlayer().removePotionEffect(PotionEffectType.SPEED);
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
            }
        }
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
