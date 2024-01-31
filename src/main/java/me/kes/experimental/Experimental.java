package me.kes.experimental;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public final class Experimental extends JavaPlugin {

    @Getter public BowClass bowClass;
    String Start = ChatColor.AQUA + "Experimental Plugin has been enabled.";
    String end = ChatColor.RED + "Experimental Plugin has been disabled";

    @Override
    public void onEnable() {
        CommandClass commandClass = new CommandClass(this);
        getCommand("getbow").setExecutor(commandClass);
        getCommand("getName").setExecutor(commandClass);
        PluginManager pm = getServer().getPluginManager();
        bowClass = new BowClass(this);
        pm.registerEvents(bowClass, this); // Pass the plugin instance
        getLogger().info(Start);
    }

    @Override
    public void onDisable() {
        getLogger().info(end);
    }

    public ItemStack Bow() {
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "A kewl Bow that shoots out explosive arrows.");
        lore.add(ChatColor.YELLOW + "Fully developed by Kes.");
        lore.add(" ");
        ItemStack itemStack = new ItemStack(Material.BOW, 1);
        ItemMeta itemmeta = itemStack.getItemMeta();

        assert itemmeta != null;
        itemmeta.setLore(lore);
        itemmeta.setDisplayName(ChatColor.RED + "Bow o' Explosives");
        itemmeta.addEnchant(Enchantment.ARROW_FIRE, 3, true);
        itemmeta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
        itemStack.setItemMeta(itemmeta);
        return itemStack;
    }
    @EventHandler
    public void entity EntitySpawnEvent(){
        Entity entity = event.getEntity();
        if (entity == Minecraft.Pig){
            Bukkit.broadcast("Entity is nullable");
            System.import.execution().kill(entity);
}
