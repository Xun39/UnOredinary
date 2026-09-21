package net.xun.unoredinary.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VaultBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultBlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultServerData;
import net.minecraft.world.level.block.entity.vault.VaultState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.xun.lib.common.api.world.structures.TerrainAwareJigsawStructure;
import net.xun.unoredinary.registry.UOStructureTypes;

import java.util.Optional;

//TODO: reset vaults server data when generating
public class FrostDungeonStructure extends TerrainAwareJigsawStructure {
    public static final MapCodec<FrostDungeonStructure> CODEC =
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(s -> s.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(s -> s.startJigsawName),
                    Codec.intRange(0, 20).fieldOf("size").forGetter(s -> s.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(s -> s.startHeight),
                    Codec.BOOL.fieldOf("use_expansion_hack").forGetter(s -> s.useExpansionHack),
                    Codec.INT.fieldOf("max_distance_from_center").forGetter(s -> s.maxDistanceFromCenter),
                    DimensionPadding.CODEC.fieldOf("dimension_padding").forGetter(s -> s.dimensionPadding),
                    LiquidSettings.CODEC.fieldOf("liquid_settings").forGetter(s -> s.liquidSettings),
                    TerrainPlacement.CODEC.fieldOf("terrain_placement").forGetter(s -> s.terrainPlacement)
            ).apply(instance, FrostDungeonStructure::new));

    public FrostDungeonStructure(
            StructureSettings settings,
            Holder<StructureTemplatePool> startPool,
            Optional<ResourceLocation> startJigsawName,
            int size,
            HeightProvider startHeight,
            boolean useExpansionHack,
            int maxDistanceFromCenter,
            DimensionPadding dimensionPadding,
            LiquidSettings liquidSettings,
            TerrainPlacement terrainPlacement
    ) {
        super(settings, startPool, startJigsawName, size, startHeight, useExpansionHack, maxDistanceFromCenter, dimensionPadding, liquidSettings, terrainPlacement);
    }

    @Override
    public void afterPlace(WorldGenLevel level, StructureManager structureManager, ChunkGenerator chunkGenerator, RandomSource random, BoundingBox boundingBox, ChunkPos chunkPos, PiecesContainer pieces) {
        super.afterPlace(level, structureManager, chunkGenerator, random, boundingBox, chunkPos, pieces);
        resetVaultRuntimeData(level, boundingBox);
    }

    private static void resetVaultRuntimeData(WorldGenLevel level, BoundingBox boundingBox) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int x = boundingBox.minX(); x <= boundingBox.maxX(); x++) {
            for (int y = boundingBox.minY(); y <= boundingBox.maxY(); y++) {
                for (int z = boundingBox.minZ(); z <= boundingBox.maxZ(); z++) {
                    pos.set(x, y, z);
                    if (!level.getBlockState(pos).is(Blocks.VAULT)) {
                        continue;
                    }

                    BlockEntity blockEntity = level.getBlockEntity(pos);
                    if (!(blockEntity instanceof VaultBlockEntity vault)) {
                        continue;
                    }

                    resetVault(vault);
                }
            }
        }
    }

    private static void resetVault(VaultBlockEntity vault) {
        VaultServerData serverData = vault.getServerData();
        if (serverData != null) serverData.pauseStateUpdatingUntil(0L);
        vault.setChanged();
    }

    @Override
    public StructureType<?> type() {
        return UOStructureTypes.FROST_DUNGEON.get();
    }
}
