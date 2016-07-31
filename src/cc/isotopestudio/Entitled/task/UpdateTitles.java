package cc.isotopestudio.Entitled.task;

import cc.isotopestudio.Entitled.type.AttriType;
import cc.isotopestudio.Entitled.type.Title;
import cc.isotopestudio.Entitled.util.Util;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static cc.isotopestudio.Entitled.Entitled.entitleFile;
import static cc.isotopestudio.Entitled.Entitled.plugin;
import static cc.isotopestudio.Entitled.type.Title.titles;

/**
 * Created by Mars on 7/30/2016.
 * Copyright ISOTOPE Studio
 */
public class UpdateTitles extends BukkitRunnable {

    @Override
    public void run() {
        titles.clear();
        for (String name : entitleFile.getKeys(false)) {
            ConfigurationSection section = entitleFile.getConfigurationSection(name);
            List<String> lore = Util.convertColorCode(section.getStringList("lore"));
            Set<AttriType> attriSet = new HashSet<>();
            Map<AttriType, double[]> parameters = new HashMap<>();

            line:
            for (String line : section.getStringList("attri")) {
                String[] part = line.split(" ");
                if (part.length < 2) {
                    plugin.getLogger().warning(line + " ´íÎó");
                    continue;
                }
                AttriType type;
                try {
                    type = AttriType.valueOf(part[0]);
                } catch (IllegalArgumentException e) {
                    plugin.getLogger().warning(line + " ´íÎó");
                    continue;
                }
                double[] numbers = new double[part.length - 1];
                for (int i = 1; i < part.length; i++) {
                    try {
                        numbers[i - 1] = Double.parseDouble(part[i]);
                    } catch (NumberFormatException e) {
                        plugin.getLogger().warning(line + " ´íÎó");
                        continue line;
                    }
                }
                if (type == AttriType.CRITICAL) {
                    if (part.length != 3) {
                        plugin.getLogger().warning(line + " ´íÎó");
                        continue;
                    }
                }
                attriSet.add(type);
                parameters.put(type, numbers);
            }

            titles.put(name, new Title(name, attriSet, parameters, lore));
        }

        System.out.println(titles);

    }
}
