package net.xun.unoredinary.client.fluid;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.xun.lib.common.api.util.CommonUtils;
import org.joml.Vector3f;

public class CryicFluidTypeClientExtensions implements IClientFluidTypeExtensions {
    @Override
    public ResourceLocation getStillTexture() {
        return CommonUtils.modLoc("fluid/cryic_still");
    }

    @Override
    public ResourceLocation getFlowingTexture() {
        return CommonUtils.modLoc("fluid/cryic_flow");
    }

    @Override
    public int getTintColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
        return new Vector3f(
                0.55F,
                0.80F,
                1.0F
        );
    }
}
