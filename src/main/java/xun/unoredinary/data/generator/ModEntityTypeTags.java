package xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.registry.ModEntityTypes;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTags extends EntityTypeTagsProvider {

    public ModEntityTypeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, UnOredinary.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(EntityTypeTags.ZOMBIES).add(
                ModEntityTypes.FROST_ZOMBIE.get());
    }
}
