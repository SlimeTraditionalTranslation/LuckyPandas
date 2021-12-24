package dev.j3fftw.luckypanda.surprise.neutral;

import dev.j3fftw.luckypanda.Utils;
import dev.j3fftw.luckypanda.surprise.Surprise;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class TryAgainPandaSurprise implements Surprise {

    @Nonnull
    @Override
    public NamespacedKey getId() {
        return Utils.getKey("try_again_panda");
    }

    @Override
    public void process(@Nonnull Player player, @Nonnull Entity entity) {
        entity.getLocation().getWorld().spawnEntity(entity.getLocation().add(0, 1, 0), EntityType.PANDA);
        player.sendMessage(ChatColor.BLUE + "你獲得另一個機會");
    }
}
