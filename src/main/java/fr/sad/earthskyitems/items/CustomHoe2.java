package fr.sad.earthskyitems.items;

import com.bgsoftware.superiorskyblock.api.island.IslandPrivilege;
import fr.sad.earthskyitems.armor.ArmorEquipEvent;
import fr.sad.earthskyitems.manager.CustomItem;
import fr.sad.earthskyitems.utils.ItemCreator;
import fr.sad.earthskyitems.utils.PermissionUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class CustomHoe2  extends CustomItem {

    public CustomHoe2() {
        super("hoe2", new ItemCreator(Material.IRON_HOE).setUnbreakable(true).setName("§6Houe (2)").setLores("", "§7Cette houe permet de casser", "§7et de replanter en 5x5").getItem());
    }

    @Override
    public void onBreak(BlockBreakEvent event) {

    }

    @Override
    public void onPlace(BlockPlaceEvent event) {

    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        if(event.getClickedBlock() != null && event.getItem() != null){
            if(isSimilar(event.getItem())){
                if(PermissionUtils.canBuild(event.getPlayer(), event.getClickedBlock().getLocation(), IslandPrivilege.getByName("BREAK"))){
                    Block block = event.getClickedBlock();

                    if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
                        for(int x = block.getX() - 2; x <= block.getX() + 2; x++){
                            for(int z = block.getZ() - 2; z <= block.getZ() + 2; z++){
                                Block target = block.getWorld().getBlockAt(x, block.getY(), z);
                                Block upper = block.getWorld().getBlockAt(x, block.getY() + 1, z);
                                if(PermissionUtils.canBuild(event.getPlayer(), target.getLocation(), IslandPrivilege.getByName("BREAK")) && PermissionUtils.canBuild(event.getPlayer(), upper.getLocation(), IslandPrivilege.getByName("BREAK"))){
                                    if((target.getType() == Material.GRASS || target.getType() == Material.DIRT || target.getType() == Material.SOIL) && upper.getType() == Material.AIR){
                                        if(canPlant(event.getPlayer())){
                                            upper.setType(removePlant(event.getPlayer()));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(event.getAction() == Action.LEFT_CLICK_BLOCK){
                        for(int x = block.getX() - 2; x <= block.getX() + 2; x++){
                            for(int z = block.getZ() - 2; z <= block.getZ() + 2; z++){
                                Block target = block.getWorld().getBlockAt(x, block.getY(), z);
                                if(PermissionUtils.canBuild(event.getPlayer(), target.getLocation(), IslandPrivilege.getByName("BREAK"))){
                                    if(isFullyGrown(target)){
                                        target.breakNaturally();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private Material removePlant(Player player) {

        for(int index = 0; index < player.getInventory().getSize(); index++){
            ItemStack itemStack = player.getInventory().getItem(index);
            if(itemStack != null){
                if(itemStack.getType() == Material.SEEDS || itemStack.getType() == Material.CARROT_ITEM || itemStack.getType() == Material.POTATO_ITEM){
                    int newAmount = itemStack.getAmount() - 1;
                    if (newAmount > 0) {
                        itemStack.setAmount(newAmount);
                    }else {
                        player.getInventory().clear(index);
                    }
                }
                if(itemStack.getType() == Material.SEEDS){
                    return Material.CROPS;
                }
                if(itemStack.getType() == Material.CARROT_ITEM){
                    return Material.CARROT;
                }
                if(itemStack.getType() == Material.POTATO_ITEM){
                    return Material.POTATO;
                }
            }
        }
        return null;
    }

    private boolean canPlant(Player player) {
        return player.getInventory().contains(Material.SEEDS) || player.getInventory().contains(Material.CARROT_ITEM) || player.getInventory().contains(Material.POTATO_ITEM);
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

    public boolean isFullyGrown(Block block) {
        int data = block.getData();
        Material type = block.getType();

        if(type.equals(Material.CROPS) && data == 7){
            return true;
        }
        if(type.equals(Material.CARROT) && data == 7){
            return true;
        }
        if(type.equals(Material.POTATO) && data == 7){
            return true;
        }
        return false;
    }
}
