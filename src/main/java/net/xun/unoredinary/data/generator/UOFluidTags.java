package net.xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.registry.UOFluids;
import net.xun.unoredinary.util.UOTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class UOFluidTags extends FluidTagsProvider {
    public UOFluidTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, UnOredinary.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(UOTags.Fluids.CRYIC).add(
                UOFluids.CRYOPLASM.get(),
                UOFluids.FLOWING_CRYOPLASM.get()
        );
    }
}
