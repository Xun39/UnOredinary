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

        // Glacium-related
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

        // Froststeel-related
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UOItems.FROSTSTEEL_INGOT)
                .requires(UOItems.CRYIC_POWDER, 2)
                .requires(Items.IRON_INGOT, 3)
                .requires(Blocks.PACKED_ICE)
                .unlockedBy(getHasName(UOItems.CRYIC_POWDER), has(UOItems.CRYIC_POWDER))
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

        copySmithingTemplate(recipeOutput, UOItems.CRYOSTEEL_UPGRADE_SMITHING_TEMPLATE, Ingredient.of(Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE));

        cryosteelSmithing(recipeOutput, RecipeCategory.TOOLS, UOTools.FROSTSTEEL.getAxe().get(), UOTools.CRYOSTEEL.getAxe().get());
        cryosteelSmithing(recipeOutput, RecipeCategory.TOOLS, UOTools.FROSTSTEEL.getPickaxe().get(), UOTools.CRYOSTEEL.getPickaxe().get());
        cryosteelSmithing(recipeOutput, RecipeCategory.TOOLS, UOTools.FROSTSTEEL.getHoe().get(), UOTools.CRYOSTEEL.getHoe().get());
        cryosteelSmithing(recipeOutput, RecipeCategory.TOOLS, UOTools.FROSTSTEEL.getShovel().get(), UOTools.CRYOSTEEL.getShovel().get());

        cryosteelSmithing(recipeOutput, RecipeCategory.COMBAT, UOTools.FROSTSTEEL.getSword().get(), UOTools.CRYOSTEEL.getSword().get());

        cryosteelSmithing(recipeOutput, RecipeCategory.COMBAT, UOArmors.FROSTSTEEL.getHelmet().get(), UOArmors.CRYOSTEEL.getHelmet().get());
        cryosteelSmithing(recipeOutput, RecipeCategory.COMBAT, UOArmors.FROSTSTEEL.getChestplate().get(), UOArmors.CRYOSTEEL.getChestplate().get());
        cryosteelSmithing(recipeOutput, RecipeCategory.COMBAT, UOArmors.FROSTSTEEL.getLeggings().get(), UOArmors.CRYOSTEEL.getLeggings().get());
        cryosteelSmithing(recipeOutput, RecipeCategory.COMBAT, UOArmors.FROSTSTEEL.getBoots().get(), UOArmors.CRYOSTEEL.getBoots().get());
    }
}
