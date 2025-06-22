package net.xun.unoredinary.data.generator;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.data.provider.UOItemModelProvider;
import net.xun.unoredinary.registry.UOArmors;
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

        basicItem(UOItems.GLACIUM_SHARD.get());
        basicItem(UOItems.GLACIUM_CRYSTAL.get());

        basicItem(UOItems.LUMINITE_CRYSTAL.get());

        basicItem(UOItems.FROSTSTEEL_INGOT.get());
        toolSetModels(UOTools.FROSTSTEEL);
        armorSetModels(UOArmors.FROSTSTEEL);

        basicItem(UOItems.GLACIALITE_INGOT.get());
        toolSetModels(UOTools.GLACIALITE);
        armorSetModels(UOArmors.GLACIALITE);

        basicItem(UOItems.GLACIALITE_UPGRADE_SMITHING_TEMPLATE.get());

        withExistingParent(CommonUtils.getRegistryID(UOItems.FROST_ZOMBIE_SPAWN_EGG.asItem()), mcLoc("item/template_spawn_egg"));
    }
}