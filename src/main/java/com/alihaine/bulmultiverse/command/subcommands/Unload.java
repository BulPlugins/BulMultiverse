package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.command.SubCommand;
import com.alihaine.bulmultiverse.file.Message;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Unload implements SubCommand {
    @Override
    public void executor(CommandSender sender, List<String> args) {
        if (args.isEmpty()) {
            new Message("no_world_target").sendMessage(sender);
            return;
        }

        World world = Bukkit.getWorld(args.get(0));
        if (world == null) {
            new Message("world_not_found").withPlaceHolder("name", args.get(0)).sendMessage(sender);
            return;
        }

        if (!world.getPlayers().isEmpty()) {
            sender.sendMessage("§cYou can't unload a world who contain players");
            return;
        }

        BulMultiverse.getWorldsFileInstance().removeWorldFromFile(world.getName());
        Bukkit.unloadWorld(world, true);
        new Message("cmd_unload_success").withPlaceHolder("name", args.get(0)).sendMessage(sender);
    }

    @Override
    public String getUsage() {
        return "/bmv unload <world_name>";
    }

    @Override
    public String getDescription() {
        return "UnLoad existing world";
    }
}
