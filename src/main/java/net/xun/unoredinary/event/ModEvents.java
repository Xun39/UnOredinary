package net.xun.unoredinary.event;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultBlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultServerData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.entity.FrostRevenantEntity;
import net.xun.unoredinary.entity.FrostZombieEntity;
import net.xun.unoredinary.entity.FrozenCataEntity;
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
        event.put(UOEntityTypes.FROST_ZOMBIE.get(), FrostZombieEntity.createAttributes().build());
        event.put(UOEntityTypes.FROST_REVENANT.get(), FrostRevenantEntity.createAttributes().build());
        event.put(UOEntityTypes.FROZEN_CATA.get(), FrozenCataEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void buildCreativeModeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            insertAfter(event, Items.ZOMBIE_SPAWN_EGG, UOItems.FROST_ZOMBIE_SPAWN_EGG);
            insertAfter(event, UOItems.FROST_ZOMBIE_SPAWN_EGG, UOItems.FROST_REVENANT_SPAWN_EGG);
        }

        if (event.getTabKey() == CreativeModeTabs.OP_BLOCKS) {
            event.accept(CustomTrialStuffs.createFrostRevenantSpawner(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.accept(CustomTrialStuffs.createFrostZombieSpawner(false), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.accept(CustomTrialStuffs.createFrostZombieSpawner(true), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.accept(CustomTrialStuffs.createStraySpawner(false), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.accept(CustomTrialStuffs.createStraySpawner(true), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.accept(CustomTrialStuffs.createFrostDungeonVault(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
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

//    @SubscribeEvent
//    public static void onChunkLoad(ChunkEvent.Load event) {
//        if (!event.isNewChunk()) return;
//        if (!(event.getLevel() instanceof ServerLevel level)) return;
//
//        ChunkPos chunkPos = event.getChunk().getPos();
//
//        //level.getServer().execute(() -> resetFrostDungeonVaults(level, chunkPos));
//    }
//
//    private static void resetFrostDungeonVaults(ServerLevel level, ChunkPos chunkPos) {
//        var chunk = level.getChunk(chunkPos.x, chunkPos.z);
//
//        for (BlockEntity blockEntity : chunk.getBlockEntities().values()) {
//            if (!(blockEntity instanceof VaultBlockEntity vault)) continue;
//            if (!isFrostDungeonVault(vault)) continue;
//
//            resetVault(vault);
//        }
//    }
//
//    private static boolean isFrostDungeonVault(VaultBlockEntity vault) {
//        return vault.getConfig().keyItem().is(UOItems.FROST_KEY.get());
//    }
//
//    private static void resetVault(VaultBlockEntity vault) {
//        VaultServerData serverData = vault.getServerData();
//        if (serverData != null) serverData.pauseStateUpdatingUntil(0L);
//        vault.setChanged();
//    }
}
