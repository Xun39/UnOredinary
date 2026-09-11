package net.xun.unoredinary.data.generator;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.registry.UOParticleTypes;

import java.util.ArrayList;
import java.util.List;

public class UOParticles extends ParticleDescriptionProvider {

    public UOParticles(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper);
    }

    @Override
    protected void addDescriptions() {
        spriteSet(UOParticleTypes.RIME.get(), CommonUtils.modLoc("rime"), 8, true);
        spriteSet(UOParticleTypes.SUBZERO_FROST.get(), CommonUtils.modLoc("subzero_frost"), 5, true);
        spriteSet(UOParticleTypes.FROST_NOVA.get(), CommonUtils.modLoc("frost_nova"), 21, false);

        List<ResourceLocation> sgaTextures = new ArrayList<>(26);
        for (char letter = 'a'; letter <= 'z'; letter++) {
            sgaTextures.add(ResourceLocation.withDefaultNamespace("sga_" + letter));
        }
        spriteSet(UOParticleTypes.TRANSENCHANT.get(), sgaTextures);
    }
}
