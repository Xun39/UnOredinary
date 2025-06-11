package net.xun.unoredinary.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class UOToolTiers {
    public static final Tier FROSTSTEEL = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 513, 6.5F, 2.0F, 16, () -> Ingredient.of(UOItems.FROSTSTEEL_INGOT)
    );
}
