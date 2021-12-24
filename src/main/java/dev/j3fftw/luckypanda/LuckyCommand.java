package dev.j3fftw.luckypanda;

import dev.j3fftw.luckypanda.surprise.Surprise;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

public class LuckyCommand implements CommandExecutor {

    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage("這個指令只能由玩家執行");
            return true;
        }
        final Player player = (Player) commandSender;
        if (player.hasPermission("luckypanda.admin")) {
            if (args.length != 0) {
                for (Surprise surprise : LuckyPanda.getInstance().getEnableSurprises()) {
                    if (surprise.getId().getKey().equalsIgnoreCase(args[0])) {
                        surprise.process(player, player);
                        return true;
                    }
                }
                player.sendMessage(ChatColor.RED + "這個ID並沒有驚喜, 請在試一次");
            } else {
                chooseSurprise().process(player, player);
            }
        } else {
            player.sendMessage(ChatColor.RED + "你沒有權限執行這個指令.");
        }
        return true;
    }

    private Surprise chooseSurprise() {
        double random = ThreadLocalRandom.current().nextDouble() + ThreadLocalRandom.current().nextInt(101);

        return LuckyPanda.getInstance().getRandomSurprise();
    }
}
