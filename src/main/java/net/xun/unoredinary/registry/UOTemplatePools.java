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
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_LONG_STAIRCASES = createKey("frost_dungeon/long_staircases");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_CENTERS = createKey("frost_dungeon/centers");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_HALLWAYS = createKey("frost_dungeon/hallways");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_INTERSECTIONS = createKey("frost_dungeon/intersections");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_HOLES = createKey("frost_dungeon/holes");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_TREASURE_ROOMS = createKey("frost_dungeon/treasure_rooms");

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

        context.register(FROST_DUNGEON_LONG_STAIRCASES, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/long_staircase/long_stairs_1"), 2)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_CENTERS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/center/center_1"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_HALLWAYS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/hallway_path_1"), 3),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/hallway_path_2"), 3),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/hallway_path_3"), 3),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/treasure_path_1_left"), 2),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/treasure_path_1_right"), 2),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/treasure_path_1_both"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_INTERSECTIONS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_cross"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_t"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_down"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_HOLES, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hole/hole_1"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_TREASURE_ROOMS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/treasure_room/empty_room_1"), 2),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/treasure_room/empty_room_2"), 2),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/treasure_room/treasure_room_loot"), 2),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/treasure_room/treasure_room_gold"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/treasure_room/treasure_room_diamond"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );
    }
}
