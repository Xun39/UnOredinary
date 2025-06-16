package net.xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.registry.UOTrimMaterials;
import net.xun.unoredinary.world.UOBiomeModifiers;
import net.xun.unoredinary.world.UOConfiguredFeatures;
import net.xun.unoredinary.world.UOPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class UODatapackEntries extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.TRIM_MATERIAL, UOTrimMaterials::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, UOConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, UOPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, UOBiomeModifiers::bootstrap);

    public UODatapackEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(UnOredinary.MOD_ID));
    }
}
