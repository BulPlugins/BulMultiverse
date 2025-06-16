package com.alihaine.bulmultiverse.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.addon.BulMultiverseAddon;
import org.bukkit.command.CommandSender;

import java.util.List;

@CommandAlias("bmv|bulmv|bulmultiverse")
public class AddonsCommand extends BaseBmvCommand {

    private final List<BulMultiverseAddon> addonList = BulMultiverse.getBulMultiverse().getAddonManager().getAddonsList();

    @Subcommand("addon|addons")
    public void onAddonList(CommandSender sender) {
        sender.sendMessage("§eLoaded addons: ");
        for (BulMultiverseAddon addon : addonList)
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

    /*
    @Override
    public String getUsage() {
        return "/bmv addons (addon_name)";
    }

    @Override
    public String getDescription() {
        return "Display addons list or infos about a specified addon";
    }*/
}
