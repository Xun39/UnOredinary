package net.xun.unoredinary.registry;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
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
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_CENTERS = createKey("frost_dungeon/centers");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_DESCENDING_CONNECTIONS = createKey("frost_dungeon/descending_connections");
    public static final ResourceKey<StructureTemplatePool> FROST_DUNGEON_HALLWAYS = createKey("frost_dungeon/hallways");
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
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/center_start"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_CENTERS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/center/center_1"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/center/center_2"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/center/center_3"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/center/center_4"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_DESCENDING_CONNECTIONS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/descending_connection/drop_1"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/descending_connection/long_stairs_1"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/descending_connection/long_stairs_2"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_HALLWAYS, new StructureTemplatePool(
                templatePools.getOrThrow(UOTemplatePools.FROST_DUNGEON_HALLWAYS_ENDS),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/hallway_path_1"), 4),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/hallway_path_2"), 4),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/hallway_path_3"), 4),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/hallway_path_open_left"), 3),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/hallway_path_open_right"), 3),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/hallway_path_open_both"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/monster_path_1"), 3),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/monster_path_2"), 3),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/treasure_path_left"), 2),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/treasure_path_right"), 2),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/hallways/treasure_path_both"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_HALLWAYS_ENDS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/endings/hallway_path_end"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/endings/hallway_path_end_shorter"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_INTERSECTIONS, new StructureTemplatePool(
                templatePools.getOrThrow(UOTemplatePools.FROST_DUNGEON_MONSTER_ROOMS),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_cross"), 3),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_t"), 3),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_drop_1"), 2),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_drop_2"), 2),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_fake_trap"), 2),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_spawner_1"), 2),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_spawner_2"), 2),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_trapped_1"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_trapped_2"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/intersection/intersection_trapped_3"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_TREASURE_ROOMS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/treasure_room/empty_room"), 3),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/treasure_room/treasure_room_loot"), 4),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/treasure_room/treasure_room_gold"), 2),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/treasure_room/treasure_room_diamond"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_MONSTER_ROOMS, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/monster_room/spawner_frost_zombie"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/monster_room/spawner_stray"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_BUILDINGS, new StructureTemplatePool(
                templatePools.getOrThrow(UOTemplatePools.FROST_DUNGEON_EMPTY_BUILDING_WALL),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/building/prison"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/building/warrior_tombs"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/building/baby_zombie"), 1),
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/building/chicken"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );

        context.register(FROST_DUNGEON_EMPTY_BUILDING_WALL, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("unoredinary:frost_dungeon/endings/wall"), 1)
                ),
                StructureTemplatePool.Projection.RIGID)
        );
    }
}