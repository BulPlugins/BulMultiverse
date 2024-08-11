package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.command.SubCommand;
import com.alihaine.bulmultiverse.command.subcommands.options.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;

public class Create implements SubCommand {

    HashMap<String, Option> options = new HashMap<>();

    public Create() {
        options.put("-e", new Environment());
    }

    @Override
    public void executor(CommandSender sender, List<String> args) {
        System.out.println(args);
        WorldCreator worldCreator = new WorldCreator(args.get(0));

        for (int i = 1; i < args.size() - 1; i++) {
            options.get(args.get(i)).optionExecutor(args.get(i+1), worldCreator);
        }

        worldCreator.createWorld();
    }
}
