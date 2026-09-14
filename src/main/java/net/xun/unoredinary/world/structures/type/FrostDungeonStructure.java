package net.xun.unoredinary.world.structures.type;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.xun.unoredinary.registry.UOStructureTypes;

import java.util.Optional;

public class FrostDungeonStructure extends Structure {

    /**
     * Width/depth of the terrain area that must be suitable for the entrance.
     *
     * These are measured from the structure start position, which is the
     * minimum X/Z of the chunk in findGenerationPoint().
     *
     * Change these to match the actual footprint of entrance.nbt.
     */
    private static final int ENTRANCE_WIDTH = 11;
    private static final int ENTRANCE_DEPTH = 11;

    /**
     * Maximum difference between the highest and lowest sampled terrain
     * heights for the entrance to be considered suitable.
     *
     * 3-4 works well for a fairly rigid building.
     */
    private static final int MAX_TERRAIN_VARIANCE = 3;

    /**
     * How far below the lowest sampled terrain height the entrance is placed.
     *
     * 0 = its lowest point is flush with the lowest terrain.
     * 1 = bury the entire entrance by one block, etc.
     */
    private static final int SURFACE_OFFSET = -2;

    public static final MapCodec<FrostDungeonStructure> CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    instance.group(
                            FrostDungeonStructure.settingsCodec(instance),

                            StructureTemplatePool.CODEC
                                    .fieldOf("start_pool")
                                    .forGetter(structure -> structure.startPool),

                            ResourceLocation.CODEC
                                    .optionalFieldOf("start_jigsaw_name")
                                    .forGetter(structure -> structure.startJigsawName),

                            com.mojang.serialization.Codec.intRange(0, 30)
                                    .fieldOf("size")
                                    .forGetter(structure -> structure.size),

                            HeightProvider.CODEC
                                    .fieldOf("start_height")
                                    .forGetter(structure -> structure.startHeight),

                            Heightmap.Types.CODEC
                                    .optionalFieldOf("project_start_to_heightmap")
                                    .forGetter(structure -> structure.projectStartToHeightmap),

                            com.mojang.serialization.Codec.intRange(1, 128)
                                    .fieldOf("max_distance_from_center")
                                    .forGetter(structure -> structure.maxDistanceFromCenter),

                            DimensionPadding.CODEC
                                    .optionalFieldOf(
                                            "dimension_padding",
                                            DimensionPadding.ZERO
                                    )
                                    .forGetter(structure -> structure.dimensionPadding),

                            LiquidSettings.CODEC
                                    .optionalFieldOf(
                                            "liquid_settings",
                                            LiquidSettings.IGNORE_WATERLOGGING
                                    )
                                    .forGetter(structure -> structure.liquidSettings)
                    ).apply(instance, FrostDungeonStructure::new)
            );

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final int size;
    private final int maxDistanceFromCenter;
    private final DimensionPadding dimensionPadding;
    private final LiquidSettings liquidSettings;

    public FrostDungeonStructure(
            StructureSettings settings,
            Holder<StructureTemplatePool> startPool,
            Optional<ResourceLocation> startJigsawName,
            int size,
            HeightProvider startHeight,
            Optional<Heightmap.Types> projectStartToHeightmap,
            int maxDistanceFromCenter,
            DimensionPadding dimensionPadding,
            LiquidSettings liquidSettings
    ) {
        super(settings);

        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.size = size;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
        this.dimensionPadding = dimensionPadding;
        this.liquidSettings = liquidSettings;
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();

        int originX = chunkPos.getMinBlockX();
        int originZ = chunkPos.getMinBlockZ();

        /*
         * Keep start_height meaningful as a vertical offset.
         *
         * Your existing structure uses:
         *
         *     ConstantHeight.of(VerticalAnchor.absolute(1))
         *
         * but because we are deliberately bypassing vanilla's
         * projectStartToHeightmap behavior, we only use the provider as
         * an additional offset.
         */
        int startHeight = this.startHeight.sample(
                context.random(),
                new WorldGenerationContext(
                        context.chunkGenerator(),
                        context.heightAccessor()
                )
        );

        /*
         * Find the terrain height across the entire entrance footprint.
         *
         * We intentionally use WORLD_SURFACE_WG here instead of reading
         * actual world blocks. This is appropriate during structure-start
         * generation and matches vanilla's worldgen heightmap behavior.
         */
        TerrainInfo terrain = sampleTerrain(
                context,
                originX,
                originZ,
                ENTRANCE_WIDTH,
                ENTRANCE_DEPTH
        );

        /*
         * If the entrance is sitting on a slope that is too steep,
         * don't generate the dungeon here at all.
         *
         * This is preferable to moving individual pieces or changing
         * their shape.
         */
        if (terrain.variance() > MAX_TERRAIN_VARIANCE) {
            return Optional.empty();
        }

        /*
         * Align the WHOLE jigsaw structure to the lowest sampled terrain
         * point. This guarantees that no part of the entrance is floating
         * simply because one edge of the terrain is lower than the center.
         *
         * Because we pass Optional.empty() to JigsawPlacement below,
         * vanilla will NOT perform another heightmap projection.
         */
        int structureY = terrain.minimumHeight() + startHeight - SURFACE_OFFSET;

        BlockPos startPos = new BlockPos(
                originX,
                structureY,
                originZ
        );

        /*
         * This is still the vanilla jigsaw assembler.
         *
         * The important difference is that we provide the Y we already
         * calculated and disable the second heightmap projection.
         *
         * All pieces remain exactly as RIGID pieces.
         */
        return JigsawPlacement.addPieces(
                context,
                this.startPool,
                this.startJigsawName,
                this.size,
                startPos,
                false,
                Optional.empty(),
                this.maxDistanceFromCenter,
                PoolAliasLookup.EMPTY,
                this.dimensionPadding,
                this.liquidSettings
        );
    }

    private static TerrainInfo sampleTerrain(GenerationContext context, int minX, int minZ, int width, int depth) {
        int minimum = Integer.MAX_VALUE;
        int maximum = Integer.MIN_VALUE;

        for (int x = minX; x < minX + width; x++) {
            for (int z = minZ; z < minZ + depth; z++) {
                int height = context.chunkGenerator().getFirstOccupiedHeight(
                        x,
                        z,
                        Heightmap.Types.WORLD_SURFACE_WG,
                        context.heightAccessor(),
                        context.randomState()
                );

                minimum = Math.min(minimum, height);
                maximum = Math.max(maximum, height);
            }
        }

        return new TerrainInfo(
                minimum,
                maximum,
                maximum - minimum
        );
    }

    @Override
    public StructureType<?> type() {
        return UOStructureTypes.FROST_DUNGEON.get();
    }

    private record TerrainInfo(
            int minimumHeight,
            int maximumHeight,
            int variance
    ) {
    }
}
