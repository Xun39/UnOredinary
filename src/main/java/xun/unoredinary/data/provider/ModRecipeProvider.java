package xun.unoredinary.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import xun.unoredinary.UnOredinary;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    protected SimpleCookingRecipeBuilder smelting(ItemLike ingredient, ItemLike result, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 200)
                .unlockedBy("has_" + getRegistryName(ingredient), has(ingredient));
    }

    protected static void onePlusOtherWithCustomCount(RecipeOutput output, RecipeCategory recipeCategory,
                                                      ItemLike unlockRecipeIngredient, int unlockRecipeIngredientCount,
                                                      ItemLike secondIngredient, int secondIngredientCount,
                                                      ItemLike result, int resultCount) {
        ShapelessRecipeBuilder.shapeless(recipeCategory, result, resultCount)
                .requires(unlockRecipeIngredient, unlockRecipeIngredientCount)
                .requires(secondIngredient, secondIngredientCount)
                .save(output, UnOredinary.modLoc(
                        getRegistryName(result) + "_from_" + getRegistryName(unlockRecipeIngredient) + "_plus_" + getRegistryName(secondIngredient))
                );
    }

    protected static void onePlusOther(RecipeOutput output, RecipeCategory recipeCategory, ItemLike unlockRecipeIngredient, ItemLike secondIngredient, ItemLike result) {
        ShapelessRecipeBuilder.shapeless(recipeCategory, result, 1)
                .requires(unlockRecipeIngredient, 1)
                .requires(secondIngredient, 1)
                .unlockedBy(getHasName(unlockRecipeIngredient), has(unlockRecipeIngredient))
                .save(output, UnOredinary.modLoc(
                        getRegistryName(result) + "_from_" + getRegistryName(unlockRecipeIngredient) + "_with_" + getRegistryName(secondIngredient)));
    }

    protected static void twoByTwo(RecipeOutput output, ItemLike ingredient, ItemLike result, RecipeCategory recipeCategory) {
        ShapedRecipeBuilder.shaped(recipeCategory, result).define('#', ingredient)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(output, UnOredinary.modLoc(getRegistryName(result) + "_from_" + getRegistryName(ingredient)));
    }

    protected static void twoByTwoConvertible(RecipeOutput output, ItemLike unpacked, ItemLike packed, RecipeCategory unpackedCategory, RecipeCategory packedCategory) {
        ShapelessRecipeBuilder.shapeless(unpackedCategory, unpacked, 4).requires(packed)
                .unlockedBy(getHasName(packed), has(packed))
                .save(output, UnOredinary.modLoc(getRegistryName(unpacked) + "_from_" + getRegistryName(packed)));
        ShapedRecipeBuilder.shaped(packedCategory, packed).define('#', unpacked).pattern("##").pattern("##")
                .unlockedBy(getHasName(unpacked), has(unpacked))
                .save(output, UnOredinary.modLoc(getRegistryName(packed) + "_from_" + getRegistryName(unpacked)));
    }

    protected static void threeByThreeConvertible(RecipeOutput output, ItemLike unpacked, ItemLike packed, RecipeCategory unpackedCategory, RecipeCategory packedCategory) {
        ShapelessRecipeBuilder.shapeless(unpackedCategory, unpacked, 9).requires(packed)
                .unlockedBy(getHasName(packed), has(packed))
                .save(output, UnOredinary.modLoc(getRegistryName(unpacked) + "_from_" + getRegistryName(packed)));
        ShapedRecipeBuilder.shaped(packedCategory, packed).define('#', unpacked).pattern("###").pattern("###").pattern("###")
                .unlockedBy(getHasName(unpacked), has(unpacked))
                .save(output, UnOredinary.modLoc(getRegistryName(packed) + "_from_" + getRegistryName(unpacked)));
    }

    protected static void stoneCuttingRecipe(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, ItemLike material, int resultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), category, result, resultCount)
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput, UnOredinary.modLoc(getRegistryName(result) + "_from_" + getRegistryName(material) + "_stonecutting"));
    }

    protected static void smithingUpgrade(RecipeOutput recipeOutput, Item ingredientItem, Item smithingTemplateItem, Item materialItem, Item resultItem, RecipeCategory category) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(smithingTemplateItem), Ingredient.of(ingredientItem), Ingredient.of(materialItem), category, resultItem)
                .unlocks(getHasName(materialItem), has(materialItem))
                .save(recipeOutput, UnOredinary.modLoc(getRegistryName(resultItem) + "_from_" + "smithing"));
    }

    protected static void sword(RecipeOutput recipeOutput, Supplier<Item> ingredient, Supplier<SwordItem> result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .pattern("#").pattern("#").pattern("!")
                .define('#', ingredient.get()).define('!', Items.STICK)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get()))
                .save(recipeOutput);
    }
    protected static void pickaxe(RecipeOutput recipeOutput, Supplier<Item> ingredient, Supplier<PickaxeItem> result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
                .pattern("###").pattern(" ! ").pattern(" ! ")
                .define('#', ingredient.get()).define('!', Items.STICK)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get()))
                .save(recipeOutput);
    }
    protected static void axe(RecipeOutput recipeOutput, Supplier<Item> ingredient, Supplier<AxeItem> result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
                .pattern("##").pattern("#!").pattern(" !")
                .define('#', ingredient.get()).define('!', Items.STICK)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get()))
                .save(recipeOutput);
    }
    protected static void hoe(RecipeOutput recipeOutput, Supplier<Item> ingredient, Supplier<HoeItem> result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
                .pattern("##").pattern(" !").pattern(" !")
                .define('#', ingredient.get()).define('!', Items.STICK)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get()))
                .save(recipeOutput);
    }
    protected static void shovel(RecipeOutput recipeOutput, Supplier<Item> ingredient, Supplier<ShovelItem> result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
                .pattern("#").pattern("!").pattern("!")
                .define('#', ingredient.get()).define('!', Items.STICK)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get()))
                .save(recipeOutput);
    }

    protected static void helmet(RecipeOutput recipeOutput, Supplier<Item> ingredient, Supplier<ArmorItem> result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .pattern("###").pattern("# #")
                .define('#', ingredient.get())
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get()))
                .save(recipeOutput);
    }
    protected static void chestplate(RecipeOutput recipeOutput, Supplier<Item> ingredient, Supplier<ArmorItem> result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .pattern("# #").pattern("###").pattern("###")
                .define('#', ingredient.get())
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get()))
                .save(recipeOutput);
    }
    protected static void leggings(RecipeOutput recipeOutput, Supplier<Item> ingredient, Supplier<ArmorItem> result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .pattern("###").pattern("# #").pattern("# #")
                .define('#', ingredient.get())
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get()))
                .save(recipeOutput);
    }
    protected static void boots(RecipeOutput recipeOutput, Supplier<Item> ingredient, Supplier<ArmorItem> result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .pattern("# #").pattern("# #")
                .define('#', ingredient.get())
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get()))
                .save(recipeOutput);
    }

    private static String getRegistryName(ItemLike itemLike) {
        return getItemName(itemLike);
    }
}
