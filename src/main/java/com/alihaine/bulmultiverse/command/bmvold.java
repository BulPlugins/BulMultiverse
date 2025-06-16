package com.alihaine.bulmultiverse.command;

import com.alihaine.bulmultiverse.command.subcommands.*;
import com.alihaine.bulmultiverse.file.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class bmvold implements CommandExecutor {
    private final HashMap<String, SubCommand> subCommands = new HashMap<>();

    public void loadDefaultCommands() {
        subCommands.put("flags", new Flags());
        subCommands.put("help", new Help());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            subCommands.get("help").executor(sender, null);
            return true;
        }

        SubCommand cmdToExec = subCommands.get(args[0]);
        if (cmdToExec == null) {
            subCommands.get("help").executor(sender, null);
            return true;
        }

        if (!sender.hasPermission("bulmultiverse.admin") && !sender.hasPermission("bulmultiverse." + args[0].toLowerCase())) {
            new Message("no_permission").sendMessage(sender);
            return true;
        }

        for (Map.Entry<String, SubCommand> entry : subCommands.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(args[0])) {
                if (sender instanceof Player) {
                    if (!sender.hasPermission("bulmultiverse.admin") && !sender.hasPermission("bulmultiverse." + args[0].toLowerCase())) {
                        new Message("no_permission").sendMessage(sender);
                        return true;
                    }
                }
                entry.getValue().executor(sender, Arrays.asList(Arrays.copyOfRange(args, 1, args.length)));
                return true;
            }
        }
        subCommands.get("help").executor(sender, null);
        return true;
    }

    public HashMap<String, SubCommand> getSubCommandsHashMap() {
        return this.subCommands;
    }


    /**
     * Adds a subcommand to the collection of subcommands.
     *
     * @param command the name of the command to be added (and also the identifier to execute the associated subcommand). It must NOT BE NULL.
     * @param subCommand the subcommand object to associate with the command. It must NOT BE NULL.
     */
    public void addCommand(String command, SubCommand subCommand) {
        this.subCommands.put(command, subCommand);
    }
}
