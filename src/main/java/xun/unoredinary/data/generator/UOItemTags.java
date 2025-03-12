package xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.content.item.armor.UOArmors;
import xun.unoredinary.content.item.tool.ToolSet;
import xun.unoredinary.content.item.tool.UOTools;
import xun.unoredinary.registry.UOItems;
import xun.unoredinary.util.UOTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UOItemTags extends ItemTagsProvider {

    public UOItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, UnOredinary.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        UOTools.getTools().forEach(set -> {
            tag(ItemTags.SWORDS).add(set.sword().get());
            tag(ItemTags.PICKAXES).add(set.pickaxe().get());
            tag(ItemTags.AXES).add(set.axe().get());
            tag(ItemTags.HOES).add(set.hoe().get());
            tag(ItemTags.SHOVELS).add(set.shovel().get());
        });

        UOArmors.getArmors().forEach(set -> {
            tag(ItemTags.HEAD_ARMOR).add(set.helmet().get());
            tag(ItemTags.CHEST_ARMOR).add(set.chestplate().get());
            tag(ItemTags.LEG_ARMOR).add(set.leggings().get());
            tag(ItemTags.FOOT_ARMOR).add(set.boots().get());
            addToTag(ItemTags.TRIMMABLE_ARMOR, set.get());
        });

        addToTag(ItemTags.FREEZE_IMMUNE_WEARABLES, UOArmors.FROSTEEL.get());
        addToTag(ItemTags.FREEZE_IMMUNE_WEARABLES, UOArmors.SOLARITE.get());

        addToTag(UOTags.Items.FROSTEEL_TOOL, UOTools.FROSTEEL.get());
        addToTag(UOTags.Items.SOLARITE_TOOL, UOTools.SOLARITE.get());

        tag(ItemTags.TRIM_TEMPLATES).add(
                UOItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get()
        );

        tag(ItemTags.TRIM_MATERIALS).add(
                UOItems.HEMOCRYLIC_CRYSTAL.get(),
                UOItems.FROSTEEL_INGOT.get(),
                UOItems.CRYOSTONE_DUST.get(),
                UOItems.LUMINITE_CRYSTAL.get(),
                UOItems.LUMINTHIUM_INGOT.get(),
                UOItems.RUBY.get()
        );
    }

    private void addToTag(TagKey<Item> tag, List<Item> items) {
        for (ItemLike item : items) {
            tag(tag).add(item.asItem());
        }
    }
}
