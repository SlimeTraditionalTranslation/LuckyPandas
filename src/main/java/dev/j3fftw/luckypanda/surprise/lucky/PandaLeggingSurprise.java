package dev.j3fftw.luckypanda.surprise.lucky;

import dev.j3fftw.luckypanda.Utils;
import dev.j3fftw.luckypanda.surprise.Surprise;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import javax.annotation.Nonnull;

public class PandaLeggingSurprise implements Surprise {

    @Nonnull
    @Override
    public NamespacedKey getId() {
        return Utils.getKey("panda_leggings");
    }

    @Override
    public void process(@Nonnull Player player, @Nonnull Entity entity) {
        final ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        final LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) leggings.getItemMeta();
        leatherArmorMeta.setDisplayName(ChatColor.BLUE + "熊貓護腿");
        leatherArmorMeta.setColor(Color.fromRGB(255, 255, 255));
        leatherArmorMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        leatherArmorMeta.addEnchant(Enchantment.DURABILITY, 10, true);
        leatherArmorMeta.addEnchant(Enchantment.MENDING, 1, true);
        leggings.setItemMeta(leatherArmorMeta);
        Utils.dropItem(player, leggings);
    }
}
