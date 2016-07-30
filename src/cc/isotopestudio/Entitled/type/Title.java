package cc.isotopestudio.Entitled.type;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

/**
 * Created by Mars on 7/30/2016.
 * Copyright ISOTOPE Studio
 */
public class Title {

    public static final Map<String, Title> titles = new HashMap<>();

    private final String name;
    private final Set<AttriType> attriSet;
    private final Map<AttriType, double[]> parameters;
    private final ItemStack item;

    public Title(String name, Set<AttriType> attriSet, Map<AttriType, double[]> parameters, List<String> des) {
        this.name = name;
        this.attriSet = attriSet;
        this.parameters = parameters;
        item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        List<String> lore = new ArrayList<>(des);
        for (AttriType type : attriSet) {
            lore.add(type.getDescription(parameters.get(type)));
        }
        item.setItemMeta(meta);
    }

    public String getName() {
        return name;
    }

    public Set<AttriType> getAttriSet() {
        return attriSet;
    }

    public Map<AttriType, double[]> getParameters() {
        return parameters;
    }

    public ItemStack getItem() {
        return item;
    }
}
