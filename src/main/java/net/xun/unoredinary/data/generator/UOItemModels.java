package net.xun.unoredinary.data.generator;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
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
        basicItem(UOItems.GLACIUM_SHARDS.get());
        basicItem(UOItems.GLACIUM_CRYSTAL.get());

        basicItem(UOItems.FROSTSTEEL_INGOT.get());
        toolSetModels(UOTools.FROSTSTEEL);
        armorSetModels(UOArmors.FROSTSTEEL);
    }
}