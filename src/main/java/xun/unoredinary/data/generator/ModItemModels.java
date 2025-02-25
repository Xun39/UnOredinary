package xun.unoredinary.data.generator;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import xun.unoredinary.UnOredinary;
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

        basicItem(ModItems.LUMINITE_CRYSTAL.get());

        basicItem(ModItems.RUBY.get());

        basicItem(ModItems.ICE_SHARD.get());
        basicItem(ModItems.ICE_BRICK.get());

        basicItem(ModItems.FROSTBITTEN_PHALANGES.get());

        wallItem(ModBlocks.FROSTBOUND_COBBLESTONE_WALL, ModBlocks.FROSTBOUND_COBBLESTONE);
        wallItem(ModBlocks.CRYOBOUND_COBBLESTONE_WALL, ModBlocks.CRYOBOUND_COBBLESTONE);
        wallItem(ModBlocks.FROSTBOUND_STONE_BRICK_WALL, ModBlocks.FROSTBOUND_STONE_BRICKS);
        wallItem(ModBlocks.ICE_BRICK_WALL, ModBlocks.ICE_BRICKS);

        basicItem(ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get());

        basicItem(ModItems.FROSTEEL_INGOT.get());

        handheldItem(ModItems.FROSTEEL_SWORD);
        handheldItem(ModItems.FROSTEEL_PICKAXE);
        handheldItem(ModItems.FROSTEEL_AXE);
        handheldItem(ModItems.FROSTEEL_HOE);
        handheldItem(ModItems.FROSTEEL_SHOVEL);

        handheldItem(ModItems.RUBY_SWORD);
        handheldItem(ModItems.RUBY_PICKAXE);
        handheldItem(ModItems.RUBY_AXE);
        handheldItem(ModItems.RUBY_HOE);
        handheldItem(ModItems.RUBY_SHOVEL);

        trimmedArmorItem(ModItems.FROSTEEL_HELMET);
        trimmedArmorItem(ModItems.FROSTEEL_CHESTPLATE);
        trimmedArmorItem(ModItems.FROSTEEL_LEGGINGS);
        trimmedArmorItem(ModItems.FROSTEEL_BOOTS);

        trimmedArmorItem(ModItems.RUBY_HELMET);
        trimmedArmorItem(ModItems.RUBY_CHESTPLATE);
        trimmedArmorItem(ModItems.RUBY_LEGGINGS);
        trimmedArmorItem(ModItems.RUBY_BOOTS);

        withExistingParent(getItemRegistryName(ModItems.LUMINITE_TORCH), mcLoc("item/generated"))
                .texture("layer0",  UnOredinary.modLoc("block/" + getBlockRegistryName(ModBlocks.LUMINITE_TORCH)));

        withExistingParent(getItemRegistryName(ModItems.FROST_ZOMBIE_SPAWN_EGG), mcLoc("item/template_spawn_egg"));
    }
}
