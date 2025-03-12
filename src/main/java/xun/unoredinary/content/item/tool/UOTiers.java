package xun.unoredinary.content.item.tool;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import xun.unoredinary.registry.UOItems;
import xun.unoredinary.util.UOTags;

public class UOTiers {

    public static final Tier FROSTEEL = new SimpleTier(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            1846, 7.0F, 2.5F, 16, () -> Ingredient.of(UOItems.FROSTEEL_INGOT));

    public static final Tier LUMINTHIUM = new SimpleTier(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            1413, 6.5F, 2.0F, 20, () -> Ingredient.of(UOItems.LUMINTHIUM_INGOT));

    public static final Tier SOLARITE = new SimpleTier(UOTags.Blocks.INCORRECT_FOR_SOLARITE_TOOL,
            2478, 12.0F, 6.0F, 12, () -> Ingredient.of(UOItems.SOLARITE_GEM));

    public static final Tier RUBY = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            1267, 8.5F, 3.5F, 11, () -> Ingredient.of(UOItems.RUBY));

    public static final Tier SAPPHIRE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            1394, 9.0F, 4.0F, 12, () -> Ingredient.of(UOItems.SAPPHIRE));
}
