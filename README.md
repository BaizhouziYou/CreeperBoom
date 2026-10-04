**English** | [简体中文](./README-zh_CN.md)
# 🧨 CreeperBoom
![logo](icon.png)

A lightweight Fabric mod that allows players to drop their own heads when killed by a Charged Creeper.
(Now it can control the behavior of Creepers after exploding)

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.21.x%20%7C%2026.x-blue?style=flat-square)
![Loader](https://img.shields.io/badge/Loader-Fabric-orange?style=flat-square)
[![Modrinth](https://img.shields.io/badge/Modrinth-Download-00AF5C?style=flat-square&logo=modrinth)](https://modrinth.com/mod/creeperboom)

---

## 📖 Description

In vanilla survival, Charged Creepers can cause zombies, skeletons, and other mobs to drop their heads, but players are excluded from this mechanic.

**CreeperBoom** fixes this: when a player is killed by a Charged Creeper, a player head with their unique skin texture will drop at the location.

### 🌟 Why did I create this Mod?
I searched through the community and couldn't find a mod that *only* implements "dropping player heads via Charged Creepers" and "prevent Creeper explosions from destroying blocks" without adding extra bloat, so I decided to write it myself.

---

## ⚙️ Configuration

After the first run, the mod will generate a configuration file named `creeper.json` in the `.minecraft/config/` directory.

```json
{
  "dropChance": 1.0,
  "preventBlockDamage": true
}
```

- **dropChance**: The probability of the head dropping.
  - **Range**: `0.1` - `1.0` (corresponds to 10% - 100%).
  - **Default**: `1.0` (100% drop rate).
- **preventBlockDamage**: Prevent Creeper explosions from destroying blocks.
  - **Range**: `true` or `fasle`.
  - **Default**: `true` .

---

## Building

Run Gradle with JDK 25; also install JDK 21 for the 1.21.x toolchain.
The build follows EasyBotMod's Stonecutter layout: shared
`src/`, a build script per loader, and dependencies per target in
`versions/<minecraft>-<loader>/gradle.properties`.

| Fabric target | Java | Build |
| --- | --- | --- |
| 1.21.3, 1.21.4, 1.21.8, 1.21.11 | 21 | Mojang mappings + remapped JAR |
| 26.1.2, 26.2, 26.3 | 25 | Unobfuscated JAR |

Only the listed versions are targeted, not every intermediate release.

```sh
# Build all configured targets and collect release/source jars in build/libs/
./gradlew buildAndCollect
# Build or run a specific target
./gradlew :1.21.4-fabric:build
./gradlew :1.21.4-fabric:runServer
# Switch the shared source/IDE target
./gradlew "Set active project to 1.21.4-fabric"
```

On Windows, use `./gradlew.bat`. Output names include Minecraft version and
loader, for example `CreeperBoom-1.1+mc1.21.4-fabric.jar`.

To add a target, register it in `settings.gradle.kts`, copy the baseline target's
properties and set its Minecraft, Fabric API and Java versions (Loader is shared
in the root properties and can be overridden per target).
Use Stonecutter conditionals in shared sources for API differences, then build
and test that target. In particular, check player-head data (NBT/components) and
the creeper explosion Mixin's invocation descriptor. Keep the committed active
target at `1.21.4-fabric`, matching `vcsVersion`.

## 📥 Download

It is recommended to get the official release from [Modrinth](https://modrinth.com/mod/creeperboom).

## 🐞 Feedback & Contact

If you find any bugs during the explosion, feel free to contact me:
- **Email**: connect@baizhouzi.top
- **GitHub**: Submit an Issue directly on the repository.

## 🧠 Planned Updates
~~- Add a feature to prevent Creepers from destroying blocks upon explosion.~~(Completed in v1.1)
