package net.xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.xun.unoredinary.data.provider.UORecipeProvider;
import net.xun.unoredinary.registry.UOArmors;
import net.xun.unoredinary.registry.UOBlocks;
import net.xun.unoredinary.registry.UOItems;
import net.xun.unoredinary.registry.UOTools;
import net.xun.unoredinary.utils.UOTags;

import java.util.concurrent.CompletableFuture;

public class UORecipes extends UORecipeProvider {
    public UORecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        // Glacium crystal and ices
        threeByThreePacker(recipeOutput, RecipeCategory.MISC, UOItems.GLACIUM_CRYSTAL, Ingredient.of(UOTags.Items.NUGGETS_GLACIUM), UOItems.GLACIUM_SHARDS);
        threeByThreePackerConvertible(
                recipeOutput,
                RecipeCategory.BUILDING_BLOCKS,
                RecipeCategory.MISC,
                Ingredient.of(UOBlocks.GLACIUM_BLOCK),
                UOBlocks.GLACIUM_BLOCK,
                Ingredient.of(UOTags.Items.GEMS_GLACIUM),
                UOItems.GLACIUM_CRYSTAL
        );

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.ICE)
                .requires(Ingredient.of(UOTags.Items.NUGGETS_GLACIUM), 1)
                .requires(Items.SNOWBALL, 1)
                .unlockedBy(getHasName(UOItems.GLACIUM_SHARDS), has(UOItems.GLACIUM_SHARDS))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.PACKED_ICE)
                .requires(Ingredient.of(UOTags.Items.NUGGETS_GLACIUM), 2)
                .requires(Items.SNOWBALL, 2)
                .unlockedBy(getHasName(UOItems.GLACIUM_SHARDS), has(UOItems.GLACIUM_SHARDS))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.BLUE_ICE)
                .requires(Ingredient.of(UOTags.Items.NUGGETS_GLACIUM), 3)
                .requires(Items.SNOWBALL, 3)
                .unlockedBy(getHasName(UOItems.GLACIUM_SHARDS), has(UOItems.GLACIUM_SHARDS))
                .save(recipeOutput);

        // Froststeel-related
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UOItems.FROSTSTEEL_INGOT)
                .requires(Ingredient.of(UOTags.Items.NUGGETS_GLACIUM))
                .requires(Items.IRON_INGOT, 3)
                .requires(Blocks.PACKED_ICE, 2)
                .unlockedBy(getHasName(Blocks.PACKED_ICE), has(Blocks.PACKED_ICE))
                .save(recipeOutput);

        toolsetRecipe(recipeOutput, UOTools.FROSTSTEEL, Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL), UOItems.FROSTSTEEL_INGOT);
        armorsetRecipe(recipeOutput, UOArmors.FROSTSTEEL, Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL), UOItems.FROSTSTEEL_INGOT);

        // Cryosteel-related
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UOItems.CRYOSTEEL_INGOT)
                .requires(Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL), 4)
                .requires(Ingredient.of(UOTags.Items.GEMS_GLACIUM), 3)
                .requires(Ingredient.of(Items.DIAMOND))
                .unlockedBy(getHasName(UOItems.FROSTSTEEL_INGOT), has(UOItems.FROSTSTEEL_INGOT))
                .save(recipeOutput); // TODO: Change the diamond to sapphire

        toolsetRecipe(recipeOutput, UOTools.CRYOSTEEL, Ingredient.of(UOTags.Items.INGOTS_CRYOSTEEL), UOItems.CRYOSTEEL_INGOT);
        armorsetRecipe(recipeOutput, UOArmors.CRYOSTEEL, Ingredient.of(UOTags.Items.INGOTS_CRYOSTEEL), UOItems.CRYOSTEEL_INGOT);
    }
}
