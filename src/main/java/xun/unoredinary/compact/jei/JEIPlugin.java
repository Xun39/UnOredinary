package xun.unoredinary.compact.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.registry.ModItems;
import xun.unoredinary.util.TextUtils;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    @Override
    public void registerRecipes(IRecipeRegistration registration) {

        registration.addIngredientInfo(new ItemStack(ModItems.FROSTEEL_SWORD.get()), VanillaTypes.ITEM_STACK, TextUtils.translatable("info", "frosteel_sword"));
        registration.addIngredientInfo(new ItemStack(ModItems.ICE_BRICK.get()), VanillaTypes.ITEM_STACK, TextUtils.translatable("info", "ice_brick"));
    }

    @Override
    public ResourceLocation getPluginUid() {
        return UnOredinary.modLoc("jei_plugin");
    }
}
