package cc.isotopestudio.Entitled;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Entitled extends JavaPlugin {

    private static final String pluginName = "Entitled";
    static final String prefix = (new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD).append("[")
            .append("系统").append("]").append(ChatColor.RED).toString();

    @Override
    public void onEnable() {

        getLogger().info(pluginName + "成功加载!");
        getLogger().info(pluginName + "由ISOTOPE Studio制作!");
        getLogger().info("http://isotopestudio.cc");
    }

    @Override
    public void onDisable() {
        getLogger().info(pluginName + "成功卸载!");
    }

}
