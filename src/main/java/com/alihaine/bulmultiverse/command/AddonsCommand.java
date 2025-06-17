package com.alihaine.bulmultiverse.command;

import co.aikar.commands.annotation.*;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.addon.BulMultiverseAddon;
import org.bukkit.command.CommandSender;

@CommandAlias(BaseBmvCommand.commandRootAlias)
@CommandPermission("bulmultiverse.addon")
public class AddonsCommand extends BaseBmvCommand {
    @Subcommand("addon|addons")
    @Description("Display loaded addons")
    @Syntax("/bmv addon")
    public void onAddonList(CommandSender sender) {
        sender.sendMessage("§eLoaded addons: ");
        for (BulMultiverseAddon addon : addonManager.getAddonsList())
            sender.sendMessage("§e" + addon.getName());

    }

    @Subcommand("addon|addons")
    @Description("Display infos about a specified addon")
    @Syntax("/bmv addon [addon_name]")
    public void onAddonTarget(CommandSender sender, String addonName) {
        BulMultiverseAddon addon = BulMultiverse.getBulMultiverse().getAddonManager().getAddonFromName(addonName);
        if (addon == null) {
            sender.sendMessage("Addon with name: " + addonName + " not found");
            return;
        }
        sender.sendMessage("§eAddon name: " + addon.getName());
        sender.sendMessage("§eAuthors : " + addon.getAuthors());
        sender.sendMessage("§eDownload links: ");
        for (String link: addon.getDownloadLinks())
            sender.sendMessage("§6" + link);
        sender.sendMessage("§aTo get help and support about this addon: §e" + addon.getSupportLink());
    }
}
