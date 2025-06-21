package net.xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UOLootTables extends LootTableProvider {

    public UOLootTables(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(
                output,
                Collections.emptySet(),
                List.of(
                        new SubProviderEntry(UOBlockLoot::new, LootContextParamSets.BLOCK),
                        new SubProviderEntry(UOChestLoot::new, LootContextParamSets.CHEST)
                ),
                registries
        );
    }
}
