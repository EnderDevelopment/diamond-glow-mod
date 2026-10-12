package com.yousufamini4229.glowingdiamondsmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlowingDiamondsMod implements ModInitializer {
    public static final String MOD_ID = "glowingdiamondsmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static KeyBinding toggleGlowKey;
    private static boolean isGlowing = false;

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Glowing Diamonds Mod");
        toggleGlowKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.toggle_glow",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "key.category.glowingdiamondsmod"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleGlowKey.wasPressed()) {
                isGlowing = !isGlowing;
                LOGGER.info("Glowing diamonds: " + isGlowing);
            }
        });
    }

    public static boolean isGlowing() {
        return isGlowing;
    }
}