# BulMultiverse

BulMultiverse is an ultra-optimized lightweight world management plugin. Compatible with version 1.8 to the Latest Minecrat version. Unlike the default Multiverse-Core plugin, BulMultiverse is designed to be lean and efficient, without any unnecessary listeners.. This plugin don't contain and will never contain any listeners for any reason.
[Download page](https://www.spigotmc.org/resources/118884/ "Click to download")
## Features
- Create world with customizable settings (e.g seed, difficulty, etc).
- Modify Existing World Settings (e.g, difficulty, PvP, etc).
- Teleport between world.
- Load existing world.
- List loaded worlds.
- Disable invalid world names (e.g, "plugins").

## Configuration file
```
//Disable invalid world names
world_disable_name: [plugins, bStats, PluginMetrics]

messages:   
  no_permission: "You don't have the permission to do that"
```
## Commands and permissions

| Command        | Description                                                         | Permission |
|----------------|---------------------------------------------------------------------| ------|
| bmv create <World Name> (Flags) | Create a world with the given name and optionals flags | bulmultiverse.admin
| bmv load <World Name> | Load the target existing world | bulmultiverse.admin
| bmv set <World Name> <Flag> | Set the flag for the target world | bulmultiverse.admin
| bmv tp <World Name> | Teleport to the target world | bulmultiverse.admin
| bmv list | List all the loaded worlds | bulmultiverse.admin
| bmv help | Display the in-game help | bulmultiverse.admin
| bmv flags | Display all the availables flag | bulmultiverse.admin

## Flags

| Command        | Description                                                         | example
|----------------|---------------------------------------------------------------------|------|
| -s <Number> | Create a world with the given seed | /bmv create exemple -s 15648648949 |
| -b <true or false> | Enable the default builds in the world (e.g, village) | /bmv create exemple -b false |
| -e <Environment> | Set the environment (e.g, nether) | /bmv create exemple -e the_end |
| -p <true or false> | Enable the pvp | /bmv create exemple -p false |
| -t <Type> | Set type (e.g, flat, amplified) | /bmv create exemple -t large_biomes |
| -d <Difficulty> | Set difficulty (e.g, easy, hard) | /bmv create exemple -d peaceful |

You can chain flags together, for example:
`/bmv create exemple -d peaceful -p false -t flat`

Mised a flag ruing creation? You can set it later using the set command:
`/bmv set exemple -d peaceful`
> NOTE
> Some flags like the seed, cannot be changed after the world is created. If you make an error in the command, such as setting an invalid difficulty:
'/bmv create exemple -d SUPERHARDCORP'
the default difficulty will be used instead. Be sure to check the console for errors when creating worlds.

## How to delete a world

BulMultiverse does not delete server files or folders directly. To remove a world:
1. Stop your server.
2. Manually delete the world's folder.
3. Restart your server.

BulMultiverse will detect that the world folder is missing and automatically remove it from its worlds.yml file.

## Distribution

This is a public plugin. You are free to use it and create a fork to develop your own version. However you are not allowed to sell or distribute it in a private manner.