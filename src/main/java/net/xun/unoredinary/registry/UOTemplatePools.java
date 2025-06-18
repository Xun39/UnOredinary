package net.xun.unoredinary.registry;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.xun.lib.common.api.util.CommonUtils;

public class UOTemplatePools {

    // Frost Dungeons
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_ENTRANCES = createKey("frost_dungeon/entrances");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_ENTRANCE_STAIRCASES = createKey("frost_dungeon/entrance_staircases");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_CENTER = createKey("frost_dungeon/center");

    private static ResourceKey<StructureTemplatePool> createKey(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, CommonUtils.modLoc(name));
    }

    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        var templatePools = context.lookup(Registries.TEMPLATE_POOL);

        context.register(FROST_DUNGEON_ENTRANCES, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/entrance/entrance_1"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/entrance/entrance_2"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/entrance/entrance_3"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_ENTRANCE_STAIRCASES, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/entrance/entrance_staircase_1"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/entrance/entrance_staircase_2"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/entrance/entrance_staircase_3"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/entrance/entrance_staircase_4"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/entrance/entrance_staircase_5"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_CENTER, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/center/center_1"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );
    }
}
