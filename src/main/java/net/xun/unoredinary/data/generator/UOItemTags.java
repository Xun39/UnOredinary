package net.xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.xun.lib.common.api.item.armor.ArmorSet;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.registry.UOArmors;
import net.xun.unoredinary.registry.UOItems;
import net.xun.unoredinary.registry.UOTools;
import net.xun.unoredinary.utils.UOTags;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class UOItemTags extends ItemTagsProvider {
    public UOItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, UnOredinary.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        // Tools
        addToTags(
                Set.of(ItemTags.SWORDS, Tags.Items.MELEE_WEAPON_TOOLS),
                UOTools.getTools().stream().map(toolSet -> (Item) toolSet.getSword().get()).toList());
        addToTags(
                Set.of(ItemTags.AXES, Tags.Items.MELEE_WEAPON_TOOLS),
                UOTools.getTools().stream().map(toolSet -> (Item) toolSet.getAxe().get()).toList());
        addToTags(
                Set.of(ItemTags.PICKAXES, Tags.Items.MINING_TOOL_TOOLS),
                UOTools.getTools().stream().map(toolSet -> (Item) toolSet.getPickaxe().get()).toList());
        addToTags(
                Set.of(ItemTags.HOES),
                UOTools.getTools().stream().map(toolSet -> (Item) toolSet.getHoe().get()).toList());
        addToTags(
                Set.of(ItemTags.SHOVELS),
                UOTools.getTools().stream().map(toolSet -> (Item) toolSet.getShovel().get()).toList());

        // Armors
        addToTags(
                Set.of(ItemTags.HEAD_ARMOR),
                UOArmors.getArmors().stream().map(armorSet -> (Item) armorSet.getHelmet().get()).toList());
        addToTags(
                Set.of(ItemTags.CHEST_ARMOR),
                UOArmors.getArmors().stream().map(armorSet -> (Item) armorSet.getChestplate().get()).toList());
        addToTags(
                Set.of(ItemTags.LEG_ARMOR),
                UOArmors.getArmors().stream().map(armorSet -> (Item) armorSet.getLeggings().get()).toList());
        addToTags(
                Set.of(ItemTags.FOOT_ARMOR),
                UOArmors.getArmors().stream().map(armorSet -> (Item) armorSet.getBoots().get()).toList());

        // cTags: Gems
        addToTags(Set.of(Tags.Items.GEMS, UOTags.Items.GEMS_GLACIUM), Set.of(UOItems.GLACIUM_CRYSTAL.get()));

        // cTags: Nuggets
        addToTags(Set.of(Tags.Items.NUGGETS, UOTags.Items.NUGGETS_GLACIUM), Set.of(UOItems.GLACIUM_SHARDS.get()));

        // cTags: Ingots
        addToTags(Set.of(Tags.Items.INGOTS, UOTags.Items.INGOTS_FROSTSTEEL), Set.of(UOItems.FROSTSTEEL_INGOT.get()));
        addToTags(Set.of(Tags.Items.INGOTS, UOTags.Items.INGOTS_CRYOSTEEL), Set.of(UOItems.CRYOSTEEL_INGOT.get()));
    }

    private void addToTags(Collection<TagKey<Item>> tags, Collection<Item> entries) {
        for (TagKey<Item> key : tags) {
            tag(key).add(entries.toArray(Item[]::new));
        }
    }
}
