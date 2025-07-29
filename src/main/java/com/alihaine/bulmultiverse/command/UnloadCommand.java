package com.alihaine.bulmultiverse.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.Message;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

@CommandAlias(BaseBmvCommand.commandRootAlias)
@CommandPermission("bulmultiverse.unload")
public class UnloadCommand extends BaseCommand {
    @Subcommand("unload")
    @Description("UnLoad existing world")
    @Syntax("/bmv unload [world_name]")
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

        BulMultiverse.getWorldsFile().removeWorldFromFile(world.getName());
        BulMultiverse.getWorldDataManager().removeWorldData(world.getName());
        Bukkit.unloadWorld(world, true);
        new Message("cmd_unload_success")
                .withPlaceHolder("name", targetWorld)
                .sendMessage(sender);
    }
}
