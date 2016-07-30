package cc.isotopestudio.Entitled;

import cc.isotopestudio.Entitled.command.CommandEntitled;
import cc.isotopestudio.Entitled.listener.PlayerListener;
import cc.isotopestudio.Entitled.task.TitlesEffectTask;
import cc.isotopestudio.Entitled.task.UpdateTitles;
import cc.isotopestudio.Entitled.util.PluginFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Entitled extends JavaPlugin {

    private static final String pluginName = "Entitled";
    public static final String prefix = (new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD).append("[")
            .append("称号").append("]").append(ChatColor.RED).toString();

    public static Entitled plugin;

    public static PluginFile entitleFile;

    @Override
    public void onEnable() {
        plugin = this;

        entitleFile = new PluginFile(this, "entitle.yml", "entitle.yml");

        this.getCommand("Entitled").setExecutor(new CommandEntitled());

        new UpdateTitles().runTaskLater(this, 1);
        new TitlesEffectTask().runTaskTimer(this, 2, 3 * 20);

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);


        getLogger().info(pluginName + "成功加载!");
        getLogger().info(pluginName + "由ISOTOPE Studio制作!");
        getLogger().info("http://isotopestudio.cc");
    }

    @Override
    public void onDisable() {
        getLogger().info(pluginName + "成功卸载!");
    }

}
