package xun.unoredinary.content.item.tool;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import xun.unoredinary.registry.ModItems;
import xun.unoredinary.util.ModTags;

public class ModToolTiers {

    public static final Tier FROSTEEL = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_FROSTEEL_TOOL,
            739, 7.0F, 2.5F, 16, () -> Ingredient.of(ModItems.FROSTEEL_INGOT));

    public static final Tier RUBY = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_RUBY_TOOL,
            1267, 8.0F, 3.0F, 11, () -> Ingredient.of(ModItems.RUBY));
}
