package net.xun.unoredinary.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.xun.lib.common.api.util.CommonUtils;

public class UOStructureSets {

    public static final ResourceKey<StructureSet> FROZEN_VAULT = createKey("frozen_vault");

    public static void bootstrap(BootstrapContext<StructureSet> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

        context.register(FROZEN_VAULT, new StructureSet(structures.getOrThrow(UOStructures.FROZEN_VAULT), new RandomSpreadStructurePlacement(24, 10, RandomSpreadType.LINEAR, 1548653468)));
    }

    private static ResourceKey<StructureSet> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, CommonUtils.modLoc(name));
    }
}
