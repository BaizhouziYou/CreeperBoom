package org.bzzy.creeper;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Creeper implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("creeperboom");
    @Override
    public void onInitialize() {
        ConfigManager.load();

        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (!(entity instanceof ServerPlayerEntity player)) {
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
        Entity attacker = damageSource.getAttacker();
        if (attacker instanceof CreeperEntity creeper && creeper.isCharged()) {
            return true;
        }

        Entity source = damageSource.getSource();
        return source instanceof CreeperEntity creeper && creeper.isCharged();
    }

    private static void dropPlayerHead(ServerPlayerEntity player) {
        if (!(player.getWorld() instanceof ServerWorld world)) {
            return;
        }

        ItemStack head = new ItemStack(Items.PLAYER_HEAD);

        head.set(
                DataComponentTypes.PROFILE,
                new ProfileComponent(player.getGameProfile())
        );

        ItemEntity itemEntity = new ItemEntity(
                world,
                player.getX(),
                player.getY(),
                player.getZ(),
                head
        );

        world.spawnEntity(itemEntity);
    }
}