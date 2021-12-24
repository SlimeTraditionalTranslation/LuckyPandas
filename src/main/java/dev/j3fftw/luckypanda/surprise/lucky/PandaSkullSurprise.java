package dev.j3fftw.luckypanda.surprise.lucky;

import dev.j3fftw.luckypanda.Constants;
import dev.j3fftw.luckypanda.Utils;
import dev.j3fftw.luckypanda.surprise.Surprise;
import io.github.bakedlibs.dough.skins.PlayerHead;
import io.github.bakedlibs.dough.skins.PlayerSkin;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

public class PandaSkullSurprise implements Surprise {

    @Nonnull
    @Override
    public NamespacedKey getId() {
        return Utils.getKey("panda_skull");
    }

    @Override
    public void process(@Nonnull Player player, @Nonnull Entity entity) {
        final ItemStack pandaSkull = PlayerHead.getItemStack(PlayerSkin.fromHashCode(Constants.PANDA_SKULL));
        final ItemMeta itemMeta = pandaSkull.getItemMeta();
        itemMeta.setDisplayName(ChatColor.BLUE + "熊貓頭");
        itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        itemMeta.addEnchant(Enchantment.OXYGEN, 3, true);
        itemMeta.addEnchant(Enchantment.MENDING, 1, true);
        itemMeta.addEnchant(Enchantment.WATER_WORKER, 1, true);
        pandaSkull.setItemMeta(itemMeta);
        Utils.dropItem(player, pandaSkull);
    }
}
