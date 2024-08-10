package com.alihaine.bulmultiverse.command;

import com.alihaine.bulmultiverse.command.subcommands.Create;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;

public class BMV implements CommandExecutor {
    HashMap<String, SubCommand> subCommands = new HashMap<>();

    public BMV() {
        subCommands.put("create", new Create());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0)
            return true;
        if (sender instanceof Player)
            if (!sender.hasPermission("bulmultiverse.admin"))
                return true;

        subCommands.forEach((key, value) -> {
            if (key.equalsIgnoreCase(args[0]))
                value.executor(sender, Arrays.copyOfRange(args, 1, args.length));
        });
        return true;
    }
}
