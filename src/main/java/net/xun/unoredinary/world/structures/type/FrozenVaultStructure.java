package net.xun.unoredinary.world.structures.type;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.neoforged.neoforge.common.Tags;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.registry.UOStructureTypes;
import net.xun.unoredinary.world.structures.pieces.FrozenVaultPiece;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FrozenVaultStructure extends Structure {
    public static final MapCodec<FrozenVaultStructure> CODEC = simpleCodec(FrozenVaultStructure::new);

    protected FrozenVaultStructure(StructureSettings settings) {
        super(settings);
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos center = new BlockPos(chunkPos.getMiddleBlockX(), 0, chunkPos.getMiddleBlockZ());

        int surfaceY = context.chunkGenerator().getBaseHeight(
                center.getX(),
                center.getZ(),
                Heightmap.Types.OCEAN_FLOOR_WG,
                context.heightAccessor(),
                context.randomState()
        );

        int minY = context.chunkGenerator().getMinY();
        int targetY = surfaceY - 20 - context.random().nextInt(10);
        int clampedY = Math.max(targetY, minY + 15);

        Optional<StructureTemplate> template = context.structureTemplateManager().get(CommonUtils.modLoc("frozen_vault"));
        if (template.isEmpty()) {
            return Optional.empty();
        }

        StructureTemplate vaultTemplate = template.get();
        int width = vaultTemplate.getSize().getX();
        int depth = vaultTemplate.getSize().getZ();

        BlockPos startPos = new BlockPos(
                center.getX() - width / 2,
                clampedY,
                center.getZ() - depth / 2
        );

        return Optional.of(new GenerationStub(startPos, builder -> builder.addPiece(new FrozenVaultPiece(context.structureTemplateManager(), startPos))));
    }

    @Override
    public StructureType<?> type() {
        return UOStructureTypes.FROZEN_VAULT.get();
    }

    public static FrozenVaultStructure create(BootstrapContext<Structure> context) {
        Map<MobCategory, StructureSpawnOverride> spawnOverrides = new HashMap<>();
        for (MobCategory category : MobCategory.values()) {
            spawnOverrides.put(category, new StructureSpawnOverride(
                    StructureSpawnOverride.BoundingBoxType.STRUCTURE,
                    WeightedRandomList.create()
            ));
        }

        return new FrozenVaultStructure(new StructureSettings(
                context.lookup(Registries.BIOME).getOrThrow(Tags.Biomes.IS_ICY),
                spawnOverrides,
                GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
                TerrainAdjustment.BEARD_THIN)
        );
    }
}