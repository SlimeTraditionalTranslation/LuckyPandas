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

public class PandaBootsSurprise implements Surprise {

    @Nonnull
    @Override
    public NamespacedKey getId() {
        return Utils.getKey("panda_boots");
    }

    @Override
    public void process(@Nonnull Player player, @Nonnull Entity entity) {
        final ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        final LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) boots.getItemMeta();
        leatherArmorMeta.setDisplayName(ChatColor.BLUE + "熊貓靴");
        leatherArmorMeta.setColor(Color.fromRGB(0, 0, 0));
        leatherArmorMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        leatherArmorMeta.addEnchant(Enchantment.DURABILITY, 10, true);
        leatherArmorMeta.addEnchant(Enchantment.MENDING, 1, true);
        leatherArmorMeta.addEnchant(Enchantment.SOUL_SPEED, 3, true);
        leatherArmorMeta.addEnchant(Enchantment.DEPTH_STRIDER, 3, true);
        leatherArmorMeta.addEnchant(Enchantment.PROTECTION_FALL, 5, true);
        boots.setItemMeta(leatherArmorMeta);
        Utils.dropItem(player, boots);
    }
}
