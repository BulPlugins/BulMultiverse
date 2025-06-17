package com.alihaine.bulmultiverse.command;

import co.aikar.commands.annotation.*;
import com.alihaine.bulmultiverse.file.Message;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias(BaseBmvCommand.commandRootAlias)
@CommandPermission("bulmultiverse.teleport")
public class TeleportCommand extends BaseBmvCommand {
    private World getWorld(CommandSender sender, String worldName) {
        final World world = Bukkit.getWorld(worldName);
        if (world == null) {
            new Message("world_not_found")
                    .withPlaceHolder("name", worldName)
                    .sendMessage(sender);
            return null;
        }
        return world;
    }

    private void teleportPlayer(Player player, World world) {
        player.teleport(world.getSpawnLocation());
        new Message("cmd_teleport")
                .withPlaceHolder("name", world.getName())
                .sendMessage(player);
    }

    @Subcommand("tp|teleport")
    @Description("Teleport to the target world")
    @Syntax("/bmv tp [world_name]")
    public void onTeleport(Player player, String worldName) {
        final World world = getWorld(player, worldName);
        if (world == null)
            return;
        teleportPlayer(player, world);
    }

    @Subcommand("tp|teleport")
    @Description("Teleport a player to the target world")
    @Syntax("/bmv tp [world_name] [player]")
    public void onTeleportTarget(CommandSender sender, String worldName, String playerName) {
        final World world = getWorld(sender, worldName);
        if (world == null)
            return;
        final Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            new Message("no_player_target").sendMessage(sender);
            return;
        }
        teleportPlayer(player, world);
        new Message("cmd_teleport_success").withPlaceHolder("player", player.getName())
                .withPlaceHolder("name", world.getName())
                .sendMessage(sender);
    }
}
