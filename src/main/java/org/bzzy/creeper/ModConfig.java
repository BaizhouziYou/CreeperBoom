package org.bzzy.creeper;

public class ModConfig {
    /**
     * 掉落概率范围 0.0 ~ 1.0
     * 1.0 = 100%
     * 0.5 = 50%
     */
    public double dropChance = 1.0;

    /**
     * 是否防止苦力怕爆炸破坏方块 (默认开启)
     * true = 爆炸不破坏方块 (保留对玩家的伤害)
     * false = 恢复原版行为
     */
    public boolean preventBlockDamage = true;
}