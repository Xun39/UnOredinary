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

import java.util.concurrent.CompletableFuture;

public abstract class UORecipeProvider extends RecipeProvider {
    public UORecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    // Common recipes
    protected static void twoByTwoPacker(RecipeOutput recipeOutput, RecipeCategory category, ItemLike packed, Ingredient unpacked, ItemLike unlockItem) {
        ShapedRecipeBuilder.shaped(category, packed, 1)
                .define('#', unpacked)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(unlockItem), has(unlockItem))
                .save(recipeOutput);
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
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(unpackedCategory, unpackedItem, 9)
                .requires(packed)
                .unlockedBy(getHasName(packedItem), has(packedItem))
                .save(recipeOutput, CommonUtils.modLoc(CommonUtils.namespacedID(getItemName(unpackedItem), "from", getItemName(packedItem))));
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

    // Tools
    protected static void toolsetRecipe(RecipeOutput recipeOutput, ToolSet toolSet, Ingredient ingredient, ItemLike unlockItem) {
        swordRecipe(recipeOutput, ingredient, unlockItem, toolSet.getSword().get());
        axeRecipe(recipeOutput, ingredient, unlockItem, toolSet.getAxe().get());
        pickaxeRecipe(recipeOutput, ingredient, unlockItem, toolSet.getPickaxe().get());
        hoeRecipe(recipeOutput, ingredient, unlockItem, toolSet.getHoe().get());
        shovelRecipe(recipeOutput, ingredient, unlockItem, toolSet.getShovel().get());
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
    protected static void armorsetRecipe(RecipeOutput recipeOutput, ArmorSet armorSet, Ingredient ingredient, ItemLike unlockItem) {
        helmetRecipe(recipeOutput, ingredient, unlockItem, armorSet.getHelmet().get());
        chestplateRecipe(recipeOutput, ingredient, unlockItem, armorSet.getChestplate().get());
        leggingsRecipe(recipeOutput, ingredient, unlockItem, armorSet.getLeggings().get());
        bootsRecipe(recipeOutput, ingredient, unlockItem, armorSet.getBoots().get());
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
}
