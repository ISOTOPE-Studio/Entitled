package cc.isotopestudio.Entitled.type;

import cc.isotopestudio.Entitled.util.S;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by Mars on 7/30/2016.
 * Copyright ISOTOPE Studio
 */
public class Title {

    public static final Map<String, Title> titles = new HashMap<>();
    private static final String namePrefix = S.toRed("称号: ");

    private final String name;
    private final Set<AttriType> attriSet;
    private final Map<AttriType, double[]> parameters;
    private final ItemStack item;

    public Title(String name, @NotNull Set<AttriType> attriSet,
                 @NotNull Map<AttriType, double[]> parameters, @NotNull List<String> des) {
        this.name = name;
        this.attriSet = attriSet;
        this.parameters = parameters;
        item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(namePrefix + getName());
        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        List<String> lore = new ArrayList<>(des);
        lore.add(S.toGray("----------------"));
        for (AttriType type : attriSet) {
            lore.add(type.getDescription(parameters.get(type)));
        }
        lore.add(S.toGray("----------------"));
        lore.add(S.toItalicYellow("<放在物品栏右下角来使用>"));
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public String getName() {
        return S.toBoldPurple(name);
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Title{");
        sb.append("name='").append(name).append('\'');
        sb.append(", attriSet=").append(attriSet);
        sb.append(", parameters=").append(parameters);
        sb.append(", item=").append(item);
        sb.append('}');
        return sb.toString();
    }

    public static Title getTitleFromPlayer(Player player) {
        try {
            ItemStack item = player.getInventory().getItem(8);
            String name = item.getItemMeta().getDisplayName().replace(namePrefix, "");
            return titles.get(ChatColor.stripColor(name));
        } catch (Exception e) {
            return null;
        }
    }
}
