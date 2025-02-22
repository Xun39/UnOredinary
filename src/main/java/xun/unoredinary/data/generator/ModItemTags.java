package xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.registry.ModItems;
import xun.unoredinary.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTags extends ItemTagsProvider {

    public ModItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, UnOredinary.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(ItemTags.SWORDS).add(
                ModItems.FROSTEEL_SWORD.get()
        );

        tag(ItemTags.PICKAXES).add(
                ModItems.FROSTEEL_PICKAXE.get()
        );

        tag(ItemTags.AXES).add(
                ModItems.FROSTEEL_AXE.get()
        );

        tag(ItemTags.HOES).add(
                ModItems.FROSTEEL_HOE.get()
        );

        tag(ItemTags.SHOVELS).add(
                ModItems.FROSTEEL_SHOVEL.get()
        );

        tag(ItemTags.TRIM_TEMPLATES).add(
                ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get()
        );

        tag(ItemTags.TRIMMABLE_ARMOR).add(
                ModItems.FROSTEEL_HELMET.get(),
                ModItems.FROSTEEL_CHESTPLATE.get(),
                ModItems.FROSTEEL_LEGGINGS.get(),
                ModItems.FROSTEEL_BOOTS.get()
        );

        tag(ModTags.Items.FROSTEEL_TOOL).add(
                ModItems.FROSTEEL_SWORD.get(),
                ModItems.FROSTEEL_PICKAXE.get(),
                ModItems.FROSTEEL_AXE.get(),
                ModItems.FROSTEEL_HOE.get(),
                ModItems.FROSTEEL_SHOVEL.get()
        );
    }
}
