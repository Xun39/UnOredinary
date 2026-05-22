package net.xun.unoredinary.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.StructureSets;
import net.minecraft.data.worldgen.Structures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.xun.lib.common.api.util.CommonUtils;

import java.util.Optional;

public class UOStructureSets {

    public static final ResourceKey<StructureSet> FROZEN_VAULTS = createKey("frozen_vaults");
    public static final ResourceKey<StructureSet> FROST_DUNGEONS = createKey("frost_dungeons");

    private static ResourceKey<StructureSet> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, CommonUtils.modLoc(name));
    }

    public static void bootstrap(BootstrapContext<StructureSet> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);
        HolderGetter<StructureSet> structureSets = context.lookup(Registries.STRUCTURE_SET);

        context.register(FROZEN_VAULTS, new StructureSet(structures.getOrThrow(UOStructures.FROZEN_VAULT), new RandomSpreadStructurePlacement(24, 10, RandomSpreadType.LINEAR, 1548653468)));
        context.register(FROST_DUNGEONS, new StructureSet(
                structures.getOrThrow(UOStructures.FROST_DUNGEON),
                new RandomSpreadStructurePlacement(
                        Vec3i.ZERO,
                        StructurePlacement.FrequencyReductionMethod.DEFAULT,
                        1.0F,
                        459846287,
                        Optional.of(new StructurePlacement.ExclusionZone(
                                structureSets.getOrThrow(BuiltinStructureSets.MINESHAFTS),
                                10)
                        ),
                        32,
                        16,
                        RandomSpreadType.LINEAR)
                )
        );
    }
}
