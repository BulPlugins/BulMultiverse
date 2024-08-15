package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.command.SubCommand;
import com.alihaine.bulmultiverse.file.WorldsFile;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Unload implements SubCommand {
    private final WorldsFile worldsFile = BulMultiverse.getWorldsFileInstance();

    @Override
    public void executor(CommandSender sender, List<String> args) {
        if (args.isEmpty()) {
            sender.sendMessage("§cPlease specify a world. §e/bmv help");
            return;
        }

        World world = Bukkit.getWorld(args.get(0));
        if (world == null) {
            sender.sendMessage("§cThe world §e" + args.get(0) + " §cis not found. §e/bmv help");
            return;
        }

        if (!world.getPlayers().isEmpty()) {
            sender.sendMessage("§cYou can't unload a world who contain players");
            return;
        }

        worldsFile.removeWorldFromFile(world.getName());
        Bukkit.unloadWorld(world, true);
        sender.sendMessage("§aThe world " + args.get(0) + " is unloaded");
    }
}
