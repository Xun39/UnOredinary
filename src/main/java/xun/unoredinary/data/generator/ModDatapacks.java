package xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.world.feature.ModConfiguredFeatures;
import xun.unoredinary.world.feature.ModPlacedFeatures;
import xun.unoredinary.world.modifier.ModBiomeModifiers;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapacks extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);

    public ModDatapacks(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(UnOredinary.MOD_ID));
    }
}
