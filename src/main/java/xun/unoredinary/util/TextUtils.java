package xun.unoredinary.util;

import net.minecraft.Util;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import xun.unoredinary.UnOredinary;

public class TextUtils {

    public static Component translatable(String type, String key) {
        return Component.translatable(type + '.' + UnOredinary.MOD_ID + '.' + key);
    }

    public static String makeShiftHoldingExtendDescription(String idPath) {
        return Screen.hasShiftDown() ? Util.makeDescriptionId("tooltip", UnOredinary.modLoc(idPath)) :
                Util.makeDescriptionId("tooltip", UnOredinary.modLoc("shift_hold_extend"));
    }
}
