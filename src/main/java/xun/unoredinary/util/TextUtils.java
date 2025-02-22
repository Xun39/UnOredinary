package xun.unoredinary.util;

import net.minecraft.Util;
import net.minecraft.client.gui.screens.Screen;
import xun.unoredinary.UnOredinary;

public class TextUtils {

    public static String makeShiftHoldingExtendDescription(String idPath) {
        return Screen.hasShiftDown() ? Util.makeDescriptionId("tooltip", UnOredinary.modLoc(idPath)) :
                Util.makeDescriptionId("tooltip", UnOredinary.modLoc("shift_hold_extend"));
    }
}
