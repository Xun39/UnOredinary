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
import xun.unoredinary.content.item.tool.ModTools;
import xun.unoredinary.data.provider.ModRecipeProvider;
import xun.unoredinary.registry.ModBlocks;
import xun.unoredinary.registry.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipes extends ModRecipeProvider {

    public ModRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        // Hemocrylic
        twoByTwoConvertible(recipeOutput, ModItems.HEMOCRYLIC_SHARD, ModItems.HEMOCRYLIC_CRYSTAL, RecipeCategory.MISC, RecipeCategory.MISC);
        threeByThreeConvertible(recipeOutput, ModItems.HEMOCRYLIC_CRYSTAL, ModBlocks.HEMOCRYLIC_BLOCK, RecipeCategory.MISC, RecipeCategory.BUILDING_BLOCKS);

        twoByTwo(recipeOutput, ModItems.ICE_SHARD.get(), Blocks.ICE, RecipeCategory.BUILDING_BLOCKS);

        // Frosteel
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FROSTEEL_INGOT)
                .requires(ModItems.HEMOCRYLIC_CRYSTAL.get(), 4)
                .requires(Items.IRON_INGOT, 4)
                .unlockedBy(getHasName(ModItems.HEMOCRYLIC_CRYSTAL), has(ModItems.HEMOCRYLIC_CRYSTAL))
                .save(recipeOutput);

        threeByThreeConvertible(recipeOutput, ModItems.FROSTEEL_INGOT, ModBlocks.FROSTEEL_BLOCK, RecipeCategory.MISC, RecipeCategory.BUILDING_BLOCKS);

        // Template Upgrade Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE, 2)
                .pattern("#P#")
                .pattern("#I#")
                .pattern("###")
                .define('#', Items.DIAMOND)
                .define('P', ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE)
                .define('I', Blocks.ICE)
                .unlockedBy(getHasName(ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE), has(ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE))
                .save(recipeOutput);

        smithingUpgrade(recipeOutput, Items.IRON_SWORD, ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.FROSTEEL_INGOT.get(),
                ModTools.FROSTEEL_TOOLS.sword().get(), RecipeCategory.COMBAT);
        smithingUpgrade(recipeOutput, Items.IRON_PICKAXE, ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.FROSTEEL_INGOT.get(),
                ModTools.FROSTEEL_TOOLS.pickaxe().get(), RecipeCategory.TOOLS);
        smithingUpgrade(recipeOutput, Items.IRON_AXE, ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.FROSTEEL_INGOT.get(),
                ModTools.FROSTEEL_TOOLS.axe().get(), RecipeCategory.TOOLS);
        smithingUpgrade(recipeOutput, Items.IRON_HOE, ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.FROSTEEL_INGOT.get(),
                ModTools.FROSTEEL_TOOLS.hoe().get(), RecipeCategory.TOOLS);
        smithingUpgrade(recipeOutput, Items.IRON_SHOVEL, ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.FROSTEEL_INGOT.get(),
                ModTools.FROSTEEL_TOOLS.shovel().get(), RecipeCategory.TOOLS);

        smithingUpgrade(recipeOutput, Items.IRON_HELMET, ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.FROSTEEL_INGOT.get(),
                ModItems.FROSTEEL_HELMET.get(), RecipeCategory.COMBAT);
        smithingUpgrade(recipeOutput, Items.IRON_CHESTPLATE, ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.FROSTEEL_INGOT.get(),
                ModItems.FROSTEEL_CHESTPLATE.get(), RecipeCategory.COMBAT);
        smithingUpgrade(recipeOutput, Items.IRON_LEGGINGS, ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.FROSTEEL_INGOT.get(),
                ModItems.FROSTEEL_LEGGINGS.get(), RecipeCategory.COMBAT);
        smithingUpgrade(recipeOutput, Items.IRON_BOOTS, ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.FROSTEEL_INGOT.get(),
                ModItems.FROSTEEL_BOOTS.get(), RecipeCategory.COMBAT);

        // Luminite Ores
        onePlusOther(recipeOutput, RecipeCategory.DECORATIONS, ModItems.LUMINITE_CRYSTAL, Items.STICK, ModItems.LUMINITE_TORCH, 4);
        threeByThreeConvertible(recipeOutput, ModItems.LUMINITE_CRYSTAL, ModBlocks.LUMINITE_BLOCK, RecipeCategory.MISC, RecipeCategory.BUILDING_BLOCKS);

        // Luminthium
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LUMINTHIUM_INGOT)
                .requires(ModItems.LUMINITE_CRYSTAL.get(), 4)
                .requires(Items.COPPER_INGOT, 4)
                .unlockedBy(getHasName(ModItems.LUMINITE_CRYSTAL), has(ModItems.LUMINITE_CRYSTAL))
                .save(recipeOutput);

        threeByThreeConvertible(recipeOutput, ModItems.LUMINTHIUM_INGOT, ModBlocks.LUMINTHIUM_BLOCK, RecipeCategory.MISC, RecipeCategory.BUILDING_BLOCKS);

        sword(recipeOutput, ModItems.LUMINTHIUM_INGOT, ModTools.LUMINTHIUM_TOOLS.sword());
        pickaxe(recipeOutput, ModItems.LUMINTHIUM_INGOT, ModTools.LUMINTHIUM_TOOLS.pickaxe());
        axe(recipeOutput, ModItems.LUMINTHIUM_INGOT, ModTools.LUMINTHIUM_TOOLS.axe());
        hoe(recipeOutput, ModItems.LUMINTHIUM_INGOT, ModTools.LUMINTHIUM_TOOLS.hoe());
        shovel(recipeOutput, ModItems.LUMINTHIUM_INGOT, ModTools.LUMINTHIUM_TOOLS.shovel());

        helmet(recipeOutput, ModItems.LUMINTHIUM_INGOT, ModItems.LUMINTHIUM_HELMET);
        chestplate(recipeOutput, ModItems.LUMINTHIUM_INGOT, ModItems.LUMINTHIUM_CHESTPLATE);
        leggings(recipeOutput, ModItems.LUMINTHIUM_INGOT, ModItems.LUMINTHIUM_LEGGINGS);
        boots(recipeOutput, ModItems.LUMINTHIUM_INGOT, ModItems.LUMINTHIUM_BOOTS);

        // Solarite
        sword(recipeOutput, ModItems.SOLARITE_GEM, ModTools.SOLARITE_TOOLS.sword());
        pickaxe(recipeOutput, ModItems.SOLARITE_GEM, ModTools.SOLARITE_TOOLS.pickaxe());
        axe(recipeOutput, ModItems.SOLARITE_GEM, ModTools.SOLARITE_TOOLS.axe());
        hoe(recipeOutput, ModItems.SOLARITE_GEM, ModTools.SOLARITE_TOOLS.hoe());
        shovel(recipeOutput, ModItems.SOLARITE_GEM, ModTools.SOLARITE_TOOLS.shovel());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SOLARITE_HELMET)
                .pattern("-#-").pattern("! !")
                .define('#', ModItems.SOLARITE_GEM)
                .define('-', Items.IRON_INGOT)
                .define('!', Blocks.STONE)
                .unlockedBy(getHasName(ModItems.SOLARITE_GEM.get()), has(ModItems.SOLARITE_GEM.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SOLARITE_CHESTPLATE)
                .pattern("! !").pattern("-#-").pattern("-!-")
                .define('#', ModItems.SOLARITE_GEM)
                .define('-', Items.IRON_INGOT)
                .define('!', Blocks.STONE)
                .unlockedBy(getHasName(ModItems.SOLARITE_GEM.get()), has(ModItems.SOLARITE_GEM.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SOLARITE_LEGGINGS)
                .pattern("-#-").pattern("! !").pattern("! !")
                .define('#', ModItems.SOLARITE_GEM)
                .define('-', Items.IRON_INGOT)
                .define('!', Blocks.STONE)
                .unlockedBy(getHasName(ModItems.SOLARITE_GEM.get()), has(ModItems.SOLARITE_GEM.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SOLARITE_BOOTS)
                .pattern("# #").pattern("! !")
                .define('#', ModItems.SOLARITE_GEM)
                .define('!', Blocks.STONE)
                .unlockedBy(getHasName(ModItems.SOLARITE_GEM.get()), has(ModItems.SOLARITE_GEM.get()))
                .save(recipeOutput);

        // Ruby Ores
        threeByThreeConvertible(recipeOutput, ModItems.RUBY, ModBlocks.RUBY_BLOCK, RecipeCategory.MISC, RecipeCategory.BUILDING_BLOCKS);

        sword(recipeOutput, ModItems.RUBY, ModTools.RUBY_TOOLS.sword());
        pickaxe(recipeOutput, ModItems.RUBY, ModTools.RUBY_TOOLS.pickaxe());
        axe(recipeOutput, ModItems.RUBY, ModTools.RUBY_TOOLS.axe());
        hoe(recipeOutput, ModItems.RUBY, ModTools.RUBY_TOOLS.hoe());
        shovel(recipeOutput, ModItems.RUBY, ModTools.RUBY_TOOLS.shovel());

        helmet(recipeOutput, ModItems.RUBY, ModItems.RUBY_HELMET);
        chestplate(recipeOutput, ModItems.RUBY, ModItems.RUBY_CHESTPLATE);
        leggings(recipeOutput, ModItems.RUBY, ModItems.RUBY_LEGGINGS);
        boots(recipeOutput, ModItems.RUBY, ModItems.RUBY_BOOTS);

        // Sapphire

        sword(recipeOutput, ModItems.SAPPHIRE, ModTools.SAPPHIRE_TOOLS.sword());
        pickaxe(recipeOutput, ModItems.SAPPHIRE, ModTools.SAPPHIRE_TOOLS.pickaxe());
        axe(recipeOutput, ModItems.SAPPHIRE, ModTools.SAPPHIRE_TOOLS.axe());
        hoe(recipeOutput, ModItems.SAPPHIRE, ModTools.SAPPHIRE_TOOLS.hoe());
        shovel(recipeOutput, ModItems.SAPPHIRE, ModTools.SAPPHIRE_TOOLS.shovel());


        // Frostbound Stone
        onePlusOther(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModItems.CRYOSTONE_DUST, Blocks.STONE, ModBlocks.FROSTBOUND_STONE);
        smelting(ModBlocks.FROSTBOUND_STONE, Blocks.STONE, 0.1F, 1);

        stairBuilder(ModBlocks.FROSTBOUND_STONE_STAIRS.get(), Ingredient.of(ModBlocks.FROSTBOUND_STONE))
                .unlockedBy("has_frostbound_stone", has(ModBlocks.FROSTBOUND_STONE)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_STONE_SLAB.get(), ModBlocks.FROSTBOUND_STONE);

        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_STONE_STAIRS, ModBlocks.FROSTBOUND_STONE, 1);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_STONE_SLAB, ModBlocks.FROSTBOUND_STONE, 2);


        twoByTwo(recipeOutput, ModBlocks.FROSTBOUND_STONE, ModBlocks.FROSTBOUND_STONE_BRICKS, RecipeCategory.BUILDING_BLOCKS);
        smelting(ModBlocks.FROSTBOUND_STONE_BRICKS, Blocks.STONE_BRICKS, 0.1F, 1);


        // Frostbound Stone Bricks
        onePlusOther(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModItems.CRYOSTONE_DUST, Blocks.STONE_BRICKS, ModBlocks.FROSTBOUND_STONE_BRICKS);

        stairBuilder(ModBlocks.FROSTBOUND_STONE_BRICK_STAIRS.get(), Ingredient.of(ModBlocks.FROSTBOUND_STONE))
                .unlockedBy("has_frostbound_stone_bricks", has(ModBlocks.FROSTBOUND_STONE)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_STONE_BRICK_SLAB.get(), ModBlocks.FROSTBOUND_STONE_BRICKS);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_STONE_BRICK_WALL.get(), ModBlocks.FROSTBOUND_STONE_BRICKS);

        // Stone cutting from Stone Bricks
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_STONE_BRICK_STAIRS, ModBlocks.FROSTBOUND_STONE_BRICKS, 1);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_STONE_BRICK_SLAB, ModBlocks.FROSTBOUND_STONE_BRICKS, 2);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_STONE_BRICK_WALL, ModBlocks.FROSTBOUND_STONE_BRICKS, 1);
        // Stone cutting from Stone
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_STONE_BRICK_STAIRS, ModBlocks.FROSTBOUND_STONE, 1);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_STONE_BRICK_SLAB, ModBlocks.FROSTBOUND_STONE, 2);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_STONE_BRICK_WALL, ModBlocks.FROSTBOUND_STONE, 1);


        // Frostbound Cobblestone
        onePlusOther(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModItems.CRYOSTONE_DUST, Blocks.COBBLESTONE, ModBlocks.FROSTBOUND_COBBLESTONE);

        smelting(ModBlocks.FROSTBOUND_COBBLESTONE, Blocks.COBBLESTONE, 0.1F, 1);

        stairBuilder(ModBlocks.FROSTBOUND_COBBLESTONE_STAIRS.get(), Ingredient.of(ModBlocks.FROSTBOUND_COBBLESTONE))
                .unlockedBy("has_frostbound_cobblestone", has(ModBlocks.FROSTBOUND_COBBLESTONE)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_COBBLESTONE_SLAB.get(), ModBlocks.FROSTBOUND_COBBLESTONE);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_COBBLESTONE_WALL.get(), ModBlocks.FROSTBOUND_COBBLESTONE);

        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_COBBLESTONE_STAIRS, ModBlocks.FROSTBOUND_COBBLESTONE, 1);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_COBBLESTONE_SLAB, ModBlocks.FROSTBOUND_COBBLESTONE, 2);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FROSTBOUND_COBBLESTONE_WALL, ModBlocks.FROSTBOUND_COBBLESTONE, 1);


        // Cryobound Cobblestone
        onePlusOther(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModItems.CRYOSTONE_DUST, ModBlocks.FROSTBOUND_COBBLESTONE, ModBlocks.CRYOBOUND_COBBLESTONE);

        stairBuilder(ModBlocks.CRYOBOUND_COBBLESTONE_STAIRS.get(), Ingredient.of(ModBlocks.CRYOBOUND_COBBLESTONE))
                .unlockedBy("has_cryobound_cobblestone", has(ModBlocks.CRYOBOUND_COBBLESTONE)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRYOBOUND_COBBLESTONE_SLAB.get(), ModBlocks.CRYOBOUND_COBBLESTONE);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRYOBOUND_COBBLESTONE_WALL.get(), ModBlocks.CRYOBOUND_COBBLESTONE);

        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRYOBOUND_COBBLESTONE_STAIRS, ModBlocks.CRYOBOUND_COBBLESTONE, 1);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRYOBOUND_COBBLESTONE_SLAB, ModBlocks.CRYOBOUND_COBBLESTONE, 2);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRYOBOUND_COBBLESTONE_WALL, ModBlocks.CRYOBOUND_COBBLESTONE, 1);


        // Ice Bricks
        twoByTwo(recipeOutput, ModItems.ICE_BRICK.get(), ModBlocks.ICE_BRICKS, RecipeCategory.BUILDING_BLOCKS);

        stairBuilder(ModBlocks.ICE_BRICK_STAIRS.get(), Ingredient.of(ModBlocks.ICE_BRICKS))
                .unlockedBy("has_ice_bricks", has(ModBlocks.ICE_BRICKS)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_BRICK_SLAB.get(), ModBlocks.ICE_BRICKS);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_BRICK_WALL.get(), ModBlocks.ICE_BRICKS);

        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_BRICK_STAIRS, ModBlocks.ICE_BRICKS, 1);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_BRICK_SLAB, ModBlocks.ICE_BRICKS, 2);
        stoneCuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_BRICK_WALL, ModBlocks.ICE_BRICKS, 1);
    }
}