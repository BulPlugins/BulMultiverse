package com.alihaine.bulmultiverse.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.Message;
import com.alihaine.bulmultiverse.world.WorldData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("bmv|bulmv|bulmultiverse")
public class InfoCommand extends BaseBmvCommand {

    private WorldData getWorldData(String worldName, CommandSender sender) {
        WorldData worldData = BulMultiverse.getBulMultiverse().getWorldDataManager().getWorldDataFromWorldName(worldName);
        if (worldData == null) {
            new Message("world_not_found").withPlaceHolder("name", worldName).sendMessage(sender);
            return null;
        }
        return worldData;
    }

    private void displayWorldData(WorldData worldData, CommandSender sender) {
        sender.sendMessage("§a" + worldData.getWorldName() + ":");
        worldData.getOptionsAndValue().forEach((key, value) -> {
            new Message("infos_pattern").
                    withPlaceHolder("name", key.getName()).
                    withPlaceHolder("value", value.toString()).
                    sendMessage(sender);
        });
    }

    @Subcommand("info|infos")
    public void onInfo(Player player) {
        String worldName = player.getWorld().getName();
        WorldData worldData = getWorldData(worldName, player);
        if (worldData != null)
            displayWorldData(worldData, player);
    }

    @Subcommand("info|infos")
    public void onInfoTarget(CommandSender sender, String worldName) {
        final WorldData worldData = getWorldData(worldName, sender);
        if (worldData != null)
            displayWorldData(worldData, sender);
    }
}
