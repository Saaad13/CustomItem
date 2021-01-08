package fr.sad.earthskyitems.manager;

import com.google.common.collect.Sets;
import fr.sad.earthskyitems.items.*;
import lombok.Getter;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

@Getter
public class CustomItemManager {

    private Set<CustomItem> customItemSet = Sets.newHashSet();

    public void register() {
        customItemSet.add(new CustomLegendaryHelmet());
        customItemSet.add(new CustomLegendaryChestplate());
        customItemSet.add(new CustomLeggingLegendary());
        customItemSet.add(new CustomLegendaryBoot());

        customItemSet.add(new CustomBootSpeed1());
        customItemSet.add(new CustomBootSpeed2());

        customItemSet.add(new CustomSwordCheat());
        customItemSet.add(new CustomPickaxeCheat());
        customItemSet.add(new CustomPickaxeCheat2());
        customItemSet.add(new CustomShovelCheat());
        customItemSet.add(new CustomMultiTool());

        customItemSet.add(new CustomSwordDeath());
        customItemSet.add(new CustomBowDeath());
        customItemSet.add(new CustomGrappin());

        customItemSet.add(new CustomHammer());
        customItemSet.add(new CustomPickaxe());
        customItemSet.add(new CustomFour());

        customItemSet.add(new CustomHoe());
        customItemSet.add(new CustomHoe2());
    }

    public boolean isCustomItem(ItemStack itemStack){
        return CraftItemStack.asNMSCopy(itemStack).hasTag() && CraftItemStack.asNMSCopy(itemStack).getTag().hasKey("customItem");
    }

    public boolean isCustomItem(String id){
        return customItemSet.stream().anyMatch(customItem -> customItem.getId().equalsIgnoreCase(id));
    }

    public CustomItem toCustomItem(String id){
        return customItemSet.stream().filter(customItem -> customItem.getId().equalsIgnoreCase(id)).findFirst().get();
    }
}
