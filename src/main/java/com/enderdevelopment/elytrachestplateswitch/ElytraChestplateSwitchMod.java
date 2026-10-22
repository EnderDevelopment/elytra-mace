package com.enderdevelopment.elytrachestplateswitch;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.lwjgl.glfw.GLFW;

public class ElytraChestplateSwitchMod implements ModInitializer {
    private static KeyBinding switchKeyBinding;

    @Override
    public void onInitialize() {
        switchKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.elytrachestplateswitch.switch",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.elytrachestplateswitch.main"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (switchKeyBinding.wasPressed()) {
                if (client.player != null && client.player.isFallFlying()) {
                    ItemStack chestplate = client.player.getEquippedStack(EquipmentSlot.CHEST);
                    ItemStack elytra = client.player.getEquippedStack(EquipmentSlot.CHEST);

                    if (elytra.getItem() == Items.ELYTRA) {
                        client.player.equipStack(EquipmentSlot.CHEST, chestplate);
                        client.player.equipStack(EquipmentSlot.CHEST, elytra);
                    } else if (chestplate.getItem() != Items.AIR) {
                        client.player.equipStack(EquipmentSlot.CHEST, elytra);
                        client.player.equipStack(EquipmentSlot.CHEST, chestplate);
                    }
                }
            }
        });
    }
}