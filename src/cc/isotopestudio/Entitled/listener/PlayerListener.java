package cc.isotopestudio.Entitled.listener;

import cc.isotopestudio.Entitled.task.TitlesEffectTask;
import cc.isotopestudio.Entitled.type.AttriType;
import cc.isotopestudio.Entitled.type.Title;
import cc.isotopestudio.Entitled.util.S;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Mars on 7/30/2016.
 * Copyright ISOTOPE Studio
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        TitlesEffectTask.map.remove(event.getPlayer());
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;
        double damage = -1;
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            Title title = Title.getTitleFromPlayer(player);
            if (title == null) return;
            damage = event.getDamage();
            if (title.getAttriSet().contains(AttriType.CRITICAL)) {
                if (Math.random() < title.getParameters().get(AttriType.CRITICAL)[0]) {
                    damage *= title.getParameters().get(AttriType.CRITICAL)[1];
                    player.sendMessage(S.toPrefixYellow("±©»÷!"));
                }
            }
            if (title.getAttriSet().contains(AttriType.ADDITIONAL)) {
                damage += title.getParameters().get(AttriType.ADDITIONAL)[0];
            }
            event.setDamage(damage);
        }

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Title title = Title.getTitleFromPlayer(player);
            if (title == null) return;
            if (title.getAttriSet().contains(AttriType.DODGE)) {
                if (Math.random() < title.getParameters().get(AttriType.DODGE)[0]) {
                    event.setCancelled(true);
                }
            }
            if (title.getAttriSet().contains(AttriType.DEFENSE)) {
                if (damage == -1) damage = event.getDamage();
                damage -= title.getParameters().get(AttriType.DEFENSE)[0];
                if (damage < 0) damage = 0;
                event.setDamage(damage);
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Player player = event.getEntity().getKiller();
            Title title = Title.getTitleFromPlayer(player);
            if (title == null) return;
            if (title.getAttriSet().contains(AttriType.EXPERIENCE)) {
                event.setDroppedExp((int) Math.round(
                        event.getDroppedExp() * title.getParameters().get(AttriType.EXPERIENCE)[0]));
            }
        }
    }
}
