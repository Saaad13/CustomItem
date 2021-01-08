package fr.sad.earthskyitems.manager;

import fr.sad.earthskyitems.armor.ArmorEquipEvent;
import fr.sad.earthskyitems.utils.ItemCreator;
import lombok.Getter;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public abstract class CustomItem {

    private final String id;
    private ItemStack itemStack;
    private int durability;

    public CustomItem(String id, ItemStack itemStack){
        this.id = id;
        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound nbtTagCompound = nmsItem.getTag();
        NBTTagString nbtTagString = new NBTTagString(this.id);
        nbtTagCompound.set("customItem", nbtTagString);
        nmsItem.setTag(nbtTagCompound);
        this.itemStack = CraftItemStack.asBukkitCopy(nmsItem);
        this.durability = -1;
    }

    public CustomItem(String id, int durability, ItemStack itemStack){
        this.id = id;

        itemStack = new ItemCreator(itemStack).setUnbreakable(true).addLore("").addLore("§7Durabilité: §e" + durability).getItem();;

        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound nbtTagCompound = nmsItem.getTag();
        NBTTagString nbtTagString = new NBTTagString(this.id);
        nbtTagCompound.set("customItem", nbtTagString);
        nmsItem.setTag(nbtTagCompound);
        this.itemStack = CraftItemStack.asBukkitCopy(nmsItem);

        this.durability = durability;
    }

    public void removeDurability(Player player, ItemStack itemStack){
        int durability = Integer.parseInt(itemStack.getItemMeta().getLore().get(itemStack.getItemMeta().getLore().size() - 1).replaceAll("§7Durabilité: §e", "")) - 1;
        updateItem(player, itemStack, durability);
    }

    public void updateItem(Player player, ItemStack itemStack, int durability){
        if(durability <= 0){
            player.setItemInHand(new ItemStack(Material.AIR));
        }else{
            ItemMeta itemMeta = itemStack.getItemMeta();

            List<String> oldLores = new ArrayList<>(itemMeta.getLore());
            
            oldLores.removeIf(s -> s.contains("§7Durabilité: "));
            oldLores.add("§7Durabilité: §e" + durability);

            itemMeta.setLore(oldLores);
            itemStack.setItemMeta(itemMeta);
            player.setItemInHand(itemStack);
        }
    }

    public boolean isSimilar(ItemStack itemStack){
        return itemStack != null && itemStack.getType() != Material.AIR && CraftItemStack.asNMSCopy(itemStack).hasTag() && CraftItemStack.asNMSCopy(itemStack).getTag().hasKey("customItem") && CraftItemStack.asNMSCopy(itemStack).getTag().getString("customItem").equalsIgnoreCase(this.id);
    }

    public abstract void onBreak(BlockBreakEvent event);
    public abstract void onPlace(BlockPlaceEvent event);
    public abstract void onInteract(PlayerInteractEvent event);
    public abstract void onEquip(ArmorEquipEvent event);
    public abstract void onDeath(PlayerDeathEvent event);
    public abstract void onSpawn(PlayerRespawnEvent event);
    public abstract void onFish(PlayerFishEvent event);

}
