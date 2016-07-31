package cc.isotopestudio.Entitled.util;

import org.bukkit.ChatColor;

import java.util.List;

/**
 * Created by Mars on 7/31/2016.
 * Copyright ISOTOPE Studio
 */
public class Util {

    public static List<String> convertColorCode(List<String> lore) {
        for (int i = 0; i < lore.size(); i++) {
            lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
        }
        return lore;
    }
}
