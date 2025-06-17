package com.alihaine.bulmultiverse.command;

import co.aikar.commands.annotation.*;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.addon.BulMultiverseAddon;
import org.bukkit.command.CommandSender;

@CommandAlias("bmv|bulmv|bulmultiverse")
@CommandPermission("test")
@Description("Display addons list or infos about a specified addon")
public class AddonsCommand extends BaseBmvCommand {
    @Subcommand("addon|addons")
    public void onAddonList(CommandSender sender) {
        sender.sendMessage("§eLoaded addons: ");
        for (BulMultiverseAddon addon : addonManager.getAddonsList())
            sender.sendMessage("§e" + addon.getName());

    }

    @Subcommand("addon|addons")
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
