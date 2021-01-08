package fr.sad.earthskyitems.items;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class CustomSwordDeath extends CustomItem {

    private final HashMap<UUID, Integer> map = Maps.newHashMap();

    public CustomSwordDeath() {
        super("sword_unlimited", new ItemCreator(Material.DIAMOND_SWORD).setUnbreakable(true).setName("§6Epée Spatial").setLores("", "§7Cette épée reste dans votre", "§7inventaire même après votre mort.").getItem());
    }

    @Override
    public  void onBreak(BlockBreakEvent event) {

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
        boolean have = event.getDrops().stream().anyMatch(this::isSimilar);

        if(have){
            List<Integer> integers = Lists.newArrayList();
            final int[] index = {0};

            event.getDrops().forEach(itemStack -> {
                if(isSimilar(itemStack))   {
                    integers.add(index[0]);
                }
                index[0]++;
            });
            map.put(event.getEntity().getUniqueId(), integers.size());
            List <ItemStack> drops = event.getDrops();
            Iterator<ItemStack> iterator = drops.iterator();
            while(iterator.hasNext()){
                if(isSimilar(iterator.next()))
                    iterator.remove();
            }
        }
    }

    @Override
    public void onSpawn(PlayerRespawnEvent event) {
        if(map.containsKey(event.getPlayer().getUniqueId())){
            for(int index = 0; index < map.get(event.getPlayer().getUniqueId()); index++)
                event.getPlayer().getInventory().addItem(getItemStack());

            map.remove(event.getPlayer().getUniqueId());
        }
    }

    @Override
    public void onFish(PlayerFishEvent event) {

    }

}
