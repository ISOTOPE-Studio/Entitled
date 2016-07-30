package cc.isotopestudio.Entitled.task;

import cc.isotopestudio.Entitled.type.AttriType;
import cc.isotopestudio.Entitled.type.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mars on 7/30/2016.
 * Copyright ISOTOPE Studio
 */
public class TitlesEffectTask extends BukkitRunnable {

    public static final Map<Player, Title> map = new HashMap<>();

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Title newTitle = Title.getTitleFromPlayer(player);
            Title oldTitle = map.get(player);

            if (newTitle == null) {
                if (oldTitle != null) {
                    /*
                    if (oldTitle.getAttriSet().contains(AttriType.SPEED)) {
                        player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
                    }
                    if (oldTitle.getAttriSet().contains(AttriType.LIFE)) {

                    }
                    */
                    map.remove(player);
                }
                continue;
            }

            //if (newTitle.equals(map.get(player))) continue;

            map.put(player, newTitle);
            player.sendMessage(newTitle.getName());
            if (newTitle.getAttriSet().contains(AttriType.SPEED)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6 * 20,
                        (int) (newTitle.getParameters().get(AttriType.LIFE)[0] - 1), true), true);
            }
            if (newTitle.getAttriSet().contains(AttriType.LIFE)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 6 * 20,
                        (int) (newTitle.getParameters().get(AttriType.LIFE)[0] - 1), true), true);
            }
        }
    }
}
