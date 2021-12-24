package dev.j3fftw.luckypanda;

import dev.j3fftw.luckypanda.surprise.Surprise;
import dev.j3fftw.luckypanda.surprise.lucky.LuckyPotion;
import dev.j3fftw.luckypanda.surprise.lucky.PandaBootsSurprise;
import dev.j3fftw.luckypanda.surprise.lucky.PandaCannonSurprise;
import dev.j3fftw.luckypanda.surprise.lucky.PandaChestplateSurprise;
import dev.j3fftw.luckypanda.surprise.lucky.PandaLeggingSurprise;
import dev.j3fftw.luckypanda.surprise.lucky.PandaSkullSurprise;
import dev.j3fftw.luckypanda.surprise.neutral.BabyPandaSurprise;
import dev.j3fftw.luckypanda.surprise.neutral.FlyingPandaSurprise;
import dev.j3fftw.luckypanda.surprise.neutral.PandaFirework;
import dev.j3fftw.luckypanda.surprise.neutral.StackedPandasSurprise;
import dev.j3fftw.luckypanda.surprise.neutral.TryAgainPandaSurprise;
import dev.j3fftw.luckypanda.surprise.unlucky.ExplodingPanda;
import dev.j3fftw.luckypanda.surprise.unlucky.HoleSurprise;
import dev.j3fftw.luckypanda.surprise.unlucky.JailAnvilSurprise;
import dev.j3fftw.luckypanda.surprise.unlucky.JailLavaSurprise;
import io.github.bakedlibs.dough.protection.ProtectionManager;
//import io.github.bakedlibs.dough.updater.GitHubBuildsUpdater;
//import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class LuckyPanda extends JavaPlugin {

    private static LuckyPanda instance;

    private final List<Surprise> enableSurprises = new ArrayList<>();

    private final List<Surprise> allSurprises = new ArrayList<>();

    private ProtectionManager protectionManager;

    @Override
    public void onEnable() {
        instance = this;

        addDefaultSurprises();
        getConfig().options().copyDefaults(true);
        saveConfig();

        //new Metrics(this, 13134);

        /*if (this.getConfig().getBoolean("options.auto-update")
            && !this.getDescription().getVersion().equals("Unofficial")
        ) {
            new GitHubBuildsUpdater(this, getFile(), "J3fftw/LuckyPandas/master").start();
        }*/

        getCommand("lucky").setExecutor(new LuckyCommand());
        getServer().getPluginManager().registerEvents(new Events(), this);

        protectionManager = new ProtectionManager(this.getServer());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public void addSurprise(Surprise surprise) {
        this.getConfig().addDefault(surprise.getId().toString(), true);
        if (this.getConfig().getBoolean(surprise.getId().toString())) {
            enableSurprises.add(surprise);
        }
    }

    public Surprise getRandomSurprise() {
        final int chance = ThreadLocalRandom.current().nextInt(0, 10);
        if (chance == 0) {
            final int randomValue = ThreadLocalRandom.current().nextInt(enableSurprises.size());
            return enableSurprises.get(randomValue);
        }
        return null;
    }

    public List<Surprise> getEnableSurprises() {
        return enableSurprises;
    }

    private void addDefaultSurprises() {
        this.addSurprise(new HoleSurprise());
        this.addSurprise(new JailAnvilSurprise());
        this.addSurprise(new JailLavaSurprise());
        this.addSurprise(new BabyPandaSurprise());
        this.addSurprise(new StackedPandasSurprise());
        this.addSurprise(new FlyingPandaSurprise());
        this.addSurprise(new TryAgainPandaSurprise());
        this.addSurprise(new PandaSkullSurprise());
        this.addSurprise(new PandaChestplateSurprise());
        this.addSurprise(new PandaLeggingSurprise());
        this.addSurprise(new PandaBootsSurprise());
        this.addSurprise(new ExplodingPanda());
        this.addSurprise(new PandaCannonSurprise());
        this.addSurprise(new LuckyPotion());
        this.addSurprise(new PandaFirework());
    }

    public ProtectionManager getProtectionManager() {
        return protectionManager;
    }

    public static LuckyPanda getInstance() {
        return instance;
    }

}
