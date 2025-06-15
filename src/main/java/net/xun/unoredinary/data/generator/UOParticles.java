package net.xun.unoredinary.data.generator;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.registry.UOParticleTypes;

public class UOParticles extends ParticleDescriptionProvider {

    public UOParticles(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper);
    }

    @Override
    protected void addDescriptions() {
        this.spriteSet(UOParticleTypes.RIME.get(), CommonUtils.modLoc("rime"), 8, true);
    }
}
