package net.xun.unoredinary.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.xun.lib.common.api.item.armor.ArmorSet;
import net.xun.lib.common.api.item.tools.ToolSet;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.registry.UOItems;
import net.xun.unoredinary.util.UOTags;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

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

    /* ---------------------------------------- COOKING ---------------------------------------- */

    protected void addCombatSetSmelting(RecipeOutput recipeOutput, ToolSet toolSet, ArmorSet armorSet, ItemLike result) {
        ItemLike[] setItems = Stream.concat(
                toolSet.getAll().stream().map(item -> (ItemLike) item),
                armorSet.getAll().stream().map(item -> (ItemLike) item)
        ).toArray(ItemLike[]::new);

        if (!Objects.equals(toolSet.getName(), armorSet.getName()))
            return;

        SimpleCookingRecipeBuilder builder = SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(setItems),
                RecipeCategory.MISC,
                result,
                0.1f,
                200);

        for (ItemLike item : setItems) {
            builder.unlockedBy(getHasName(item), has(item));
        }

        builder.save(recipeOutput, CommonUtils.modLoc(getItemName(result) + "_from_smelting"));
    }

    protected void addCombatSetBlasting(RecipeOutput recipeOutput, ToolSet toolSet, ArmorSet armorSet, ItemLike result) {
        ItemLike[] setItems = Stream.concat(
                toolSet.getAll().stream().map(item -> (ItemLike) item),
                armorSet.getAll().stream().map(item -> (ItemLike) item)
        ).toArray(ItemLike[]::new);

        if (!Objects.equals(toolSet.getName(), armorSet.getName()))
            return;

        SimpleCookingRecipeBuilder builder = SimpleCookingRecipeBuilder.blasting(
                Ingredient.of(setItems),
                RecipeCategory.MISC,
                result,
                0.1f,
                200);

        for (ItemLike item : setItems) {
            builder.unlockedBy(getHasName(item), has(item));
        }

        builder.save(recipeOutput, CommonUtils.modLoc(getItemName(result) + "_from_blasting"));
    }

    /* ---------------------------------------- SMITHING ---------------------------------------- */

    protected static void glacialiteSmithing(RecipeOutput recipeOutput, RecipeCategory category, Item ingredientItem, Item resultItem) {
        SmithingTransformRecipeBuilder
                .smithing(
                        Ingredient.of(UOItems.GLACIALITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ingredientItem), Ingredient.of(UOTags.Items.INGOTS_GLACIALITE), category, resultItem
                )
                .unlocks(getHasName(UOItems.GLACIALITE_INGOT), has(UOItems.GLACIALITE_INGOT))
                .save(recipeOutput, CommonUtils.modLoc(getItemName(resultItem) + "_smithing"));
    }
}
