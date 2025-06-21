package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.neoforged.neoforge.common.Tags;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.util.UOTags;
import net.xun.unoredinary.world.structures.type.FrozenVaultStructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UOStructures {

    public static final ResourceKey<Structure> FROZEN_VAULT = createKey("frozen_vault");
    public static final ResourceKey<Structure> FROST_DUNGEON = createKey("frost_dungeon");

    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, CommonUtils.modLoc(name));
    }

    public static void bootstrap(BootstrapContext<Structure> context) {
        Map<MobCategory, StructureSpawnOverride> emptySpawnOverrides = new HashMap<>();
        for (MobCategory category : MobCategory.values()) {
            emptySpawnOverrides.put(category, new StructureSpawnOverride(
                    StructureSpawnOverride.BoundingBoxType.STRUCTURE,
                    WeightedRandomList.create()
            ));
        }

        context.register(FROZEN_VAULT, new FrozenVaultStructure(
                new Structure.StructureSettings(
                        context.lookup(Registries.BIOME).getOrThrow(Tags.Biomes.IS_ICY),
                        emptySpawnOverrides,
                        GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
                        TerrainAdjustment.BEARD_THIN)
                )
        );
        context.register(FROST_DUNGEON, new JigsawStructure(
                new Structure.StructureSettings(
                        context.lookup(Registries.BIOME).getOrThrow(UOTags.Biomes.HAS_STRUCTURE_FROST_DUNGEON),
                        emptySpawnOverrides,
                        GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
                        TerrainAdjustment.ENCAPSULATE
                ),
                context.lookup(Registries.TEMPLATE_POOL).getOrThrow(UOTemplatePools.FROST_DUNGEON_START),
                Optional.empty(),
                15,
                UniformHeight.of(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(-12)),
                false,
                Optional.empty(),
                116,
                List.of(),
                JigsawStructure.DEFAULT_DIMENSION_PADDING,
                LiquidSettings.IGNORE_WATERLOGGING)
        );
        // TODO: Use processors for frost dungeon
    }
}
