package xun.unoredinary.content.item.tool;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import xun.unoredinary.registry.ModItems;
import xun.unoredinary.util.ModTags;

public class ModToolTiers {

    public static final Tier FROSTEEL = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_FROSTEEL_TOOL,
            1846, 7.0F, 2.5F, 16, () -> Ingredient.of(ModItems.FROSTEEL_INGOT));

    public static final Tier RUBY = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_RUBY_TOOL,
            1267, 8.5F, 3.5F, 11, () -> Ingredient.of(ModItems.RUBY));

    public static final Tier LUMINTHIUM = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_LUMINTHIUM_TOOL,
            1413, 6.5F, 2.0F, 20, () -> Ingredient.of(ModItems.LUMINTHIUM_INGOT));

    public static final Tier SOLARITE = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_SOLARITE_TOOL,
            2478, 12.0F, 6.0F, 12, () -> Ingredient.of(ModItems.SOLARITE_GEM));
}
