package me.kes.experimental;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BowClass implements Listener {
    public final Experimental plugin;
    private Arrow explosiveArrow;
    String message = (ChatColor.RED + "I have now become death, destroyers of worlds.");
    @EventHandler
    public void OnShoot(EntityShootBowEvent event) {
        Entity entity = event.getProjectile();
        if (entity instanceof Arrow) {
            Arrow arrow = (Arrow) entity;
            Player shooter = (Player) arrow.getShooter();

            if (shooter == null) {
                return;
            }

            if (shooter.getInventory().getItemInMainHand().equals(plugin.Bow())) {
                explosiveArrow = arrow;
            }
        }
    }

    @EventHandler
    public void OnHit(ProjectileHitEvent event){
        Arrow arrow = (Arrow) event.getEntity();
        Player shooter = (Player) arrow.getShooter();
        if (shooter == null){
            return;
        }
        Vector PlayerEyeDirection = shooter.getLocation().getDirection().normalize().multiply(10);
        if (event.getEntity() == explosiveArrow){
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                explosiveArrow.setVelocity(PlayerEyeDirection);
                explosiveArrow.getWorld().createExplosion(explosiveArrow.getLocation(), 10.0f, true, true);
            }, 0);
        }
    }
}
