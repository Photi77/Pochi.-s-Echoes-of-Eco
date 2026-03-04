package net.pochi.pochimod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

import java.awt.event.KeyEvent;

public class KeyBinding {
    public static final String KEY_CATEGORY_XPREAL = "key.category.pochimod.pochi";
    public static final String KEY_UNI_JUMP = "key.pochimod.uni_jump";

    public static final String KEY_RIGHT_1 = "key.pochimod.right_click_1";

    public static final String KEY_XP_1 = "key.pochimod.xp1";
    public static final String KEY_XP_2 = "key.pochimod.xp2";
    public static final String KEY_XP_3 = "key.pochimod.xp3";
    public static final String KEY_XP_4 = "key.pochimod.xp4";
    public static final String KEY_XP_5 = "key.pochimod.xp5";
    public static final String KEY_XP_6 = "key.pochimod.xp6";
    public static final String KEY_XP_7 = "key.pochimod.xp7";
    public static final String KEY_XP_8 = "key.pochimod.xp8";


    public static final KeyMapping RIGHT = new KeyMapping(KEY_RIGHT_1, KeyConflictContext.IN_GAME,
            InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_RIGHT, KEY_CATEGORY_XPREAL);
    public static final KeyMapping JUMP_KEY = new KeyMapping(KEY_UNI_JUMP, KeyEvent.VK_SPACE, KEY_CATEGORY_XPREAL);
    public static final KeyMapping XP_1_KEY = new KeyMapping(KEY_XP_1, KeyEvent.VK_Y, KEY_CATEGORY_XPREAL);
    public static final KeyMapping XP_2_KEY = new KeyMapping(KEY_XP_2, KeyEvent.VK_H, KEY_CATEGORY_XPREAL);
    public static final KeyMapping XP_3_KEY = new KeyMapping(KEY_XP_3, KeyEvent.VK_I, KEY_CATEGORY_XPREAL);
    //public static final KeyMapping XP_4_KEY = new KeyMapping(KEY_XP_4, KeyEvent.VK_H, KEY_CATEGORY_XPREAL);
    public static final KeyMapping XP_5_KEY = new KeyMapping(KEY_XP_5, KeyEvent.VK_J, KEY_CATEGORY_XPREAL);
    public static final KeyMapping XP_6_KEY = new KeyMapping(KEY_XP_6, KeyEvent.VK_K, KEY_CATEGORY_XPREAL);
    public static final KeyMapping XP_7_KEY = new KeyMapping(KEY_XP_7, KeyEvent.VK_O, KEY_CATEGORY_XPREAL);
    public static final KeyMapping XP_8_KEY = new KeyMapping(KEY_XP_8, KeyEvent.VK_N, KEY_CATEGORY_XPREAL);
}
