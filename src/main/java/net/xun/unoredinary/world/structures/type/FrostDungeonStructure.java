package net.xun.unoredinary.world.structures.type;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.neoforged.neoforge.common.Tags;
import net.xun.unoredinary.registry.UOStructureTypes;
import net.xun.unoredinary.registry.UOTemplatePools;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FrostDungeonStructure extends Structure {

    public static final MapCodec<FrostDungeonStructure> CODEC = RecordCodecBuilder.<FrostDungeonStructure>mapCodec(
                    instance -> instance.group(
                                    settingsCodec(instance),
                                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                                    Codec.intRange(0, 20).fieldOf("size").forGetter(structure -> structure.maxDepth),
                                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
                            )
                            .apply(instance, FrostDungeonStructure::new));

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int maxDepth;
    private final HeightProvider startHeight;
    private final int maxDistanceFromCenter;

    protected FrostDungeonStructure(
            StructureSettings settings,
            Holder<StructureTemplatePool> startPool,
            Optional<ResourceLocation> startJigsawName,
            int maxDepth,
            HeightProvider startHeight,
            int maxDistanceFromCenter
    ) {
        super(settings);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.maxDepth = maxDepth;
        this.startHeight = startHeight;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }

    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
        int startY = this.startHeight.sample(
                context.random(),
                new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor())
        );

        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos(chunkPos.getMinBlockX(), startY, chunkPos.getMinBlockZ());

        return JigsawPlacement.addPieces(
                context,
                this.startPool,
                Optional.empty(),
                this.maxDepth,
                blockPos,
                false,
                Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
                this.maxDistanceFromCenter,
                PoolAliasLookup.EMPTY,
                JigsawStructure.DEFAULT_DIMENSION_PADDING,
                JigsawStructure.DEFAULT_LIQUID_SETTINGS
        );
    }

    @Override
    public StructureType<?> type() {
        return UOStructureTypes.FROST_DUNGEON.get();
    }

    public static FrostDungeonStructure create(BootstrapContext<Structure> context) {
        Map<MobCategory, StructureSpawnOverride> spawnOverrides = new HashMap<>();
        for (MobCategory category : MobCategory.values()) {
            spawnOverrides.put(category, new StructureSpawnOverride(
                    StructureSpawnOverride.BoundingBoxType.STRUCTURE,
                    WeightedRandomList.create()
            ));
        }

        return new FrostDungeonStructure(
                new StructureSettings(
                        context.lookup(Registries.BIOME).getOrThrow(Tags.Biomes.IS_SNOWY),
                        spawnOverrides,
                        GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
                        TerrainAdjustment.NONE
                ),
                context.lookup(Registries.TEMPLATE_POOL).getOrThrow(UOTemplatePools.FROST_DUNGEON_ENTRANCES),
                Optional.empty(),
                12,
                TrapezoidHeight.of(VerticalAnchor.aboveBottom(64), VerticalAnchor.belowTop(256)),
                84
                );
    }
}
