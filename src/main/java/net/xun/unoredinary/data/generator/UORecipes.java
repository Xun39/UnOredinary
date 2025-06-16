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
import net.xun.unoredinary.util.UOTags;

import java.util.concurrent.CompletableFuture;

public class UORecipes extends UORecipeProvider {
    public UORecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        // Cryic-related
        threeByThreePackerConvertible(recipeOutput, RecipeCategory.BUILDING_BLOCKS, RecipeCategory.MISC, UOBlocks.CRYIC_BLOCK, UOItems.CRYIC_POWDER);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.ICE)
                .requires(UOItems.CRYIC_POWDER, 1)
                .requires(Blocks.SNOW_BLOCK, 1)
                .unlockedBy(getHasName(UOItems.CRYIC_POWDER), has(UOItems.CRYIC_POWDER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.PACKED_ICE)
                .requires(UOItems.CRYIC_POWDER, 2)
                .requires(Blocks.SNOW_BLOCK, 2)
                .unlockedBy(getHasName(UOItems.CRYIC_POWDER), has(UOItems.CRYIC_POWDER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.BLUE_ICE)
                .requires(UOItems.CRYIC_POWDER, 3)
                .requires(Blocks.SNOW_BLOCK, 3)
                .unlockedBy(getHasName(UOItems.CRYIC_POWDER), has(UOItems.CRYIC_POWDER))
                .save(recipeOutput);

        // Sapphire-related
        threeByThreePackerConvertible(
                recipeOutput,
                RecipeCategory.BUILDING_BLOCKS,
                RecipeCategory.MISC,
                Ingredient.of(UOBlocks.SAPPHIRE_BLOCK),
                UOBlocks.SAPPHIRE_BLOCK,
                Ingredient.of(UOTags.Items.GEMS_SAPPHIRE),
                UOItems.SAPPHIRE
        );

        toolsetRecipe(recipeOutput, UOTools.SAPPHIRE, Ingredient.of(UOTags.Items.GEMS_SAPPHIRE), UOItems.SAPPHIRE);
        armorsetRecipe(recipeOutput, UOArmors.SAPPHIRE, Ingredient.of(UOTags.Items.GEMS_SAPPHIRE), UOItems.SAPPHIRE);

        // Glacium-related
        threeByThreePacker(recipeOutput, RecipeCategory.MISC, UOItems.GLACIUM_CRYSTAL, Ingredient.of(UOTags.Items.NUGGETS_GLACIUM), UOItems.GLACIUM_SHARD);
        threeByThreePackerConvertible(
                recipeOutput,
                RecipeCategory.BUILDING_BLOCKS,
                RecipeCategory.MISC,
                Ingredient.of(UOBlocks.GLACIUM_BLOCK),
                UOBlocks.GLACIUM_BLOCK,
                Ingredient.of(UOTags.Items.GEMS_GLACIUM),
                UOItems.GLACIUM_CRYSTAL
        );

        // Froststeel-related
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UOItems.FROSTSTEEL_INGOT)
                .requires(UOItems.CRYIC_POWDER, 2)
                .requires(Items.IRON_INGOT, 3)
                .requires(Blocks.PACKED_ICE)
                .unlockedBy(getHasName(UOItems.CRYIC_POWDER), has(UOItems.CRYIC_POWDER))
                .save(recipeOutput);
        threeByThreePackerConvertible(
                recipeOutput,
                RecipeCategory.BUILDING_BLOCKS,
                RecipeCategory.MISC,
                Ingredient.of(UOBlocks.FROSTSTEEL_BLOCK),
                UOBlocks.FROSTSTEEL_BLOCK,
                Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL),
                UOItems.FROSTSTEEL_INGOT);

        toolsetRecipe(recipeOutput, UOTools.FROSTSTEEL, Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL), UOItems.FROSTSTEEL_INGOT);
        armorsetRecipe(recipeOutput, UOArmors.FROSTSTEEL, Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL), UOItems.FROSTSTEEL_INGOT);

        // Glacialite-related
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UOItems.GLACIALITE_INGOT)
                .requires(Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL), 4)
                .requires(Ingredient.of(UOTags.Items.GEMS_GLACIUM), 3)
                .requires(Ingredient.of(UOTags.Items.GEMS_SAPPHIRE))
                .unlockedBy(getHasName(UOItems.FROSTSTEEL_INGOT), has(UOItems.FROSTSTEEL_INGOT))
                .save(recipeOutput);
        threeByThreePackerConvertible(
                recipeOutput,
                RecipeCategory.BUILDING_BLOCKS,
                RecipeCategory.MISC,
                Ingredient.of(UOBlocks.GLACIALITE_BLOCK),
                UOBlocks.GLACIALITE_BLOCK,
                Ingredient.of(UOTags.Items.INGOTS_GLACIALITE),
                UOItems.GLACIALITE_INGOT);

        copySmithingTemplate(recipeOutput, UOItems.GLACIALITE_UPGRADE_SMITHING_TEMPLATE, Ingredient.of(Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE));

        glacialiteSmithing(recipeOutput, RecipeCategory.COMBAT, UOTools.FROSTSTEEL.getSword().get(), UOTools.GLACIALITE.getSword().get());
        glacialiteSmithing(recipeOutput, RecipeCategory.TOOLS, UOTools.FROSTSTEEL.getAxe().get(), UOTools.GLACIALITE.getAxe().get());
        glacialiteSmithing(recipeOutput, RecipeCategory.TOOLS, UOTools.FROSTSTEEL.getPickaxe().get(), UOTools.GLACIALITE.getPickaxe().get());
        glacialiteSmithing(recipeOutput, RecipeCategory.TOOLS, UOTools.FROSTSTEEL.getHoe().get(), UOTools.GLACIALITE.getHoe().get());
        glacialiteSmithing(recipeOutput, RecipeCategory.TOOLS, UOTools.FROSTSTEEL.getShovel().get(), UOTools.GLACIALITE.getShovel().get());

        glacialiteSmithing(recipeOutput, RecipeCategory.COMBAT, UOArmors.FROSTSTEEL.getHelmet().get(), UOArmors.GLACIALITE.getHelmet().get());
        glacialiteSmithing(recipeOutput, RecipeCategory.COMBAT, UOArmors.FROSTSTEEL.getChestplate().get(), UOArmors.GLACIALITE.getChestplate().get());
        glacialiteSmithing(recipeOutput, RecipeCategory.COMBAT, UOArmors.FROSTSTEEL.getLeggings().get(), UOArmors.GLACIALITE.getLeggings().get());
        glacialiteSmithing(recipeOutput, RecipeCategory.COMBAT, UOArmors.FROSTSTEEL.getBoots().get(), UOArmors.GLACIALITE.getBoots().get());
    }
}
