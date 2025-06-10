package net.xun.unoredinary.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.xun.lib.common.api.util.CommonUtils;

import java.util.concurrent.CompletableFuture;

public abstract class UORecipeProvider extends RecipeProvider {
    public UORecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    protected static void twoByTwoPackerConvertible(RecipeOutput recipeOutput, RecipeCategory packedCategory, RecipeCategory unpackedCategory, ItemLike packed, ItemLike unpacked) {
        ShapedRecipeBuilder.shaped(packedCategory, packed, 1)
                .define('#', unpacked)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(unpacked), has(unpacked))
                .save(recipeOutput, CommonUtils.modLoc(CommonUtils.namespacedID(getItemName(packed), "from", getItemName(unpacked))));
        ShapelessRecipeBuilder.shapeless(unpackedCategory, unpacked, 4)
                .requires(packed)
                .unlockedBy(getHasName(packed), has(packed))
                .save(recipeOutput, CommonUtils.modLoc(CommonUtils.namespacedID(getItemName(unpacked), "from", getItemName(packed))));
    }

    protected static void swordRecipe(RecipeOutput recipeOutput, Ingredient ingredient, ItemLike unlockItem, Item result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .define('#', ingredient).define('$', Items.STICK)
                .pattern("#")
                .pattern("#")
                .pattern("$")
                .unlockedBy(getHasName(unlockItem), has(unlockItem))
                .save(recipeOutput);
    }
}
