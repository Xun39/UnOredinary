package net.xun.unoredinary.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.client.model.FrozenCataModel;
import net.xun.unoredinary.client.model.layer.UOModelLayers;
import net.xun.unoredinary.entity.FrozenCataEntity;

public class FrozenCataRenderer extends MobRenderer<FrozenCataEntity, FrozenCataModel<FrozenCataEntity>> {
    public FrozenCataRenderer(EntityRendererProvider.Context context) {
        super(context, new FrozenCataModel<>(context.bakeLayer(UOModelLayers.FROZEN_CATA)), 2.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(FrozenCataEntity entity) {
        return CommonUtils.modLoc("textures/entity/frozen_cata/frozen_cata.png");
    }
}
