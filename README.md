<p align="center">
    <img src="https://i.goopics.net/77bvma.png" width="256">
</p>

BulMultiverse is an ultra-optimized lightweight world management plugin. Compatible with version 1.8 to the Latest Minecrat version. Unlike the default Multiverse-Core plugin, BulMultiverse is designed to be lean and efficient, without any unnecessary listeners.. This plugin don't contain and will never contain any listeners for any reason.
[Download page](https://www.spigotmc.org/resources/118884/ "Click to download")

<img src="https://img.shields.io/badge/Table_of_contents-50C878?style=for-the-badge" alt="Configuration file" style="pointer-events: none;">

1. [Features](#features)
1. [Configuration file](#configuration-file)
2. [Commands and permissions](#commands-and-permissions)
3. [Flags](#flags)
4. [How to delete a world](#how-to-delete-a-world)
5. [Addons](#addons)
6. [Distribution](#distribution)

<img id="features" src="https://img.shields.io/badge/Features-50C878?style=for-the-badge" alt="Configuration file" style="pointer-events: none;">

- Create world with customizable settings (e.g seed, difficulty, etc).
- Modify Existing World Settings (e.g, difficulty, PvP, etc).
- Teleport between world.
- Load existing world.
- List loaded worlds.
- Disable invalid world names (e.g, "plugins").

<img id="configuration-file" src="https://img.shields.io/badge/Configuration_file-50C878?style=for-the-badge" alt="Configuration file" style="pointer-events: none;">

```
//Disable invalid world names
world_disable_name: [plugins, bStats, PluginMetrics]

messages:
  no_world_target: "&e[BULMultiverse] &cYou didn't target any world or world name. &e/bmv help"
  world_not_found: "&e[BULMultiverse] &cThe world &e%name% is not found. &e/bmv list"
  flag_not_found: "&e[BULMultiverse] &cThe flag %name% don't exist. &e/bmv flags"
  forbidden_world_name: "&e[BULMultiverse] &cYou can't create a world with this name, check your config.yml."
  cmd_load_success: "&e[BULMultiverse] &aworld: &2%name% &aloaded."
  cmd_teleport_success: "&e[BULMultiverse] &aYou are teleported to the world: &2%name%."
  cmd_unload_success: "&e[BULMultiverse] &aThe world: &2%name% is unload."
  error_set_option: "&e[BULMultiverse] &cImpossible to set this option."
  error_world_creator: "&e[BULMultiverse] &cThis option does not support WorldCreator."
  only_ingame_command: "&e[BULMultiverse] &cThis command can be executed only in-game."
  no_permission: "&e[BULMultiverse] &cYou don't have the permission to do that"
```

<img id="commands-and-permissions" src="https://img.shields.io/badge/Commands_and_permissions-50C878?style=for-the-badge" alt="Configuration file" style="pointer-events: none;">

| Command                         | Description                                                       | Permission          |
|---------------------------------|-------------------------------------------------------------------|---------------------|
| bmv create [World Name] (Flags) | Create a world with the given name and optionals flags            | bulmultiverse.admin |
| bmv load [World Name]           | Load the target existing world                                    | bulmultiverse.admin |
| bmv unload [World Name]         | UnLoad the target existing world (This doesn't remove the folder) | bulmultiverse.admin |
| bmv set [World Name] [Flag]     | Set the flag for the target world                                 | bulmultiverse.admin |
| bmv tp [World Name]             | Teleport to the target world                                      | bulmultiverse.admin |
| bmv list                        | List all the loaded worlds                                        | bulmultiverse.admin |
| bmv help                        | Display the in-game help                                          | bulmultiverse.admin |
| bmv flags                       | Display all the availables flag                                   | bulmultiverse.admin |

<img id="flags" src="https://img.shields.io/badge/Flags-50C878?style=for-the-badge" alt="Configuration file" style="pointer-events: none;">

| Command            | Description                                           | example                             |
|--------------------|-------------------------------------------------------|-------------------------------------|
| -s [Number]        | Create a world with the given seed                    | /bmv create exemple -s 15648648949  |
| -b [true or false] | Enable the default builds in the world (e.g, village) | /bmv create exemple -b false        |
| -e [Environment]   | Set the environment (e.g, nether)                     | /bmv create exemple -e the_end      |
| -p [true or false] | Enable the pvp                                        | /bmv create exemple -p false        |
| -t [Type]          | Set type (e.g, flat, amplified)                       | /bmv create exemple -t large_biomes |
| -d [Difficulty]    | Set difficulty (e.g, easy, hard)                      | /bmv create exemple -d peaceful     |

You can chain flags together, for example:
`/bmv create exemple -d peaceful -p false -t flat`

Missed a flag during creation? You can set it later using the set command:
`/bmv set exemple -d peaceful`
> NOTE
> Some flags like the seed, cannot be changed after the world is created. If you make an error in the command, such as setting an invalid difficulty:
'/bmv create exemple -d SUPERHARDCORP'
the default difficulty will be used instead. Be sure to check the console for errors when creating worlds.

<img id="how-to-delete-a-world" src="https://img.shields.io/badge/How_to_delete_a_world-50C878?style=for-the-badge" alt="Configuration file" style="pointer-events: none;">

BulMultiverse does not delete server files or folders directly. To remove a world:
1. Stop your server.
2. Manually delete the world's folder.
3. Restart your server.

BulMultiverse will detect that the world folder is missing and automatically remove it from its worlds.yml file.

<img id="addons" src="https://img.shields.io/badge/Addons-50C878?style=for-the-badge" alt="Configuration file" style="pointer-events: none;">

> /!\ DO NOT RENAME THE ADDONS JAR FILE, OR THE PLUGIN WILL NOT DETECT THEM

So the default BulMultiverse.jar is very light and optimized, but what if you want an additional specific feature ?

To address this, I've created a robust addons system. This means you can add a specific .jar file (for example, PerWorldInventory.jar)
to the 'addons' folder within the BulMultiverse directory, and you'll have a new feature: PerWorldInventory!

#### VoidWorld

This addon allow you to create a totally empty world. [Download page](https://www.spigotmc.org/resources/119020/ "Click to download")

| Type    | value     | Description                     | example                     |
|---------|-----------|---------------------------------|-----------------------------|
| flag    | -c void   | Create a empty world (void)     | /bmv create exemple -c void |
| command | /setblock | Create a block at your position | /setblock                   |

#### PerWorldInventory

WORK IN PROGRESS. To be notified join the discord https://discord.gg/wxnTV68dX2

#### GuiWorldManager

WORK IN PROGRESS. To be notified join the discord https://discord.gg/wxnTV68dX2

#### Portal

WORK IN PROGRESS. To be notified join the discord https://discord.gg/wxnTV68dX2

<img id="distribution" src="https://img.shields.io/badge/Distribution-50C878?style=for-the-badge" alt="Configuration file" style="pointer-events: none;">

This is a public plugin. You are free to use it and create a fork to develop your own version. However you are not allowed to sell or distribute it in a private manner.