package xun.unoredinary.data.provider;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import xun.unoredinary.UnOredinary;

public abstract class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, UnOredinary.MOD_ID, existingFileHelper);
    }

    protected ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(getItemRegistryName(item),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                UnOredinary.modLoc("item/" + getItemRegistryName(item)));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  UnOredinary.modLoc("block/" + baseBlock.getId().getPath()));
    }

    protected static String getItemRegistryName(DeferredItem<?> deferredItem) {
        return deferredItem.getId().getPath();
    }
}
