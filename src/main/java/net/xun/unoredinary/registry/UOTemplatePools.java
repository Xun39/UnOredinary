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
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_START = createKey("frost_dungeon/start");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_CONNECTIONS = createKey("frost_dungeon/connections");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_CENTERS = createKey("frost_dungeon/centers");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_HALLWAYS = createKey("frost_dungeon/hallways");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_HALLS = createKey("frost_dungeon/halls");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_5x5_DECORATIONS = createKey("frost_dungeon/5x5_decorations");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_7x7_DECORATIONS = createKey("frost_dungeon/7x7_decorations");

    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_HALLWAYS_ENDS = createKey("frost_dungeon/hallway_ends");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_INTERSECTIONS = createKey("frost_dungeon/intersections");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_TREASURE_ROOMS = createKey("frost_dungeon/treasure_rooms");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_MONSTER_ROOMS = createKey("frost_dungeon/monster_rooms");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_BUILDINGS = createKey("frost_dungeon/buildings");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_EMPTY_BUILDING_WALL = createKey("frost_dungeon/empty_building_wall");

    private static ResourceKey<StructureTemplatePool> createKey(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, CommonUtils.modLoc(name));
    }

    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        var templatePools = context.lookup(Registries.TEMPLATE_POOL);

        context.register(FROST_DUNGEON_START, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/entrance"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_CONNECTIONS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/connect/grand_hall_connection"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/connect/boss_connection"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/connect/descending_stairs"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_CENTERS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/center/center"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_HALLWAYS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/hallway_1"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_HALLS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/halls/main_hall"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/halls/lower_hall"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/halls/grand_hall"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_5x5_DECORATIONS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/deco/5x5_deco_1"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/deco/5x5_deco_2"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/deco/5x5_deco_3"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_7x7_DECORATIONS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/deco/7x7_deco_1"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/deco/7x7_deco_2"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/deco/7x7_deco_3"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/deco/7x7_deco_4"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/deco/7x7_deco_5"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );
//
//        context.register(FROST_DUNGEON_HALLWAYS_ENDS, new StructureTemplatePool(
//                templatePools.getOrThrow(Pools.EMPTY),
//                ImmutableList.of(
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/endings/hallway_path_end"), 1),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/endings/hallway_path_end_shorter"), 1)
//                ),
//                StructureTemplatePool.Projection.RIGID)
//        );
//
//        context.register(FROST_DUNGEON_INTERSECTIONS, new StructureTemplatePool(
//                templatePools.getOrThrow(UOTemplatePools.FROST_DUNGEON_MONSTER_ROOMS),
//                ImmutableList.of(
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_cross"), 3),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_t"), 3),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_drop_1"), 2),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_drop_2"), 2),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_fake_trap"), 2),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_spawner_1"), 2),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_spawner_2"), 2),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_trapped_1"), 1),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_trapped_2"), 1),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_trapped_3"), 1)
//                ),
//                StructureTemplatePool.Projection.RIGID)
//        );
//
//        context.register(FROST_DUNGEON_TREASURE_ROOMS, new StructureTemplatePool(
//                templatePools.getOrThrow(Pools.EMPTY),
//                ImmutableList.of(
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/treasure_room/empty_room"), 3),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/treasure_room/treasure_room_loot"), 4),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/treasure_room/treasure_room_gold"), 2),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/treasure_room/treasure_room_diamond"), 1)
//                ),
//                StructureTemplatePool.Projection.RIGID)
//        );
//
//        context.register(FROST_DUNGEON_MONSTER_ROOMS, new StructureTemplatePool(
//                templatePools.getOrThrow(Pools.EMPTY),
//                ImmutableList.of(
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/monster_room/spawner_frost_zombie"), 1),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/monster_room/spawner_stray"), 1)
//                ),
//                StructureTemplatePool.Projection.RIGID)
//        );
//
//        context.register(FROST_DUNGEON_BUILDINGS, new StructureTemplatePool(
//                templatePools.getOrThrow(UOTemplatePools.FROST_DUNGEON_EMPTY_BUILDING_WALL),
//                ImmutableList.of(
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/building/prison"), 1),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/building/warrior_tombs"), 1),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/building/baby_zombie"), 1),
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/building/chicken"), 1)
//                ),
//                StructureTemplatePool.Projection.RIGID)
//        );
//
//        context.register(FROST_DUNGEON_EMPTY_BUILDING_WALL, new StructureTemplatePool(
//                templatePools.getOrThrow(Pools.EMPTY),
//                ImmutableList.of(
//                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/endings/wall"), 1)
//                ),
//                StructureTemplatePool.Projection.RIGID)
//        );
    }
}