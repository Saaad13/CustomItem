package fr.sad.earthskyitems.commands;

import fr.sad.earthskyitems.Main;
import fr.sad.earthskyitems.manager.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CommandItems implements CommandExecutor {

    private final Main main;

    public CommandItems(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(!commandSender.hasPermission("items.give")){
            commandSender.sendMessage("§cVous n'avez pas la permission.");
            return false;
        }

        if(args.length == 0){
            commandSender.sendMessage("§b/items list");
            commandSender.sendMessage("§b/items give <joueur> <item>");
            return false;
        }

        if(args[0].equalsIgnoreCase("list")){
            String str = "§b";
            for(CustomItem customItem :main.getCustomItemManager().getCustomItemSet())
                str+= customItem.getId() + ", ";

            commandSender.sendMessage(str);
        }

        if(args[0].equalsIgnoreCase("give")){
            if(args.length < 3){
                commandSender.sendMessage("§b/items give <joueur> <item>");
                return false;
            }
            Player target = Bukkit.getPlayer(args[1]);

            if(target == null){
                commandSender.sendMessage("§cCe joueur n'est pas connecté.");
                return false;
            }
            String id = args[2].toLowerCase();

            if(!main.getCustomItemManager().isCustomItem(id)){
                commandSender.sendMessage("§cCet item n'existe pas.");
                return false;
            }
            CustomItem customItem = main.getCustomItemManager().toCustomItem(id);
            HashMap<Integer, ItemStack> ignored = target.getInventory().addItem(customItem.getItemStack());
            for(Map.Entry<Integer, ItemStack> entry : ignored.entrySet())
                target.getWorld().dropItemNaturally(target.getLocation().add(0, 1, 0), entry.getValue());

            commandSender.sendMessage("§aVous avez envoyé §b" + id + " §aà §e" + target.getName());
            target.sendMessage("§aVous avez reçu §b" + id + " §ade la part de §e" + commandSender.getName());
        }

        return false;
    }
}
