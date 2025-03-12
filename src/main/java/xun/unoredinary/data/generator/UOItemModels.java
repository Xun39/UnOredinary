package xun.unoredinary.data.generator;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.content.item.armor.UOArmors;
import xun.unoredinary.content.item.tool.UOTools;
import xun.unoredinary.data.provider.UOItemModelProvider;
import xun.unoredinary.registry.UOBlocks;
import xun.unoredinary.registry.UOItems;

public class UOItemModels extends UOItemModelProvider {

    public UOItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        basicItem(UOItems.HEMOCRYLIC_SHARD.get());
        basicItem(UOItems.HEMOCRYLIC_CRYSTAL.get());

        basicItem(UOItems.CRYOSTONE_DUST.get());
        withExistingParent(getItemRegistryName(UOItems.CRYOSTONE_TORCH), mcLoc("item/generated"))
                .texture("layer0",  UnOredinary.modLoc("block/" + getBlockRegistryName(UOBlocks.CRYOSTONE_TORCH)));

        basicItem(UOItems.LUMINITE_CRYSTAL.get());
        withExistingParent(getItemRegistryName(UOItems.LUMINITE_TORCH), mcLoc("item/generated"))
                .texture("layer0",  UnOredinary.modLoc("block/" + getBlockRegistryName(UOBlocks.LUMINITE_TORCH)));

        basicItem(UOItems.RUBY.get());
        basicItem(UOItems.SAPPHIRE.get());

        basicItem(UOItems.SOLARITE_GEM.get());

        basicItem(UOItems.ICE_SHARD.get());
        basicItem(UOItems.ICE_BRICK.get());

        basicItem(UOItems.FROSTBITTEN_PHALANGES.get());

        wallItem(UOBlocks.FROSTBOUND_COBBLESTONE_WALL, UOBlocks.FROSTBOUND_COBBLESTONE);
        wallItem(UOBlocks.CRYOBOUND_COBBLESTONE_WALL, UOBlocks.CRYOBOUND_COBBLESTONE);
        wallItem(UOBlocks.FROSTBOUND_STONE_BRICK_WALL, UOBlocks.FROSTBOUND_STONE_BRICKS);
        wallItem(UOBlocks.ICE_BRICK_WALL, UOBlocks.ICE_BRICKS);

        basicItem(UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get());

        basicItem(UOItems.FROSTEEL_INGOT.get());
        basicItem(UOItems.LUMINTHIUM_INGOT.get());

        toolItemSet(UOTools.FROSTEEL);
        toolItemSet(UOTools.SOLARITE);
        toolItemSet(UOTools.LUMINTHIUM);
        toolItemSet(UOTools.RUBY);
        toolItemSet(UOTools.SAPPHIRE);

        trimmedArmorItemSet(UOArmors.FROSTEEL);
        trimmedArmorItemSet(UOArmors.SOLARITE);
        trimmedArmorItemSet(UOArmors.LUMINTHIUM);
        trimmedArmorItemSet(UOArmors.RUBY);
        trimmedArmorItemSet(UOArmors.SAPPHIRE);

        basicItem(UOItems.RUBY_HORSE_ARMOR.get());

        withExistingParent(getItemRegistryName(UOItems.FROST_ZOMBIE_SPAWN_EGG), mcLoc("item/template_spawn_egg"));
    }
}
