package dev.j3fftw.luckypanda.surprise.lucky;

import dev.j3fftw.luckypanda.Utils;
import dev.j3fftw.luckypanda.surprise.Surprise;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;

public class LuckyPotion implements Surprise {

    @Nonnull
    @Override
    public NamespacedKey getId() {
        return Utils.getKey("lucky_potion");
    }

    @Override
    public void process(@Nonnull Player player, @Nonnull Entity entity) {
        final ItemStack itemStack = new ItemStack(Material.POTION);
        final PotionMeta itemMeta = (PotionMeta) itemStack.getItemMeta();
        final int duration = 300;
        final int amplifier = 4;
        itemMeta.setDisplayName(ChatColor.of("#0FA311") + "幸運藥水");
        Utils.applyPotion(itemMeta, PotionEffectType.LUCK, duration, amplifier);
        Utils.applyPotion(itemMeta, PotionEffectType.REGENERATION, duration, amplifier);
        Utils.applyPotion(itemMeta, PotionEffectType.FIRE_RESISTANCE, duration, amplifier);
        Utils.applyPotion(itemMeta, PotionEffectType.HEALTH_BOOST, duration, amplifier);
        Utils.applyPotion(itemMeta, PotionEffectType.DOLPHINS_GRACE, duration, amplifier);
        Utils.applyPotion(itemMeta, PotionEffectType.FAST_DIGGING, duration, amplifier);
        Utils.applyPotion(itemMeta, PotionEffectType.WATER_BREATHING, duration, amplifier);
        Utils.applyPotion(itemMeta, PotionEffectType.SATURATION, duration, amplifier);
        Utils.applyPotion(itemMeta, PotionEffectType.NIGHT_VISION, duration, amplifier);
        itemStack.setItemMeta(itemMeta);
        Utils.dropItem(player, itemStack);
    }
}
