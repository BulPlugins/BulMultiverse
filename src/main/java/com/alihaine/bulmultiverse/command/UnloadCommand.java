package com.alihaine.bulmultiverse.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.alihaine.bulmultiverse.file.Message;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

@CommandAlias("bmv|bulmv|bulmultiverse")
@Description("UnLoad existing world")
public class UnloadCommand extends BaseBmvCommand {
    @Subcommand("unload")
    public void onUnload(CommandSender sender, String targetWorld) {
        World world = Bukkit.getWorld(targetWorld);
        if (world == null) {
            new Message("world_not_found")
                    .withPlaceHolder("name", targetWorld)
                    .sendMessage(sender);
            return;
        }

        if (!world.getPlayers().isEmpty()) {
            sender.sendMessage("§cYou can't unload a world who contain players");
            return;
        }

        worldsFile.removeWorldFromFile(world.getName());
        worldDataManager.removeWorldData(world.getName());
        Bukkit.unloadWorld(world, true);
        new Message("cmd_unload_success")
                .withPlaceHolder("name", targetWorld)
                .sendMessage(sender);
    }
}
