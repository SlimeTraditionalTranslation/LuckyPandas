package dev.j3fftw.luckypanda.surprise.lucky;

import dev.j3fftw.luckypanda.Constants;
import dev.j3fftw.luckypanda.Utils;
import dev.j3fftw.luckypanda.surprise.Surprise;
import io.github.bakedlibs.dough.data.persistent.PersistentDataAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

public class PandaCannonSurprise implements Surprise {

    @Nonnull
    @Override
    public NamespacedKey getId() {
        return Utils.getKey("panda_cannon");
    }

    @Override
    public void process(@Nonnull Player player, @Nonnull Entity entity) {
        final ItemStack itemStack = new ItemStack(Material.BAMBOO);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.of("#0FA311") + "熊貓砲");
        PersistentDataAPI.setBoolean(itemMeta, Constants.PANDA_CANNON, true);
        itemStack.setItemMeta(itemMeta);
        entity.getWorld().dropItemNaturally(entity.getLocation(), itemStack);
    }
}
