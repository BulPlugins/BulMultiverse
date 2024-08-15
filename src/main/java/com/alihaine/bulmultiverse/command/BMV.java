package com.alihaine.bulmultiverse.command;

import com.alihaine.bulmultiverse.command.subcommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BMV implements CommandExecutor {
    HashMap<String, SubCommand> subCommands = new HashMap<>();

    public BMV() {
        subCommands.put("create", new Create());
        subCommands.put("load", new Load());
        subCommands.put("unload", new Unload());
        subCommands.put("tp", new Teleport());
        subCommands.put("list", new ListWorlds());
        subCommands.put("set", new Set());
        subCommands.put("flags", new Flags());
        subCommands.put("help", new Help());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (!sender.hasPermission("bulmultiverse.admin")) {
                sender.sendMessage("NO PERMISSION MSG");
                return true;
            }
        }

        if (args.length == 0) {
            subCommands.get("help").executor(sender, null);
            return true;
        }

        for (Map.Entry<String, SubCommand> entry : subCommands.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(args[0])) {
                entry.getValue().executor(sender, Arrays.asList(Arrays.copyOfRange(args, 1, args.length)));
                return true;
            }
        }
        subCommands.get("help").executor(sender, null);
        return true;
    }
}
