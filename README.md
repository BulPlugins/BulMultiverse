<p align="center">
    <img src="https://i.goopics.net/77bvma.png" width="256">
</p>

![BulMultiverse](https://img.shields.io/badge/BulMultiverse-50C878?style=for-the-badge)

BulMultiverse is an ultra-optimized lightweight world management plugin. Compatible with version 1.8 to the Latest Minecrat version. Unlike the default Multiverse-Core plugin, BulMultiverse is designed to be lean and efficient, without any unnecessary listeners.. This plugin don't contain and will never contain any listeners for any reason.
[Download page](https://www.spigotmc.org/resources/118884/ "Click to download")

![Table of contents](https://img.shields.io/badge/Table_of_contents-50C878?style=for-the-badge)

1. [Features](#features)
1. [Configuration file](#configuration-file)
2. [Commands and permissions](#commands-and-permissions)
3. [Flags](#flags)
4. [How to delete a world](#how-to-delete-a-world)
5. [Addons](#addons)
6. [Distribution](#distribution)

<p id="features"></p>

![Features](https://img.shields.io/badge/Features-50C878?style=for-the-badge)


- Create world with customizable settings (e.g seed, difficulty, etc).
- Modify Existing World Settings (e.g, difficulty, PvP, etc).
- Teleport between world.
- Load existing world.
- List loaded worlds.
- Disable invalid world names (e.g, "plugins").

<p id="configuration-file"></p>

![Configuration file](https://img.shields.io/badge/Configuration_file-50C878?style=for-the-badge)

```
//Disable invalid world names
world_disable_name: [plugins, bStats, PluginMetrics]

messages:   
  no_permission: "You don't have the permission to do that"
```
<p id="commands-and-permissions"></p>

![Configuration file](https://img.shields.io/badge/Commands_and_permissions-50C878?style=for-the-badge)


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

<p id="flags"></p>

![Flags](https://img.shields.io/badge/Flags-50C878?style=for-the-badge)


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

<p id="how-to-delete-a-world"></p>

![Delete world](https://img.shields.io/badge/How_to_delete_a_world-50C878?style=for-the-badge)

BulMultiverse does not delete server files or folders directly. To remove a world:
1. Stop your server.
2. Manually delete the world's folder.
3. Restart your server.

BulMultiverse will detect that the world folder is missing and automatically remove it from its worlds.yml file.

<p id="addons"></p>

![Addons](https://img.shields.io/badge/Addons-50C878?style=for-the-badge)

> /!\ DO NOT RENAME THE ADDONS JAR FILE, OR THE PLUGIN WILL NOT DETECT THEM

So the default BulMultiverse.jar is very light and optimized, but what if you want an additional specific feature ?

To address this, I've created a robust addons system. This means you can add a specific .jar file (for example, PerWorldInventory.jar)
to the 'addons' folder within the BulMultiverse directory, and you'll have a new feature: PerWorldInventory!

#### VoidWorld

This addon allow you to create a totally empty world. [Download page](https://www.spigotmc.org/resources/118884/ "Click to download")

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

<p id="distribution"></p>

![Distribution](https://img.shields.io/badge/Distribution-50C878?style=for-the-badge)

This is a public plugin. You are free to use it and create a fork to develop your own version. However you are not allowed to sell or distribute it in a private manner.