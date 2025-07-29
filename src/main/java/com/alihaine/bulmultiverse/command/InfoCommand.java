package com.alihaine.bulmultiverse.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.Message;
import com.alihaine.bulmultiverse.world.WorldData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias(BaseBmvCommand.commandRootAlias)
@CommandPermission("bulmultiverse.info")
public class InfoCommand extends BaseCommand {
    private WorldData getWorldData(String worldName, CommandSender sender) {
        WorldData worldData = BulMultiverse.getWorldDataManager().getWorldDataFromWorldName(worldName);
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
    @Description("Display infos about world")
    @Syntax("/bmv info")
    public void onInfo(Player player) {
        String worldName = player.getWorld().getName();
        WorldData worldData = getWorldData(worldName, player);
        if (worldData != null)
            displayWorldData(worldData, player);
    }

    @Subcommand("info|infos")
    @Description("Display infos about a specific world")
    @Syntax("/bmv info [world_name]")
    public void onInfoTarget(CommandSender sender, String worldName) {
        final WorldData worldData = getWorldData(worldName, sender);
        if (worldData != null)
            displayWorldData(worldData, sender);
    }
}
