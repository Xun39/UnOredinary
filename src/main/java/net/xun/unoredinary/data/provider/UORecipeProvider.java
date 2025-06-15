package net.xun.unoredinary.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.xun.lib.common.api.item.armor.ArmorSet;
import net.xun.lib.common.api.item.tools.ToolSet;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.registry.UOItems;
import net.xun.unoredinary.util.UOTags;

import java.util.concurrent.CompletableFuture;

public abstract class UORecipeProvider extends RecipeProvider {
    public UORecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    /* ---------------------------------------- CRAFTING ---------------------------------------- */

    // Common recipes
    protected static void twoByTwoPacker(RecipeOutput recipeOutput, RecipeCategory category, ItemLike packed, Ingredient unpacked, ItemLike unlockItem) {
        ShapedRecipeBuilder.shaped(category, packed, 1)
                .define('#', unpacked)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(unlockItem), has(unlockItem))
                .save(recipeOutput);
    }

    protected static void twoByTwoPackerConvertible(RecipeOutput recipeOutput,
                                                    RecipeCategory packedCategory,
                                                    RecipeCategory unpackedCategory,
                                                    Ingredient packed,
                                                    ItemLike packedItem,
                                                    Ingredient unpacked,
                                                    ItemLike unpackedItem) {
        ShapedRecipeBuilder.shaped(packedCategory, packedItem, 1)
                .define('#', unpacked)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(unpackedItem), has(unpackedItem))
                .save(recipeOutput, CommonUtils.modLoc(getItemName(packedItem) + "_from_" + getItemName(unpackedItem)));
        ShapelessRecipeBuilder.shapeless(unpackedCategory, unpackedItem, 9)
                .requires(packed)
                .unlockedBy(getHasName(packedItem), has(packedItem))
                .save(recipeOutput, CommonUtils.modLoc(getItemName(unpackedItem) + "_from_" + getItemName(packedItem)));
    }

    protected static void threeByThreePacker(RecipeOutput recipeOutput, RecipeCategory category, ItemLike packed, Ingredient unpacked, ItemLike unpackedItem) {
        ShapedRecipeBuilder.shaped(category, packed, 1)
                .define('#', unpacked)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(unpackedItem), has(unpackedItem))
                .save(recipeOutput);
    }

    protected static void threeByThreePackerConvertible(RecipeOutput recipeOutput, RecipeCategory packedCategory, RecipeCategory unpackedCategory, ItemLike packed, ItemLike unpacked) {
        threeByThreePackerConvertible(recipeOutput, packedCategory, unpackedCategory, Ingredient.of(packed), packed, Ingredient.of(unpacked), unpacked);
    }

    protected static void threeByThreePackerConvertible(RecipeOutput recipeOutput,
                                                        RecipeCategory packedCategory,
                                                        RecipeCategory unpackedCategory,
                                                        Ingredient packed,
                                                        ItemLike packedItem,
                                                        Ingredient unpacked,
                                                        ItemLike unpackedItem) {
        ShapedRecipeBuilder.shaped(packedCategory, packedItem, 1)
                .define('#', unpacked)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(unpackedItem), has(unpackedItem))
                .save(recipeOutput, CommonUtils.modLoc(getItemName(packedItem) + "_from_" + getItemName(unpackedItem)));
        ShapelessRecipeBuilder.shapeless(unpackedCategory, unpackedItem, 9)
                .requires(packed)
                .unlockedBy(getHasName(packedItem), has(packedItem))
                .save(recipeOutput, CommonUtils.modLoc(getItemName(unpackedItem) + "_from_" + getItemName(packedItem)));
    }

    protected static void surroundByEightRecipe(RecipeOutput recipeOutput, RecipeCategory category, Ingredient surrounds, Ingredient center, ItemLike surroundsAsItem, ItemLike result) {
        ShapedRecipeBuilder.shaped(category, result)
                .define('#', surrounds).define('*', center)
                .pattern("###")
                .pattern("#*#")
                .pattern("###")
                .unlockedBy(getHasName(surroundsAsItem), has(surroundsAsItem))
                .save(recipeOutput);
    }

    // Tools
    protected static void toolsetRecipe(RecipeOutput recipeOutput, ToolSet toolSet, Ingredient ingredient, ItemLike ingredientAsItem) {
        swordRecipe(recipeOutput, ingredient, ingredientAsItem, toolSet.getSword().get());
        axeRecipe(recipeOutput, ingredient, ingredientAsItem, toolSet.getAxe().get());
        pickaxeRecipe(recipeOutput, ingredient, ingredientAsItem, toolSet.getPickaxe().get());
        hoeRecipe(recipeOutput, ingredient, ingredientAsItem, toolSet.getHoe().get());
        shovelRecipe(recipeOutput, ingredient, ingredientAsItem, toolSet.getShovel().get());
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

    protected static void axeRecipe(RecipeOutput recipeOutput, Ingredient ingredient, ItemLike unlockItem, Item result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .define('#', ingredient).define('$', Items.STICK)
                .pattern("##")
                .pattern("#$")
                .pattern(" $")
                .unlockedBy(getHasName(unlockItem), has(unlockItem))
                .save(recipeOutput);
    }

    protected static void pickaxeRecipe(RecipeOutput recipeOutput, Ingredient ingredient, ItemLike unlockItem, Item result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .define('#', ingredient).define('$', Items.STICK)
                .pattern("###")
                .pattern(" $ ")
                .pattern(" $ ")
                .unlockedBy(getHasName(unlockItem), has(unlockItem))
                .save(recipeOutput);
    }

    protected static void hoeRecipe(RecipeOutput recipeOutput, Ingredient ingredient, ItemLike unlockItem, Item result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .define('#', ingredient).define('$', Items.STICK)
                .pattern("##")
                .pattern(" $")
                .pattern(" $")
                .unlockedBy(getHasName(unlockItem), has(unlockItem))
                .save(recipeOutput);
    }

    protected static void shovelRecipe(RecipeOutput recipeOutput, Ingredient ingredient, ItemLike unlockItem, Item result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .define('#', ingredient).define('$', Items.STICK)
                .pattern("#")
                .pattern("$")
                .pattern("$")
                .unlockedBy(getHasName(unlockItem), has(unlockItem))
                .save(recipeOutput);
    }

    // Armors
    protected static void armorsetRecipe(RecipeOutput recipeOutput, ArmorSet armorSet, Ingredient ingredient, ItemLike ingredientAsItem) {
        helmetRecipe(recipeOutput, ingredient, ingredientAsItem, armorSet.getHelmet().get());
        chestplateRecipe(recipeOutput, ingredient, ingredientAsItem, armorSet.getChestplate().get());
        leggingsRecipe(recipeOutput, ingredient, ingredientAsItem, armorSet.getLeggings().get());
        bootsRecipe(recipeOutput, ingredient, ingredientAsItem, armorSet.getBoots().get());
    }

    protected static void helmetRecipe(RecipeOutput recipeOutput, Ingredient ingredient, ItemLike unlockItem, Item result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .define('#', ingredient)
                .pattern("###")
                .pattern("# #")
                .unlockedBy(getHasName(unlockItem), has(unlockItem))
                .save(recipeOutput);
    }

    protected static void chestplateRecipe(RecipeOutput recipeOutput, Ingredient ingredient, ItemLike unlockItem, Item result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .define('#', ingredient)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(unlockItem), has(unlockItem))
                .save(recipeOutput);
    }

    protected static void leggingsRecipe(RecipeOutput recipeOutput, Ingredient ingredient, ItemLike unlockItem, Item result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .define('#', ingredient)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(unlockItem), has(unlockItem))
                .save(recipeOutput);
    }

    protected static void bootsRecipe(RecipeOutput recipeOutput, Ingredient ingredient, ItemLike unlockItem, Item result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .define('#', ingredient)
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(unlockItem), has(unlockItem))
                .save(recipeOutput);
    }

    /* ---------------------------------------- SMITHING ---------------------------------------- */

    protected static void cryosteelSmithing(RecipeOutput recipeOutput, RecipeCategory category, Item ingredientItem, Item resultItem) {
        SmithingTransformRecipeBuilder
                .smithing(
                        Ingredient.of(UOItems.CRYOSTEEL_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ingredientItem), Ingredient.of(UOTags.Items.INGOTS_CRYOSTEEL), category, resultItem
                )
                .unlocks(getHasName(UOItems.CRYOSTEEL_INGOT), has(UOItems.CRYOSTEEL_INGOT))
                .save(recipeOutput, CommonUtils.modLoc(getItemName(resultItem) + "_smithing"));
    }
}
