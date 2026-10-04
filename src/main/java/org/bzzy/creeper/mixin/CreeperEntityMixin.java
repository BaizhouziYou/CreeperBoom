package org.bzzy.creeper.mixin;

import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import org.bzzy.creeper.ConfigManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Creeper.class)
public class CreeperEntityMixin {

    @ModifyArg(
            method = "explodeCreeper",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;explode(Lnet/minecraft/world/entity/Entity;DDDFLnet/minecraft/world/level/Level$ExplosionInteraction;)V"
            ),
            index = 5
    )
    private Level.ExplosionInteraction modifyExplosionType(Level.ExplosionInteraction originalType) {
        if (ConfigManager.get().preventBlockDamage) {
            return Level.ExplosionInteraction.NONE;
        }
        return originalType;
    }
}
