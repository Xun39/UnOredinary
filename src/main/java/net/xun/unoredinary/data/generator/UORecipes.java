package net.xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.xun.lib.common.api.item.tools.ToolSet;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.data.provider.UORecipeProvider;
import net.xun.unoredinary.registry.UOArmors;
import net.xun.unoredinary.registry.UOBlocks;
import net.xun.unoredinary.registry.UOItems;
import net.xun.unoredinary.registry.UOTools;
import net.xun.unoredinary.util.UOTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class UORecipes extends UORecipeProvider {
    public UORecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        // Cryic-related
        threeByThreePackerConvertible(recipeOutput, RecipeCategory.BUILDING_BLOCKS, RecipeCategory.MISC, UOBlocks.CRYIC_BLOCK, UOItems.CRYIC_POWDER);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.ICE)
                .requires(Ingredient.of(UOTags.Items.DUSTS_CRYIC), 1)
                .requires(Blocks.SNOW_BLOCK, 1)
                .unlockedBy(getHasName(UOItems.CRYIC_POWDER), has(UOTags.Items.DUSTS_CRYIC))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.PACKED_ICE)
                .requires(Ingredient.of(UOTags.Items.DUSTS_CRYIC), 2)
                .requires(Blocks.SNOW_BLOCK, 2)
                .unlockedBy(getHasName(UOItems.CRYIC_POWDER), has(UOTags.Items.DUSTS_CRYIC))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.BLUE_ICE)
                .requires(Ingredient.of(UOTags.Items.DUSTS_CRYIC), 3)
                .requires(Blocks.SNOW_BLOCK, 3)
                .unlockedBy(getHasName(UOItems.CRYIC_POWDER), has(UOTags.Items.DUSTS_CRYIC))
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
        threeByThreePacker(recipeOutput, RecipeCategory.MISC, UOItems.GLACIUM_CRYSTAL, Ingredient.of(UOItems.GLACIUM_SHARD), UOItems.GLACIUM_SHARD);
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
                .requires(Ingredient.of(UOTags.Items.DUSTS_CRYIC), 2)
                .requires(Ingredient.of(Tags.Items.INGOTS_IRON), 3)
                .requires(Blocks.PACKED_ICE)
                .unlockedBy(getHasName(UOItems.CRYIC_POWDER), has(UOTags.Items.DUSTS_CRYIC))
                .save(recipeOutput);
        threeByThreePackerConvertible(
                recipeOutput,
                RecipeCategory.BUILDING_BLOCKS,
                RecipeCategory.MISC,
                Ingredient.of(UOBlocks.FROSTSTEEL_BLOCK),
                UOBlocks.FROSTSTEEL_BLOCK,
                Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL),
                UOItems.FROSTSTEEL_INGOT);
        threeByThreePackerConvertible(
                recipeOutput,
                RecipeCategory.MISC,
                RecipeCategory.MISC,
                Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL),
                UOItems.FROSTSTEEL_INGOT,
                Ingredient.of(UOTags.Items.NUGGETS_FROSTSTEEL),
                UOItems.FROSTSTEEL_NUGGET);

        addCombatSetSmelting(recipeOutput, UOTools.FROSTSTEEL, UOArmors.FROSTSTEEL, UOItems.FROSTSTEEL_NUGGET);
        addCombatSetBlasting(recipeOutput, UOTools.FROSTSTEEL, UOArmors.FROSTSTEEL, UOItems.FROSTSTEEL_NUGGET);

        toolsetRecipe(recipeOutput, UOTools.FROSTSTEEL, Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL), UOItems.FROSTSTEEL_INGOT);
        armorsetRecipe(recipeOutput, UOArmors.FROSTSTEEL, Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL), UOItems.FROSTSTEEL_INGOT);

        // Glacialite-related
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UOItems.GLACIALITE_INGOT)
                .requires(Ingredient.of(UOTags.Items.INGOTS_FROSTSTEEL), 4)
                .requires(Ingredient.of(UOTags.Items.GEMS_GLACIUM), 3)
                .requires(Ingredient.of(UOTags.Items.GEMS_SAPPHIRE))
                .unlockedBy(getHasName(UOItems.FROSTSTEEL_INGOT), has(UOTags.Items.INGOTS_FROSTSTEEL))
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

        // Luminite-related
        threeByThreePackerConvertible(
                recipeOutput,
                RecipeCategory.BUILDING_BLOCKS,
                RecipeCategory.MISC,
                Ingredient.of(UOBlocks.LUMINITE_BLOCK),
                UOBlocks.LUMINITE_BLOCK,
                Ingredient.of(UOTags.Items.GEMS_LUMINITE),
                UOItems.LUMINITE_CRYSTAL
        );
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.SPECTRAL_ARROW, 16)
                .define('^', UOTags.Items.GEMS_LUMINITE)
                .define('$', Items.STICK)
                .define('#', Items.FEATHER)
                .pattern("^")
                .pattern("$")
                .pattern("#")
                .unlockedBy(getHasName(UOItems.LUMINITE_CRYSTAL), has(UOTags.Items.GEMS_LUMINITE))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UOItems.LUMINITE_CRYSTAL)
                .define('*', Tags.Items.GEMS_AMETHYST)
                .define('$', Tags.Items.GEMS_DIAMOND)
                .define('#', Tags.Items.DUSTS_GLOWSTONE)
                .pattern("$#$")
                .pattern("#*#")
                .pattern("$#$")
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Tags.Items.GEMS_AMETHYST))
                .save(recipeOutput);


        // Luminium-related
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UOItems.LUMINIUM_INGOT)
                .requires(Ingredient.of(Tags.Items.INGOTS_COPPER), 4)
                .requires(Ingredient.of(UOTags.Items.GEMS_LUMINITE), 3)
                .requires(Ingredient.of(Tags.Items.DUSTS_GLOWSTONE))
                .unlockedBy(getHasName(UOItems.LUMINITE_CRYSTAL), has(UOTags.Items.GEMS_LUMINITE))
                .save(recipeOutput);
        threeByThreePackerConvertible(
                recipeOutput,
                RecipeCategory.BUILDING_BLOCKS,
                RecipeCategory.MISC,
                Ingredient.of(UOBlocks.LUMINIUM_BLOCK),
                UOBlocks.LUMINIUM_BLOCK,
                Ingredient.of(UOTags.Items.INGOTS_LUMINIUM),
                UOItems.LUMINIUM_INGOT
        );
        threeByThreePackerConvertible(
                recipeOutput,
                RecipeCategory.MISC,
                RecipeCategory.MISC,
                Ingredient.of(UOTags.Items.INGOTS_LUMINIUM),
                UOItems.LUMINIUM_INGOT,
                Ingredient.of(UOTags.Items.NUGGETS_LUMINIUM),
                UOItems.LUMINIUM_NUGGET
        );

        addCombatSetSmelting(recipeOutput, UOTools.LUMINIUM, UOArmors.LUMINIUM, UOItems.LUMINIUM_NUGGET);
        addCombatSetBlasting(recipeOutput, UOTools.LUMINIUM, UOArmors.LUMINIUM, UOItems.LUMINIUM_NUGGET);

        toolsetRecipe(recipeOutput, UOTools.LUMINIUM, Ingredient.of(UOTags.Items.INGOTS_LUMINIUM), UOItems.LUMINIUM_INGOT);
        armorsetRecipe(recipeOutput, UOArmors.LUMINIUM, Ingredient.of(UOTags.Items.INGOTS_LUMINIUM), UOItems.LUMINIUM_INGOT);
    }
}
