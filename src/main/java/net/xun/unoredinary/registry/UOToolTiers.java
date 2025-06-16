package net.xun.unoredinary.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.xun.unoredinary.util.UOTags;

public class UOToolTiers {

    public static final Tier FROSTSTEEL = new SimpleTier(
            UOTags.Blocks.INCORRECT_FOR_FROSTSTEEL_TOOL, 767, 6.5F, 2.0F, 16, () -> Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL)
    );

    public static final Tier CRYOSTEEL = new SimpleTier(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1742, 9.0F, 4.0F, 13, () -> Ingredient.of(UOTags.Items.INGOTS_CRYOSTEEL)
    );

    public static final Tier SAPPHIRE = new SimpleTier(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1261, 10.0F, 3.0F, 9, () -> Ingredient.of(UOTags.Items.GEMS_SAPPHIRE)
    );
}
