package fr.sad.earthskyitems.utils;

import com.bgsoftware.superiorskyblock.api.island.IslandPrivilege;
import com.google.common.collect.Maps;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class HammerUtils {

    public static HashMap<UUID, BlockFace> playerRelatives = Maps.newHashMap();

    public static void destroy(Player player, ItemStack itemStack, Block block) {

        if (playerRelatives.get(player.getUniqueId()) == null || playerRelatives.get(player.getUniqueId()) == BlockFace.DOWN || playerRelatives.get(player.getUniqueId()) == BlockFace.UP) {
            processBlock(player, block.getRelative(BlockFace.NORTH), itemStack);
            processBlock(player, block.getRelative(BlockFace.NORTH_EAST), itemStack);
            processBlock(player, block.getRelative(BlockFace.EAST), itemStack);
            processBlock(player, block.getRelative(BlockFace.SOUTH_EAST), itemStack);
            processBlock(player, block.getRelative(BlockFace.SOUTH), itemStack);
            processBlock(player, block.getRelative(BlockFace.SOUTH_WEST), itemStack);
            processBlock(player, block.getRelative(BlockFace.WEST), itemStack);
            processBlock(player, block.getRelative(BlockFace.NORTH_WEST), itemStack);
        }
        if (playerRelatives.get(player.getUniqueId()) == BlockFace.EAST || playerRelatives.get(player.getUniqueId()) == BlockFace.WEST) {
            processBlock(player, block.getRelative(BlockFace.UP), itemStack);
            processBlock(player, block.getRelative(BlockFace.DOWN), itemStack);
            processBlock(player, block.getRelative(BlockFace.NORTH), itemStack);
            processBlock(player, block.getRelative(BlockFace.SOUTH), itemStack);
            processBlock(player, block.getRelative(BlockFace.NORTH).getRelative(BlockFace.UP), itemStack);
            processBlock(player, block.getRelative(BlockFace.NORTH).getRelative(BlockFace.DOWN), itemStack);
            processBlock(player, block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.UP), itemStack);
            processBlock(player, block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.DOWN), itemStack);
        }
        if (playerRelatives.get(player.getUniqueId()) == BlockFace.NORTH || playerRelatives.get(player.getUniqueId()) == BlockFace.SOUTH) {
            processBlock(player, block.getRelative(BlockFace.UP), itemStack);
            processBlock(player, block.getRelative(BlockFace.DOWN), itemStack);
            processBlock(player, block.getRelative(BlockFace.EAST), itemStack);
            processBlock(player, block.getRelative(BlockFace.WEST), itemStack);
            processBlock(player, block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP), itemStack);
            processBlock(player, block.getRelative(BlockFace.EAST).getRelative(BlockFace.DOWN), itemStack);
            processBlock(player, block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP), itemStack);
            processBlock(player, block.getRelative(BlockFace.WEST).getRelative(BlockFace.DOWN), itemStack);
        }
    }

    private static void processBlock(Player player, Block block, ItemStack itemStack) {
        if(!PermissionUtils.canBuild(player, block.getLocation(), IslandPrivilege.getByName("BREAK")) || block.isLiquid() || block.getType() == Material.BEDROCK || block.getType() == Material.OBSIDIAN || block.getType().equals(Material.AIR))
            return;
        block.breakNaturally();
    }

}
