<img src="https://i.goopics.net/77bvma.png" width="256" alt="Logo" style="display: block; margin-left: auto; margin-right: auto; width: 25%">

**BulMultiverse** is an ultra-optimized, lightweight, world management _Spigot_ plugin for _Minecraft: Java Edition_.
It is compatible with a wide array of versions: from legacy Minecraft 1.8 to modern 1.20+.
Unlike _MultiVerse Core_, **BulMultiverse** is designed to be lean and efficient &mdash; without any unnecessary listeners.

This plugin does not, or ever will, contain event listeners for any reason.
[Check it out on SpigotMC](https://www.spigotmc.org/resources/118884/ "Go to SpigotMC").

<img src="https://img.shields.io/badge/Table_of_contents-50C878?style=for-the-badge" alt="Table of Contents" style="pointer-events: none;">

1. [Features](#features)
2. [Configuration file](#configuration-file)
3. [Commands and permissions](#commands-and-permissions)
4. [Flags](#flags)
5. [How to delete a world](#how-to-delete-a-world)
6. [Addons](#addons)
7. [Distribution](#distribution)

<img id="features" src="https://img.shields.io/badge/Features-50C878?style=for-the-badge" alt="Features" style="pointer-events: none;">

- Create world with customizable settings (e.g. seed, difficulty, etc.)
- Modify Existing World Settings (e.g, difficulty, PvP, etc.)
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
  help_pattern: "&e%usage% &8| &e%description%"
  flags_pattern: "&e%usage% &8| &e%description%"
  only_ingame_command: "&e[BULMultiverse] &cThis command can be executed only in-game."
  no_permission: "&e[BULMultiverse] &cYou don't have the permission to do that"
```

<img id="commands-and-permissions" src="https://img.shields.io/badge/Commands_and_permissions-50C878?style=for-the-badge" alt="Commands and Permissions" style="pointer-events: none;">

| Command                           | Description                                                          | Permission          |
|-----------------------------------|----------------------------------------------------------------------|---------------------|
| bmv create \[World Name\] (Flags) | Create a world with the given name (and optional flags.)             | bulmultiverse.admin |
| bmv load \[World Name\]           | Load an existing target world.                                       | bulmultiverse.admin |
| bmv unload \[World Name\]         | Unload an existing world. (This does not remove the world's folder.) | bulmultiverse.admin |
| bmv set \[World Name\] \[Flag\]   | Set the flags for a given world.                                     | bulmultiverse.admin |
| bmv tp \[World Name\]             | Teleport to the target world.                                        | bulmultiverse.admin |
| bmv list                          | List all the loaded worlds.                                          | bulmultiverse.admin |
| bmv help                          | Display the in-game help.                                            | bulmultiverse.admin |
| bmv flags                         | Display all the available flags.                                     | bulmultiverse.admin |

<img id="flags" src="https://img.shields.io/badge/Flags-50C878?style=for-the-badge" alt="Flags." style="pointer-events: none;">

| Command            | Description                                            | example                             |
|--------------------|--------------------------------------------------------|-------------------------------------|
| -s [Number]        | Specify the world generation seed.                     | /bmv create example -s 15648648949  |
| -b [true or false] | Enable default structures in the world (i.e., village) | /bmv create example -b false        |
| -e [Environment]   | Set the environment (e.g, nether)                      | /bmv create example -e the_end      |
| -p [true or false] | Enable \| Disable Player-vs-Player interactions.       | /bmv create example -p false        |
| -t [Type]          | Specify the world type (e.g, flat, amplified)          | /bmv create example -t large_biomes |
| -d [Difficulty]    | Set the world's difficulty (e.g, easy, hard)           | /bmv create example -d peaceful     |

Flags can be chained together. For example, to create a flat world, on peaceful difficulty, without PvP:
`/bmv create exemple -d peaceful -p false -t flat`

Missed a flag during creation? You can set it later using the set command: <br/>
`/bmv set exemple -d peaceful`

> **NOTE**: Some flags cannot be changed after the world is created. 
> 
> Be sure to check the console for errors when creating worlds.<br>
> If you make an error in the command, such as setting an invalid difficulty, the default value will be used instead.
> Example: `/bmv create example -d SUPERHARDCORP` would create an 'example' world, with the default difficulty.
 


<img id="how-to-delete-a-world" src="https://img.shields.io/badge/How_to_delete_a_world-50C878?style=for-the-badge" alt="How to delete a world." style="pointer-events: none;">

BulMultiverse does _**not**_ delete server files or folders directly.

To remove a world:
1. Stop your server.
2. Manually delete the world's folder.
3. Restart your server.

BulMultiverse will detect that the world folder is missing and automatically remove it from it's `worlds.yml` file.



<img id="addons" src="https://img.shields.io/badge/Addons-50C878?style=for-the-badge" alt="Addons" style="pointer-events: none;">

> ⚠️ **DO NOT RENAME THE ADDON JAR FILES! THE PLUGIN WILL NOT DETECT THEM.** ⚠️

So the default BulMultiverse.jar is very light and optimized, but what if you want an additional specific feature ?

To address this, I've created a robust addons system.
This means you can add a specific .jar file (for example, _PerWorldInventory.jar_)
to the 'addons' folder within the BulMultiverse directory, and you'll have a new feature: PerWorldInventory!

#### VoidWorld

The VoidWorld addon allows you to create a totally empty world.<br>
[Check it out on SpigotMC](https://www.spigotmc.org/resources/119020/ "Check it out on SpigotMC")

| Type    | value     | Description                     | example                     |
|---------|-----------|---------------------------------|-----------------------------|
| flag    | -c void   | Create a empty world (void)     | /bmv create example -c void |
| command | /setblock | Create a block at your position | /setblock                   |

#### PerWorldInventory

**_WORK IN PROGRESS_** <br>
To be notified [join our Discord server](https://discord.gg/wxnTV68dX2).

#### GuiWorldManager

**_WORK IN PROGRESS_** <br>
To be notified [join our Discord server](https://discord.gg/wxnTV68dX2).

#### Portal

**_WORK IN PROGRESS_** <br>
To be notified [join our Discord server](https://discord.gg/wxnTV68dX2).

<img id="distribution" src="https://img.shields.io/badge/Distribution-50C878?style=for-the-badge" alt="Distribution" style="pointer-events: none;">

This is a public plugin. You are free to use it and create a fork to develop your own version. However, you are not allowed to sell or distribute it in a private manner.