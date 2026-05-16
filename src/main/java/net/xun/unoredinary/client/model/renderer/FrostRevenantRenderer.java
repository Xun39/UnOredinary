package net.xun.unoredinary.client.model.renderer;

import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.client.model.UOModelLayers;
import net.xun.unoredinary.client.model.entity.FrostRevenantModel;
import net.xun.unoredinary.entity.FrostRevenant;

public class FrostRevenantRenderer extends AbstractZombieRenderer<FrostRevenant, FrostRevenantModel> {

    private static final ResourceLocation LOCATION = CommonUtils.modLoc("textures/entity/frost_revenant/frost_revenant.png");

    public FrostRevenantRenderer(EntityRendererProvider.Context context) {
        super(context,
                new FrostRevenantModel(context.bakeLayer(UOModelLayers.FROST_REVENANT)),
                new FrostRevenantModel(context.bakeLayer(UOModelLayers.FROST_REVENANT_INNER_ARMOR)),
                new FrostRevenantModel(context.bakeLayer(UOModelLayers.FROST_REVENANT_OUTER_ARMOR))
        );
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie entity) {
        return LOCATION;
    }
}
