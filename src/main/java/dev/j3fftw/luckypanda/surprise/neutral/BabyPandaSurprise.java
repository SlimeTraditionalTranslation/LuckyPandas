package dev.j3fftw.luckypanda.surprise.neutral;

import dev.j3fftw.luckypanda.Utils;
import dev.j3fftw.luckypanda.surprise.Surprise;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadLocalRandom;

public class BabyPandaSurprise implements Surprise {

    @Nonnull
    @Override
    public NamespacedKey getId() {
        return Utils.getKey("baby_panda");
    }

    @Override
    public void process(@Nonnull Player player, @Nonnull Entity entity) {
        final Location location = player.getLocation();
        for (int i = 1; i <= 10; i++) {
            final Panda panda = Utils.spawnSurprisedPanda(location.clone().add(
                ThreadLocalRandom.current().nextDouble(5),
                ThreadLocalRandom.current().nextDouble(1),
                ThreadLocalRandom.current().nextDouble(5))
            );

            panda.setBaby();
            panda.setAgeLock(true);
            panda.setCustomNameVisible(true);
            if (i == 1) {
                setRarePanda(location, panda, player);
            }
        }
    }

    private void setRarePanda(@Nonnull Location location, Panda panda, Player player) {
        final int rarePanda = ThreadLocalRandom.current().nextInt(5);
        switch (rarePanda) {
            case 0:
                panda.setCustomName(ChatColor.of("#FB9900") + "全能的 Walshy");
                panda.setMainGene(Panda.Gene.AGGRESSIVE);
                panda.setTarget(player);
                panda.setCanPickupItems(true);
                panda.isBreedItem(Material.ACACIA_FENCE_GATE);
                break;
            case 1:
                panda.setCustomName(ChatColor.of("#0099FF") + "瘋狂的 Sefi");
                panda.setMainGene(Panda.Gene.PLAYFUL);
                panda.setAdult();
                location.getWorld().spawnEntity(location, EntityType.BOAT);
                panda.isBreedItem(Material.BEETROOT_SEEDS);
                break;
            case 2:
                panda.setCustomName(ChatColor.of("#803CA1") + "懶惰的 Jeff");
                panda.setMainGene(Panda.Gene.LAZY);
                panda.setVisualFire(true);
                break;
            case 3:
                final int chance = ThreadLocalRandom.current().nextInt(10);
                if (chance == 1) {
                    panda.setCustomName(ChatColor.of("#0704E0") + "瑪西藍莓");
                } else {
                    // Sorry blame Walshy
                    panda.setCustomName(ChatColor.of("#0704E0") + "一個蠢貨");
                }
                panda.setMainGene(Panda.Gene.WORRIED);
                panda.isBreedItem(Material.WAXED_EXPOSED_CUT_COPPER_STAIRS);
                panda.setGlowing(true);
                break;
            case 4:
                panda.setCustomName(ChatColor.of("#363680") + "忙碌的 Biscuit");
                panda.setMainGene(Panda.Gene.WORRIED);
                panda.isBreedItem(Material.COOKIE);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + rarePanda);
        }
    }
}
