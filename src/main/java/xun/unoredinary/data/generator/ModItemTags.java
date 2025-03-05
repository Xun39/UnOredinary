package xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.content.item.tool.ModTools;
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
                ModTools.FROSTEEL_TOOLS.sword().get(),
                ModTools.LUMINTHIUM_TOOLS.sword().get(),
                ModTools.SOLARITE_TOOLS.sword().get(),
                ModTools.RUBY_TOOLS.sword().get(),
                ModTools.SAPPHIRE_TOOLS.sword().get()
        );
        tag(ItemTags.PICKAXES).add(
                ModTools.FROSTEEL_TOOLS.pickaxe().get(),
                ModTools.LUMINTHIUM_TOOLS.pickaxe().get(),
                ModTools.SOLARITE_TOOLS.pickaxe().get(),
                ModTools.RUBY_TOOLS.pickaxe().get(),
                ModTools.SAPPHIRE_TOOLS.pickaxe().get()
        );
        tag(ItemTags.AXES).add(
                ModTools.FROSTEEL_TOOLS.axe().get(),
                ModTools.LUMINTHIUM_TOOLS.axe().get(),
                ModTools.SOLARITE_TOOLS.axe().get(),
                ModTools.RUBY_TOOLS.axe().get(),
                ModTools.SAPPHIRE_TOOLS.axe().get()
        );
        tag(ItemTags.HOES).add(
                ModTools.FROSTEEL_TOOLS.hoe().get(),
                ModTools.LUMINTHIUM_TOOLS.hoe().get(),
                ModTools.SOLARITE_TOOLS.hoe().get(),
                ModTools.RUBY_TOOLS.hoe().get(),
                ModTools.SAPPHIRE_TOOLS.hoe().get()
        );
        tag(ItemTags.SHOVELS).add(
                ModTools.FROSTEEL_TOOLS.shovel().get(),
                ModTools.LUMINTHIUM_TOOLS.shovel().get(),
                ModTools.SOLARITE_TOOLS.shovel().get(),
                ModTools.RUBY_TOOLS.shovel().get(),
                ModTools.SAPPHIRE_TOOLS.shovel().get()
        );

        tag(ItemTags.TRIM_TEMPLATES).add(
                ModItems.FROSTEEL_UPGRADE_SMITHING_TEMPLATE.get()
        );

        tag(ItemTags.TRIM_MATERIALS).add(
                ModItems.HEMOCRYLIC_CRYSTAL.get(),
                ModItems.FROSTEEL_INGOT.get(),
                ModItems.CRYOSTONE_DUST.get(),
                ModItems.LUMINITE_CRYSTAL.get(),
                ModItems.LUMINTHIUM_INGOT.get(),
                ModItems.RUBY.get()
        );

        tag(ItemTags.HEAD_ARMOR).add(
                ModItems.FROSTEEL_HELMET.get(),
                ModItems.LUMINTHIUM_HELMET.get(),
                ModItems.SOLARITE_HELMET.get(),
                ModItems.RUBY_HELMET.get()
                //ModItems.SAPPHIRE_ARMORS.helmet().get()
        );
        tag(ItemTags.CHEST_ARMOR).add(
                ModItems.FROSTEEL_CHESTPLATE.get(),
                ModItems.LUMINTHIUM_CHESTPLATE.get(),
                ModItems.SOLARITE_CHESTPLATE.get(),
                ModItems.RUBY_CHESTPLATE.get()
                //ModItems.SAPPHIRE_ARMORS.chestplate().get()
        );
        tag(ItemTags.LEG_ARMOR).add(
                ModItems.FROSTEEL_LEGGINGS.get(),
                ModItems.LUMINTHIUM_LEGGINGS.get(),
                ModItems.SOLARITE_LEGGINGS.get(),
                ModItems.RUBY_LEGGINGS.get()
                //ModItems.SAPPHIRE_ARMORS.leggings().get()
        );
        tag(ItemTags.FOOT_ARMOR).add(
                ModItems.FROSTEEL_BOOTS.get(),
                ModItems.LUMINTHIUM_BOOTS.get(),
                ModItems.SOLARITE_BOOTS.get(),
                ModItems.RUBY_BOOTS.get()
                //ModItems.SAPPHIRE_ARMORS.boots().get()
        );

        tag(ItemTags.FREEZE_IMMUNE_WEARABLES).add(
                ModItems.FROSTEEL_HELMET.get(),
                ModItems.FROSTEEL_CHESTPLATE.get(),
                ModItems.FROSTEEL_LEGGINGS.get(),
                ModItems.FROSTEEL_BOOTS.get(),

                ModItems.SOLARITE_HELMET.get(),
                ModItems.SOLARITE_CHESTPLATE.get(),
                ModItems.SOLARITE_LEGGINGS.get(),
                ModItems.SOLARITE_BOOTS.get()
        );

        tag(ItemTags.TRIMMABLE_ARMOR).add(
                ModItems.FROSTEEL_HELMET.get(),
                ModItems.FROSTEEL_CHESTPLATE.get(),
                ModItems.FROSTEEL_LEGGINGS.get(),
                ModItems.FROSTEEL_BOOTS.get(),

                ModItems.LUMINTHIUM_HELMET.get(),
                ModItems.LUMINTHIUM_CHESTPLATE.get(),
                ModItems.LUMINTHIUM_LEGGINGS.get(),
                ModItems.LUMINTHIUM_BOOTS.get(),

                ModItems.SOLARITE_HELMET.get(),
                ModItems.SOLARITE_CHESTPLATE.get(),
                ModItems.SOLARITE_LEGGINGS.get(),
                ModItems.SOLARITE_BOOTS.get(),

                ModItems.RUBY_HELMET.get(),
                ModItems.RUBY_CHESTPLATE.get(),
                ModItems.RUBY_LEGGINGS.get(),
                ModItems.RUBY_BOOTS.get()

                /*ModItems.SAPPHIRE_ARMORS.helmet().get(),
                ModItems.SAPPHIRE_ARMORS.chestplate().get(),
                ModItems.SAPPHIRE_ARMORS.leggings().get(),
                ModItems.SAPPHIRE_ARMORS.boots().get()*/
        );

        tag(ModTags.Items.FROSTEEL_TOOL).add(
                ModTools.FROSTEEL_TOOLS.sword().get(),
                ModTools.FROSTEEL_TOOLS.pickaxe().get(),
                ModTools.FROSTEEL_TOOLS.axe().get(),
                ModTools.FROSTEEL_TOOLS.hoe().get(),
                ModTools.FROSTEEL_TOOLS.shovel().get()
        );

        tag(ModTags.Items.SOLARITE_TOOL).add(
                ModTools.SOLARITE_TOOLS.sword().get(),
                ModTools.SOLARITE_TOOLS.pickaxe().get(),
                ModTools.SOLARITE_TOOLS.axe().get(),
                ModTools.SOLARITE_TOOLS.hoe().get(),
                ModTools.SOLARITE_TOOLS.shovel().get()
        );
    }
}
