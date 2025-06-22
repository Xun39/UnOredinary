package net.xun.unoredinary.client.model.renderer;

import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.client.model.UOModelLayers;
import net.xun.unoredinary.client.model.entity.FrostZombieModel;
import net.xun.unoredinary.entity.FrostZombie;

public class FrostZombieRenderer extends AbstractZombieRenderer<FrostZombie, FrostZombieModel> {

    private static final ResourceLocation LOCATION = CommonUtils.modLoc("textures/entity/frost_zombie/frost_zombie.png");

    public FrostZombieRenderer(EntityRendererProvider.Context context) {
        super(context,
                new FrostZombieModel(context.bakeLayer(UOModelLayers.FROST_ZOMBIE)),
                new FrostZombieModel(context.bakeLayer(UOModelLayers.FROST_ZOMBIE_INNER_ARMOR)),
                new FrostZombieModel(context.bakeLayer(UOModelLayers.FROST_ZOMBIE_OUTER_ARMOR))
        );

        this.addLayer(new FrostZombieOuterLayer(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie entity) {
        return LOCATION;
    }
}
