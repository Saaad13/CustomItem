package fr.sad.earthskyitems.items;

import fr.sad.earthskyitems.armor.ArmorEquipEvent;
import fr.sad.earthskyitems.manager.CustomItem;
import fr.sad.earthskyitems.utils.ItemCreator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class CustomGrappin extends CustomItem {

    private final HashMap<UUID, Long> cooldown = new HashMap<>();

    public CustomGrappin() {
        super("grappin", new ItemCreator(Material.FISHING_ROD).setName("§6Grappin").setUnbreakable(true).getItem());
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
        if(event.getState() == PlayerFishEvent.State.FISHING) return;

        if(isSimilar(event.getPlayer().getItemInHand())){
            if (!cooldown.containsKey(event.getPlayer().getUniqueId())) {
                cooldown.put(event.getPlayer().getUniqueId(), System.currentTimeMillis() + 5000);
                pullEntityToLocation(event.getPlayer(), event.getHook().getLocation().add(0, 4, 0));
                return;
            }
            if (cooldown.containsKey(event.getPlayer().getUniqueId()) && cooldown.get(event.getPlayer().getUniqueId()) < System.currentTimeMillis()) {
                cooldown.put(event.getPlayer().getUniqueId(), System.currentTimeMillis() + 5000);
                pullEntityToLocation(event.getPlayer(), event.getHook().getLocation().add(0, 4, 0));
                return;
            }
            int time = (int) (cooldown.get(event.getPlayer().getUniqueId()) - System.currentTimeMillis()) / 1000;
            event.getPlayer().sendMessage("§cVeuillez attendre " + time + " seconde" + (time > 1 ? "s" : ""));
        }
    }

    private void pullEntityToLocation(final Entity e, final Location loc) {
        Location entityLoc = e.getLocation();
        final double t = loc.distance(entityLoc);
        final double v_x = (1.0 + 0.07 * t) * (loc.getX() - entityLoc.getX()) / t;
        final double v_y = (1.0 + 0.03 * t) * (loc.getY() - entityLoc.getY()) / t - -0.04 * t;
        final double v_z = (1.0 + 0.07 * t) * (loc.getZ() - entityLoc.getZ()) / t;
        final Vector v = e.getVelocity();
        v.setX(v_x);
        v.setY(v_y);
        v.setZ(v_z);
        e.setVelocity(v);
    }
}
