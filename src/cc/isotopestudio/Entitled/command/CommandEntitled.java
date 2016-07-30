package cc.isotopestudio.Entitled.command;

import cc.isotopestudio.Entitled.type.Title;
import cc.isotopestudio.Entitled.util.S;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mars on 7/22/2016.
 * Copyright ISOTOPE Studio
 */
public class CommandEntitled implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Entitle")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(S.toPrefixRed("���ִ�е�����"));
                return true;
            }
            Player player = (Player) sender;
            if (!player.hasPermission("entitle.admin")) {
                player.sendMessage(S.toPrefixRed("��û��Ȩ��"));
                return true;
            }
            if (args.length < 1) {
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {

                player.sendMessage(S.toPrefixGreen("���سɹ�"));
                return true;
            }
            if (args[0].equalsIgnoreCase("get") && args.length > 1) {
                Title title = Title.titles.get(args[1]);
                if (title == null) {
                    player.sendMessage(S.toPrefixRed(args[1] + "������"));
                    return true;
                }
                player.getInventory().addItem(title.getItem());
                player.sendMessage(S.toPrefixGreen("���"));
                return true;
            }
            if (args[0].equalsIgnoreCase("list")) {
                player.sendMessage(S.toPrefixYellow("�ƺ��б�"));
                player.sendMessage(S.toPrefixGreen(Title.titles.toString()));
                return true;
            }
            return true;
        }
        return false;
    }
}