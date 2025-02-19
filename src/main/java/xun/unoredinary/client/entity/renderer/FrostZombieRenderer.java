package xun.unoredinary.client.entity.renderer;

import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.client.entity.model.FrostZombieModel;
import xun.unoredinary.content.entity.FrostZombieEntity;
import xun.unoredinary.util.ModModelLayers;

public class FrostZombieRenderer extends AbstractZombieRenderer<FrostZombieEntity, FrostZombieModel> {

    private static final ResourceLocation LOCATION = UnOredinary.modLoc("textures/entity/frost_zombie/frost_zombie.png");

    public FrostZombieRenderer(EntityRendererProvider.Context context) {
        super(context,
                new FrostZombieModel(context.bakeLayer(ModModelLayers.FROST_ZOMBIE)),
                new FrostZombieModel(context.bakeLayer(ModModelLayers.FROST_ZOMBIE_INNER_ARMOR)),
                new FrostZombieModel(context.bakeLayer(ModModelLayers.FROST_ZOMBIE_OUTER_ARMOR))
        );

        this.addLayer(new FrostZombieOuterLayer(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie entity) {
        return LOCATION;
    }
}
