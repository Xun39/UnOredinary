package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.world.structures.type.FrostDungeonStructure;
import net.xun.unoredinary.world.structures.type.FrozenVaultStructure;

public class UOStructures {

    public static final ResourceKey<Structure> FROZEN_VAULT = createKey("frozen_vault");
    public static final ResourceKey<Structure> FROST_DUNGEON = createKey("frost_dungeon");

    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, CommonUtils.modLoc(name));
    }

    public static void bootstrap(BootstrapContext<Structure> context) {
        context.register(FROZEN_VAULT, FrozenVaultStructure.create(context));
        context.register(FROST_DUNGEON, FrostDungeonStructure.create(context));
    }
}
