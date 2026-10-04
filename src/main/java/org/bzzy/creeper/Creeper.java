package org.bzzy.creeper;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Creeper implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("creeperboom");
    @Override
    public void onInitialize() {
        ConfigManager.load();

        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (!(entity instanceof ServerPlayer player)) {
                return;
            }

            if (!isKilledByChargedCreeper(damageSource)) {
                return;
            }

            double chance = ConfigManager.get().dropChance;
            if (player.getRandom().nextDouble() > chance) {
                return;
            }

            dropPlayerHead(player);
        });
        LOGGER.info("CreeperBoom Mod has been initialized on the server!");
    }

    private static boolean isKilledByChargedCreeper(DamageSource damageSource) {
        Entity attacker = damageSource.getEntity();
        if (attacker instanceof net.minecraft.world.entity.monster.Creeper creeper && creeper.isPowered()) {
            return true;
        }

        Entity source = damageSource.getDirectEntity();
        return source instanceof net.minecraft.world.entity.monster.Creeper creeper && creeper.isPowered();
    }

    private static void dropPlayerHead(ServerPlayer player) {
        ServerLevel world = (ServerLevel) player.level();

        ItemStack head = new ItemStack(Items.PLAYER_HEAD);

        head.set(
                DataComponents.PROFILE,
                //? >=1.21.11 {
                /*ResolvableProfile.createResolved(player.getGameProfile())
                *///?} else {
                new ResolvableProfile(player.getGameProfile())
                //?}
        );

        ItemEntity itemEntity = new ItemEntity(
                world,
                player.getX(),
                player.getY(),
                player.getZ(),
                head
        );

        world.addFreshEntity(itemEntity);
    }
}
