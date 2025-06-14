package net.xun.unoredinary.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.xun.unoredinary.util.UOTags;

public class UOToolTiers {

    public static final Tier FROSTSTEEL = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 767, 6.5F, 2.0F, 16, () -> Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL)
    );

    public static final Tier CRYOSTEEL = new SimpleTier(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1742, 9.0F, 4.0F, 13, () -> Ingredient.of(UOTags.Items.INGOTS_CRYOSTEEL)
    );
}
