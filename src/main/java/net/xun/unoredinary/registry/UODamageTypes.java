package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.xun.lib.common.api.util.CommonUtils;

import java.util.Map;

public class UODamageTypes {

    public static final ResourceKey<DamageType> COLD_DAMAGE = createKey("cold_damage");

    private static ResourceKey<DamageType> createKey(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, CommonUtils.modLoc(name));
    }
}
