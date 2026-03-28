# 🧨 CreeperBoom

一款轻量化的 Fabric 模组。当玩家被高压苦力怕（Charged Creeper）炸死时，掉落其对应的玩家头颅。

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.21.4-blue?style=flat-square)
![Loader](https://img.shields.io/badge/Loader-Fabric-orange?style=flat-square)
[![Modrinth](https://img.shields.io/badge/Modrinth-Download-00AF5C?style=flat-square&logo=modrinth)](https://modrinth.com/mod/creeperboom)

---

## 📖 简介

在原版生存中，高压苦力怕可以使僵尸、骷髅等生物掉落头颅，但玩家却无法通过这种方式获得自己的头颅。

**CreeperBoom** 解决了这个问题：当玩家被高压苦力怕击杀时，会原地掉落一个带有该玩家皮肤纹理的头颅。

### 🌟 为什么写这个 Mod？
因为我翻遍了社区，也没找到一个实现“高压苦力怕炸掉头”这一件事的模组，所以只能自己写了。

---

## ⚙️ 配置说明

首次运行后，模组会在 `.minecraft/config/` 目录下生成配置文件 `creeper.json`。

```json
{
  "dropChance": 1.0
}
```

- **dropChance**: 掉落概率。
- **取值范围**: `0.1` - `1.0` (对应 10%-100%)。
- **默认值**: `1.0` (100% 掉落)。

---

## 📥 下载

推荐前往 [Modrinth](https://modrinth.com/mod/creeperboom) 获取正式版本。

## 🐞 反馈与联系

如果你在爆炸中发现了任何 Bug，欢迎联系我：
- **Email**: connect@baizhouzi.top
- **GitHub**: 直接在仓库提交 Issues。

## 🧠 预计更新计划
添加防止苦力怕爆炸破坏方块的功能
