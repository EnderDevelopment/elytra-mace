package com.enderdevelopment.elytrachestplateswitch;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ElytraSwitchHandler {
    public static void register() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (player.isFallFlying() && hand == Hand.MAIN_HAND) {
                ItemStack mainHandItem = player.getMainHandStack();
                if (mainHandItem.getItem() == Items.MACE) {
                    switchArmor(player);
                }
            }
            return ActionResult.PASS;
        });
    }

    private static void switchArmor(PlayerEntity player) {
        ItemStack chestplate = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack elytra = player.getEquippedStack(EquipmentSlot.CHEST);

        if (elytra.getItem() == Items.ELYTRA) {
            // Switch to chestplate
            player.equipStack(EquipmentSlot.CHEST, chestplate);
            player.equipStack(EquipmentSlot.CHEST, elytra);
        } else if (chestplate.getItem() != Items.AIR) {
            // Switch to elytra
            player.equipStack(EquipmentSlot.CHEST, elytra);
            player.equipStack(EquipmentSlot.CHEST, chestplate);
        }
    }
}