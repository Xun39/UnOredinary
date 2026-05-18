package net.xun.unoredinary.data.generator;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.data.provider.UOItemModelProvider;
import net.xun.unoredinary.registry.UOArmors;
import net.xun.unoredinary.registry.UOBlocks;
import net.xun.unoredinary.registry.UOItems;
import net.xun.unoredinary.registry.UOTools;

public class UOItemModels extends UOItemModelProvider {
    public UOItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        basicItem(UOItems.CRYIC_POWDER.get());

        basicItem(UOItems.SAPPHIRE.get());
        toolSetModels(UOTools.SAPPHIRE);
        armorSetModels(UOArmors.SAPPHIRE);

        basicItem(UOItems.NETHER_RUBY.get());
        toolSetModels(UOTools.RUBY);
        armorSetModels(UOArmors.RUBY);

        basicItem(UOItems.GLACIUM_SHARD.get());
        basicItem(UOItems.GLACIUM_CRYSTAL.get());

        basicItem(UOItems.FROSTSTEEL_INGOT.get());
        basicItem(UOItems.FROSTSTEEL_NUGGET.get());
        toolSetModels(UOTools.FROSTSTEEL);
        armorSetModels(UOArmors.FROSTSTEEL);

        basicItem(UOItems.GLACIALITE_INGOT.get());
        toolSetModels(UOTools.GLACIALITE);
        armorSetModels(UOArmors.GLACIALITE);

        basicItem(UOItems.GLACIALITE_UPGRADE_SMITHING_TEMPLATE.get());

        basicItem(UOItems.LUMINITE_CRYSTAL.get());

        basicItem(UOItems.LUMINIUM_INGOT.get());
        basicItem(UOItems.LUMINIUM_NUGGET.get());
        toolSetModels(UOTools.LUMINIUM);
        armorSetModels(UOArmors.LUMINIUM);

        basicItem(UOItems.FROST_KEY.get());

        withExistingParent(BuiltInRegistries.ITEM.getKey(UOItems.FROST_ZOMBIE_SPAWN_EGG.get()).getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(BuiltInRegistries.ITEM.getKey(UOItems.FROST_REVENANT_SPAWN_EGG.get()).getPath(), mcLoc("item/template_spawn_egg"));

        // Blocks
        basicItem(UOBlocks.ICE_DOOR.asItem());
    }
}