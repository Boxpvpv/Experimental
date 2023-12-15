package me.kes.experimental;

import lombok.RequiredArgsConstructor;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class CommandClass implements CommandExecutor {
    public final Experimental plugin;
    private int emptyslot;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("getbow")) {
            if (!(p.hasPermission("op"))) {
                p.sendMessage(ChatColor.RED + "You do not have the required permissions to execute this command.");
                return false;
            }
            if (p.getInventory().getItemInMainHand().getType() == (Material.AIR)) {
                p.getInventory().setItemInMainHand(plugin.Bow());
                p.sendMessage(ChatColor.GREEN + "Enjoy your Bow o' Explosives");
                return false;
            }
            else{
                ItemStack helditem = p.getInventory().getItemInMainHand();
                for (int i = 0; i < p.getInventory().getSize(); i++){
                    if (p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) {
                        emptyslot = i;
                        break;
                    }
                    else{
                        p.sendMessage(ChatColor.RED + "Your Inventory is currently full, please empty something.");
                    }
                }
                p.getInventory().setItem(emptyslot, helditem);
                p.getInventory().setItemInMainHand(plugin.Bow());
                p.sendMessage(ChatColor.GREEN + "Enjoy your Bow o' Explosives");
                helditem.setAmount(0);
            }
        }
        else if (command.getName().equalsIgnoreCase("getName")){
            String heldItem = p.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
            p.sendMessage(ChatColor.AQUA + " " + ChatColor.BOLD + heldItem);
        }
        return false;
    }
}
