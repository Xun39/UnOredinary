package xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import xun.unoredinary.content.item.armor.UOArmors;
import xun.unoredinary.content.item.tool.UOTools;
import xun.unoredinary.data.provider.UORecipeProvider;
import xun.unoredinary.registry.UOBlocks;
import xun.unoredinary.registry.UOItems;

import java.util.concurrent.CompletableFuture;

public class UORecipes extends UORecipeProvider {

    public UORecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        // Hemocrylic
        twoByTwoConvertible(recipeOutput, UOItems.HEMOCRYLIC_SHARD, UOItems.HEMOCRYLIC_CRYSTAL, RecipeCategory.MISC, RecipeCategory.MISC);
        threeByThreeConvertible(recipeOutput, UOItems.HEMOCRYLIC_CRYSTAL, UOBlocks.HEMOCRYLIC_BLOCK, RecipeCategory.MISC, RecipeCategory.BUILDING_BLOCKS);

        twoByTwo(recipeOutput, UOItems.ICE_SHARD.get(), Blocks.ICE, RecipeCategory.BUILDING_BLOCKS);

        // Frosteel
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UOItems.FROSTEEL_INGOT)
                .requires(UOItems.HEMOCRYLIC_CRYSTAL.get(), 4)
                .requires(Items.IRON_INGOT, 4)
                .unlockedBy(getHasName(UOItems.HEMOCRYLIC_CRYSTAL), has(UOItems.HEMOCRYLIC_CRYSTAL))
                .save(recipeOutput);

        threeByThreeConvertible(recipeOutput, UOItems.FROSTEEL_INGOT, UOBlocks.FROSTEEL_BLOCK, RecipeCategory.MISC, RecipeCategory.BUILDING_BLOCKS);

        // Template Upgrade Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE, 2)
                .pattern("#P#")
                .pattern("#I#")
                .pattern("###")
                .define('#', Items.DIAMOND)
                .define('P', UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE)
                .define('I', Blocks.ICE)
                .unlockedBy(getHasName(UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE), has(UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE))
                .save(recipeOutput);

        smithingUpgrade(recipeOutput, Items.IRON_SWORD, UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), UOItems.FROSTEEL_INGOT.get(),
                UOTools.FROSTEEL.sword().get(), RecipeCategory.COMBAT);
        smithingUpgrade(recipeOutput, Items.IRON_PICKAXE, UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), UOItems.FROSTEEL_INGOT.get(),
                UOTools.FROSTEEL.pickaxe().get(), RecipeCategory.TOOLS);
        smithingUpgrade(recipeOutput, Items.IRON_AXE, UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), UOItems.FROSTEEL_INGOT.get(),
                UOTools.FROSTEEL.axe().get(), RecipeCategory.TOOLS);
        smithingUpgrade(recipeOutput, Items.IRON_HOE, UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), UOItems.FROSTEEL_INGOT.get(),
                UOTools.FROSTEEL.hoe().get(), RecipeCategory.TOOLS);
        smithingUpgrade(recipeOutput, Items.IRON_SHOVEL, UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), UOItems.FROSTEEL_INGOT.get(),
                UOTools.FROSTEEL.shovel().get(), RecipeCategory.TOOLS);

        smithingUpgrade(recipeOutput, Items.IRON_HELMET, UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), UOItems.FROSTEEL_INGOT.get(),
                UOArmors.FROSTEEL.helmet().get(), RecipeCategory.COMBAT);
        smithingUpgrade(recipeOutput, Items.IRON_CHESTPLATE, UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), UOItems.FROSTEEL_INGOT.get(),
                UOArmors.FROSTEEL.chestplate().get(), RecipeCategory.COMBAT);
        smithingUpgrade(recipeOutput, Items.IRON_LEGGINGS, UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), UOItems.FROSTEEL_INGOT.get(),
                UOArmors.FROSTEEL.leggings().get(), RecipeCategory.COMBAT);
        smithingUpgrade(recipeOutput, Items.IRON_BOOTS, UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), UOItems.FROSTEEL_INGOT.get(),
                UOArmors.FROSTEEL.boots().get(), RecipeCategory.COMBAT);

        // Luminite Ores
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UOItems.LUMINITE_TORCH, 4)
                .pattern("#")
                .pattern("!")
                .define('!', Items.STICK)
                .define('#', UOItems.LUMINITE_CRYSTAL)
                .unlockedBy(getHasName(UOItems.LUMINITE_CRYSTAL), has(UOItems.LUMINITE_CRYSTAL))
                .save(recipeOutput);
        threeByThreeConvertible(recipeOutput, UOItems.LUMINITE_CRYSTAL, UOBlocks.LUMINITE_BLOCK, RecipeCategory.MISC, RecipeCategory.BUILDING_BLOCKS);

        // Luminthium
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UOItems.LUMINTHIUM_INGOT)
                .requires(UOItems.LUMINITE_CRYSTAL.get(), 4)
                .requires(Items.COPPER_INGOT, 4)
                .unlockedBy(getHasName(UOItems.LUMINITE_CRYSTAL), has(UOItems.LUMINITE_CRYSTAL))
                .save(recipeOutput);

        threeByThreeConvertible(recipeOutput, UOItems.LUMINTHIUM_INGOT, UOBlocks.LUMINTHIUM_BLOCK, RecipeCategory.MISC, RecipeCategory.BUILDING_BLOCKS);

        toolSet(recipeOutput, UOItems.LUMINTHIUM_INGOT, UOTools.LUMINTHIUM);
        armorSet(recipeOutput, UOItems.LUMINTHIUM_INGOT, UOArmors.LUMINTHIUM);

        // Solarite
        toolSet(recipeOutput, UOItems.SOLARITE_GEM, UOTools.SOLARITE);
        armorSet(recipeOutput, UOItems.SOLARITE_GEM, UOArmors.SOLARITE);

        // Ruby Ores
        threeByThreeConvertible(recipeOutput, UOItems.RUBY, UOBlocks.RUBY_BLOCK, RecipeCategory.MISC, RecipeCategory.BUILDING_BLOCKS);

        toolSet(recipeOutput, UOItems.RUBY, UOTools.RUBY);
        armorSet(recipeOutput, UOItems.RUBY, UOArmors.RUBY);

        // Sapphire
        toolSet(recipeOutput, UOItems.SAPPHIRE, UOTools.SAPPHIRE);
        armorSet(recipeOutput, UOItems.SAPPHIRE, UOArmors.SAPPHIRE);

        // Frostbound Stone
        onePlusOther(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOItems.CRYOSTONE_DUST, Blocks.STONE, UOBlocks.FROSTBOUND_STONE);
        smelting(UOBlocks.FROSTBOUND_STONE, Blocks.STONE, 0.1F, 1);

        stairBuilder(UOBlocks.FROSTBOUND_STONE_STAIRS.get(), Ingredient.of(UOBlocks.FROSTBOUND_STONE))
                .unlockedBy("has_frostbound_stone", has(UOBlocks.FROSTBOUND_STONE)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_STONE_SLAB.get(), UOBlocks.FROSTBOUND_STONE);

        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_STONE_STAIRS, UOBlocks.FROSTBOUND_STONE, 1);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_STONE_SLAB, UOBlocks.FROSTBOUND_STONE, 2);


        twoByTwo(recipeOutput, UOBlocks.FROSTBOUND_STONE, UOBlocks.FROSTBOUND_STONE_BRICKS, RecipeCategory.BUILDING_BLOCKS);
        smelting(UOBlocks.FROSTBOUND_STONE_BRICKS, Blocks.STONE_BRICKS, 0.1F, 1);


        // Frostbound Stone Bricks
        onePlusOther(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOItems.CRYOSTONE_DUST, Blocks.STONE_BRICKS, UOBlocks.FROSTBOUND_STONE_BRICKS);

        stairBuilder(UOBlocks.FROSTBOUND_STONE_BRICK_STAIRS.get(), Ingredient.of(UOBlocks.FROSTBOUND_STONE))
                .unlockedBy("has_frostbound_stone_bricks", has(UOBlocks.FROSTBOUND_STONE)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_STONE_BRICK_SLAB.get(), UOBlocks.FROSTBOUND_STONE_BRICKS);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_STONE_BRICK_WALL.get(), UOBlocks.FROSTBOUND_STONE_BRICKS);

        // Stone cutting from Stone Bricks
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_STONE_BRICK_STAIRS, UOBlocks.FROSTBOUND_STONE_BRICKS, 1);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_STONE_BRICK_SLAB, UOBlocks.FROSTBOUND_STONE_BRICKS, 2);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_STONE_BRICK_WALL, UOBlocks.FROSTBOUND_STONE_BRICKS, 1);
        // Stone cutting from Stone
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_STONE_BRICK_STAIRS, UOBlocks.FROSTBOUND_STONE, 1);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_STONE_BRICK_SLAB, UOBlocks.FROSTBOUND_STONE, 2);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_STONE_BRICK_WALL, UOBlocks.FROSTBOUND_STONE, 1);


        // Frostbound Cobblestone
        onePlusOther(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOItems.CRYOSTONE_DUST, Blocks.COBBLESTONE, UOBlocks.FROSTBOUND_COBBLESTONE);

        smelting(UOBlocks.FROSTBOUND_COBBLESTONE, Blocks.COBBLESTONE, 0.1F, 1);

        stairBuilder(UOBlocks.FROSTBOUND_COBBLESTONE_STAIRS.get(), Ingredient.of(UOBlocks.FROSTBOUND_COBBLESTONE))
                .unlockedBy("has_frostbound_cobblestone", has(UOBlocks.FROSTBOUND_COBBLESTONE)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_COBBLESTONE_SLAB.get(), UOBlocks.FROSTBOUND_COBBLESTONE);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_COBBLESTONE_WALL.get(), UOBlocks.FROSTBOUND_COBBLESTONE);

        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_COBBLESTONE_STAIRS, UOBlocks.FROSTBOUND_COBBLESTONE, 1);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_COBBLESTONE_SLAB, UOBlocks.FROSTBOUND_COBBLESTONE, 2);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.FROSTBOUND_COBBLESTONE_WALL, UOBlocks.FROSTBOUND_COBBLESTONE, 1);


        // Cryobound Cobblestone
        onePlusOther(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOItems.CRYOSTONE_DUST, UOBlocks.FROSTBOUND_COBBLESTONE, UOBlocks.CRYOBOUND_COBBLESTONE);

        stairBuilder(UOBlocks.CRYOBOUND_COBBLESTONE_STAIRS.get(), Ingredient.of(UOBlocks.CRYOBOUND_COBBLESTONE))
                .unlockedBy("has_cryobound_cobblestone", has(UOBlocks.CRYOBOUND_COBBLESTONE)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.CRYOBOUND_COBBLESTONE_SLAB.get(), UOBlocks.CRYOBOUND_COBBLESTONE);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.CRYOBOUND_COBBLESTONE_WALL.get(), UOBlocks.CRYOBOUND_COBBLESTONE);

        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.CRYOBOUND_COBBLESTONE_STAIRS, UOBlocks.CRYOBOUND_COBBLESTONE, 1);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.CRYOBOUND_COBBLESTONE_SLAB, UOBlocks.CRYOBOUND_COBBLESTONE, 2);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.CRYOBOUND_COBBLESTONE_WALL, UOBlocks.CRYOBOUND_COBBLESTONE, 1);


        // Ice Bricks
        twoByTwo(recipeOutput, UOItems.ICE_BRICK.get(), UOBlocks.ICE_BRICKS, RecipeCategory.BUILDING_BLOCKS);

        stairBuilder(UOBlocks.ICE_BRICK_STAIRS.get(), Ingredient.of(UOBlocks.ICE_BRICKS))
                .unlockedBy("has_ice_bricks", has(UOBlocks.ICE_BRICKS)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.ICE_BRICK_SLAB.get(), UOBlocks.ICE_BRICKS);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.ICE_BRICK_WALL.get(), UOBlocks.ICE_BRICKS);

        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.ICE_BRICK_STAIRS, UOBlocks.ICE_BRICKS, 1);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.ICE_BRICK_SLAB, UOBlocks.ICE_BRICKS, 2);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, UOBlocks.ICE_BRICK_WALL, UOBlocks.ICE_BRICKS, 1);
    }
}