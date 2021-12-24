package dev.j3fftw.luckypanda;

import dev.j3fftw.luckypanda.surprise.Surprise;
import io.github.bakedlibs.dough.data.persistent.PersistentDataAPI;
import io.github.bakedlibs.dough.protection.Interaction;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Events implements Listener {

    @EventHandler
    public void onPandaDeath(EntityDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        Entity killed = event.getEntity();
        if (killed instanceof Panda
            && killer != null
            && !Utils.isSurprisePanda((Panda) killed)
        ) {
            Surprise surprise = LuckyPanda.getInstance().getRandomSurprise();
            if (surprise != null) {
                surprise.process(killer, killed);
                killer.sendMessage(ChatColor.GREEN + "你這次很幸運!");
            } else {
                killer.sendMessage(ChatColor.GREEN + "你到底為甚麼要這樣做?");
            }
        }
    }

    /**
     * Credits to Sefiraat for explaining/actually telling me what to write
     */
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player
            && event.getDamager() instanceof Panda
        ) {
            Panda damager = (Panda) event.getDamager();
            if (PersistentDataAPI.getBoolean(damager, Constants.EXPLOSIVE_PANDA)) {
                final Player damaged = (Player) event.getEntity();
                final Location location = damager.getLocation();
                final double range = 7;
                final int density = 20;
                location.getWorld().createExplosion(location, (float) range, true);
                for (double height = 0; height <= Math.PI; height += Math.PI / density) {
                    for (int i = 0; i < range; i++) {
                        final double radius = i * Math.sin(height);
                        final double y = i * Math.cos(height);
                        for (double angle = 0; angle < Math.PI * 2; angle += Math.PI / density) {
                            final double x = Math.cos(angle) * radius;
                            final double z = Math.sin(angle) * radius;
                            final Location block = location.clone().add(x, y, z);
                            if (LuckyPanda.getInstance().getProtectionManager()
                                .hasPermission(damaged, block, Interaction.BREAK_BLOCK)
                            ) {
                                block.getBlock().breakNaturally();
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onCanonUse(PlayerInteractEvent event) {
        final ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
        if (!itemStack.getType().isAir() && itemStack.hasItemMeta()) {
            final ItemMeta itemMeta = itemStack.getItemMeta();
            final Location location = event.getPlayer().getLocation();
            if (PersistentDataAPI.getBoolean(itemMeta, Constants.PANDA_CANNON)) {
                Panda panda = Utils.spawnSurprisedPanda(location);
                PersistentDataAPI.setBoolean(panda, Constants.CANNONED_PANDA, true);
                panda.setVelocity(location.getDirection().multiply(3));
            }
        }
    }

    @EventHandler
    public void onPandaCanonFallDamage(EntityDamageEvent event) {
        if (PersistentDataAPI.getBoolean(event.getEntity(), Constants.CANNONED_PANDA)
            && event.getCause() == EntityDamageEvent.DamageCause.FALL
        ) {
            event.setCancelled(true);
            PersistentDataAPI.remove(event.getEntity(), Constants.CANNONED_PANDA);
        }
    }

    @EventHandler
    public void onFireworkExplode(FireworkExplodeEvent event) {
        if (PersistentDataAPI.hasBoolean(event.getEntity(), Constants.SURPRISE_FIREWORK)) {
            for (Entity passenger : event.getEntity().getPassengers()) {
                passenger.remove();
            }
        }
    }
}