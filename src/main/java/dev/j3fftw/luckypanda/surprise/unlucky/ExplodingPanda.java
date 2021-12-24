package dev.j3fftw.luckypanda.surprise.unlucky;

import dev.j3fftw.luckypanda.Constants;
import dev.j3fftw.luckypanda.Utils;
import dev.j3fftw.luckypanda.surprise.Surprise;
import io.github.bakedlibs.dough.data.persistent.PersistentDataAPI;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class ExplodingPanda implements Surprise {

    @Nonnull
    @Override
    public NamespacedKey getId() {
        return Utils.getKey("exploding_panda");
    }

    @Override
    public void process(@Nonnull Player player, @Nonnull Entity entity) {
        Panda panda = Utils.spawnSurprisedPanda(entity.getLocation());
        panda.setTarget(player);
        panda.setMainGene(Panda.Gene.AGGRESSIVE);
        panda.setCustomName(ChatColor.DARK_RED + "炸彈熊貓");
        PersistentDataAPI.setBoolean(panda, Constants.EXPLOSIVE_PANDA, true);
    }
}