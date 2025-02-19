package xun.unoredinary.util;

import net.minecraft.client.model.geom.ModelLayerLocation;
import xun.unoredinary.UnOredinary;

public class ModModelLayers {

    public static final ModelLayerLocation FROST_ZOMBIE = registerDefault("frost_zombie");
    public static final ModelLayerLocation FROST_ZOMBIE_OUTER_LAYER = registerOuterLayer("frost_zombie");
    public static final ModelLayerLocation FROST_ZOMBIE_INNER_ARMOR = registerInnerArmor("frost_zombie");
    public static final ModelLayerLocation FROST_ZOMBIE_OUTER_ARMOR = registerOuterArmor("frost_zombie");

    private static ModelLayerLocation registerDefault(String path) {
        return createLocation(path, "main");
    }
    private static ModelLayerLocation registerOuterLayer(String path) {
        return createLocation(path, "outer");
    }
    private static ModelLayerLocation registerInnerArmor(String path) {
        return createLocation(path, "inner_armor");
    }
    private static ModelLayerLocation registerOuterArmor(String path) {
        return createLocation(path, "outer_armor");
    }

    private static ModelLayerLocation createLocation(String path, String model) {
        return new ModelLayerLocation(UnOredinary.modLoc(path), model);
    }
}
