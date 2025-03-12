package xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.content.item.UOTrimMaterials;
import xun.unoredinary.world.feature.UOConfiguredFeatures;
import xun.unoredinary.world.feature.UOPlacedFeatures;
import xun.unoredinary.world.modifier.UOBiomeModifiers;

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
