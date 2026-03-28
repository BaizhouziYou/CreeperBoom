package org.bzzy.creeper.mixin;

import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import org.bzzy.creeper.ConfigManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin {

    @ModifyArg(
            method = "explode",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/world/ServerWorld;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/World$ExplosionSourceType;)V"
            ),
            index = 5
    )
    private World.ExplosionSourceType modifyExplosionType(World.ExplosionSourceType originalType) {
        if (ConfigManager.get().preventBlockDamage) {
            return World.ExplosionSourceType.NONE;
        }
        return originalType;
    }
}
