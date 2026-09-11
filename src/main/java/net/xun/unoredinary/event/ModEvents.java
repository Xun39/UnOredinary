package net.xun.unoredinary.event;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.entity.FrostRevenant;
import net.xun.unoredinary.entity.FrostZombie;
import net.xun.unoredinary.registry.UOEntityTypes;
import net.xun.unoredinary.registry.UOFluids;
import net.xun.unoredinary.registry.UOItems;
import net.xun.unoredinary.util.CustomTrialStuffs;
import net.xun.unoredinary.world.UOCauldronInteractions;

import java.nio.file.Path;
import java.util.Optional;

@EventBusSubscriber(modid = UnOredinary.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(UOFluids::registerFluidInteractions);
        event.enqueueWork(UOCauldronInteractions::bootstrap);
    }

    @SubscribeEvent
    public static void onEntityAttributesCreated(EntityAttributeCreationEvent event) {
        event.put(UOEntityTypes.FROST_ZOMBIE.get(), FrostZombie.createAttributes().build());
        event.put(UOEntityTypes.FROST_REVENANT.get(), FrostRevenant.createAttributes().build());
    }

    @SubscribeEvent
    public static void buildCreativeModeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            insertAfter(event, Items.ZOMBIE_SPAWN_EGG, UOItems.FROST_ZOMBIE_SPAWN_EGG);
            insertAfter(event, UOItems.FROST_ZOMBIE_SPAWN_EGG, UOItems.FROST_REVENANT_SPAWN_EGG);
        }

        if (event.getTabKey() == CreativeModeTabs.OP_BLOCKS) {
            event.accept(CustomTrialStuffs.createFrostDungeonTrialSpawner(), CreativeModeTab.TabVisibility.SEARCH_TAB_ONLY);
            event.accept(CustomTrialStuffs.createFrostDungeonVault(), CreativeModeTab.TabVisibility.SEARCH_TAB_ONLY);
        }
    }

    private static void insertAfter(BuildCreativeModeTabContentsEvent event, ItemLike existingEntry, ItemLike newEntry) {
        event.insertAfter(new ItemStack(existingEntry), new ItemStack(newEntry), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    // temporary built-in pack
    @SubscribeEvent
    public static void onAddPackFinders(AddPackFindersEvent event) {
        registerCompatPack(event, PackType.CLIENT_RESOURCES, "UnOredinary Compat");
        registerCompatPack(event, PackType.SERVER_DATA, "UnOredinary Compat");
    }

    private static void registerCompatPack(AddPackFindersEvent event, PackType packType, String displayName) {
        if (event.getPackType() == packType) {
            Path packPath = ModList.get().getModFileById(UnOredinary.MOD_ID)
                    .getFile()
                    .findResource("resourcepacks/unoredinary_compat");

            PackLocationInfo packInfo = new PackLocationInfo(
                    "unoredinary:compat_" + packType.getDirectory(),
                    Component.literal(displayName),
                    PackSource.BUILT_IN,
                    Optional.empty()
            );

            Pack pack = Pack.readMetaAndCreate(
                    packInfo,
                    new PathPackResources.PathResourcesSupplier(packPath),
                    packType,
                    new PackSelectionConfig(true, Pack.Position.BOTTOM, true)
            );

            if (pack != null) {
                event.addRepositorySource(consumer -> consumer.accept(pack.hidden()));
            }
        }
    }
}
