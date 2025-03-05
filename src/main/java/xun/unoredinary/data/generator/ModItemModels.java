package xun.unoredinary.data.generator;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.content.item.tool.ModTools;
import xun.unoredinary.data.provider.ModItemModelProvider;
import xun.unoredinary.registry.ModBlocks;
import xun.unoredinary.registry.ModItems;

public class ModItemModels extends ModItemModelProvider {

    public ModItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        basicItem(ModItems.HEMOCRYLIC_SHARD.get());
        basicItem(ModItems.HEMOCRYLIC_CRYSTAL.get());

        basicItem(ModItems.CRYOSTONE_DUST.get());
        withExistingParent(getItemRegistryName(ModItems.CRYOSTONE_TORCH), mcLoc("item/generated"))
                .texture("layer0",  UnOredinary.modLoc("block/" + getBlockRegistryName(ModBlocks.CRYOSTONE_TORCH)));

        basicItem(ModItems.LUMINITE_CRYSTAL.get());
        withExistingParent(getItemRegistryName(ModItems.LUMINITE_TORCH), mcLoc("item/generated"))
                .texture("layer0",  UnOredinary.modLoc("block/" + getBlockRegistryName(ModBlocks.LUMINITE_TORCH)));

        basicItem(ModItems.RUBY.get());
        basicItem(ModItems.SAPPHIRE.get());

        basicItem(ModItems.SOLARITE_GEM.get());

        basicItem(ModItems.ICE_SHARD.get());
        basicItem(ModItems.ICE_BRICK.get());

        basicItem(ModItems.FROSTBITTEN_PHALANGES.get());

        wallItem(ModBlocks.FROSTBOUND_COBBLESTONE_WALL, ModBlocks.FROSTBOUND_COBBLESTONE);
        wallItem(ModBlocks.CRYOBOUND_COBBLESTONE_WALL, ModBlocks.CRYOBOUND_COBBLESTONE);
        wallItem(ModBlocks.FROSTBOUND_STONE_BRICK_WALL, ModBlocks.FROSTBOUND_STONE_BRICKS);
        wallItem(ModBlocks.ICE_BRICK_WALL, ModBlocks.ICE_BRICKS);

        basicItem(ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get());

        basicItem(ModItems.FROSTEEL_INGOT.get());
        basicItem(ModItems.LUMINTHIUM_INGOT.get());

        handheldItem(ModTools.FROSTEEL_TOOLS.sword());
        handheldItem(ModTools.FROSTEEL_TOOLS.pickaxe());
        handheldItem(ModTools.FROSTEEL_TOOLS.axe());
        handheldItem(ModTools.FROSTEEL_TOOLS.hoe());
        handheldItem(ModTools.FROSTEEL_TOOLS.shovel());

        handheldItem(ModTools.LUMINTHIUM_TOOLS.sword());
        handheldItem(ModTools.LUMINTHIUM_TOOLS.pickaxe());
        handheldItem(ModTools.LUMINTHIUM_TOOLS.axe());
        handheldItem(ModTools.LUMINTHIUM_TOOLS.hoe());
        handheldItem(ModTools.LUMINTHIUM_TOOLS.shovel());

        handheldItem(ModTools.SOLARITE_TOOLS.sword());
        handheldItem(ModTools.SOLARITE_TOOLS.pickaxe());
        handheldItem(ModTools.SOLARITE_TOOLS.axe());
        handheldItem(ModTools.SOLARITE_TOOLS.hoe());
        handheldItem(ModTools.SOLARITE_TOOLS.shovel());

        handheldItem(ModTools.RUBY_TOOLS.sword());
        handheldItem(ModTools.RUBY_TOOLS.pickaxe());
        handheldItem(ModTools.RUBY_TOOLS.axe());
        handheldItem(ModTools.RUBY_TOOLS.hoe());
        handheldItem(ModTools.RUBY_TOOLS.shovel());

        handheldItem(ModTools.SAPPHIRE_TOOLS.sword());
        handheldItem(ModTools.SAPPHIRE_TOOLS.pickaxe());
        handheldItem(ModTools.SAPPHIRE_TOOLS.axe());
        handheldItem(ModTools.SAPPHIRE_TOOLS.hoe());
        handheldItem(ModTools.SAPPHIRE_TOOLS.shovel());

        trimmedArmorItem(ModItems.FROSTEEL_HELMET);
        trimmedArmorItem(ModItems.FROSTEEL_CHESTPLATE);
        trimmedArmorItem(ModItems.FROSTEEL_LEGGINGS);
        trimmedArmorItem(ModItems.FROSTEEL_BOOTS);

        trimmedArmorItem(ModItems.LUMINTHIUM_HELMET);
        trimmedArmorItem(ModItems.LUMINTHIUM_CHESTPLATE);
        trimmedArmorItem(ModItems.LUMINTHIUM_LEGGINGS);
        trimmedArmorItem(ModItems.LUMINTHIUM_BOOTS);

        trimmedArmorItem(ModItems.SOLARITE_HELMET);
        trimmedArmorItem(ModItems.SOLARITE_CHESTPLATE);
        trimmedArmorItem(ModItems.SOLARITE_LEGGINGS);
        trimmedArmorItem(ModItems.SOLARITE_BOOTS);

        trimmedArmorItem(ModItems.RUBY_HELMET);
        trimmedArmorItem(ModItems.RUBY_CHESTPLATE);
        trimmedArmorItem(ModItems.RUBY_LEGGINGS);
        trimmedArmorItem(ModItems.RUBY_BOOTS);
        basicItem(ModItems.RUBY_HORSE_ARMOR.get());

        /*trimmedArmorItem(ModItems.SAPPHIRE_ARMORS.helmet());
        trimmedArmorItem(ModItems.SAPPHIRE_ARMORS.chestplate());
        trimmedArmorItem(ModItems.SAPPHIRE_ARMORS.leggings());
        trimmedArmorItem(ModItems.SAPPHIRE_ARMORS.boots());*/


        withExistingParent(getItemRegistryName(ModItems.FROST_ZOMBIE_SPAWN_EGG), mcLoc("item/template_spawn_egg"));
    }
}
