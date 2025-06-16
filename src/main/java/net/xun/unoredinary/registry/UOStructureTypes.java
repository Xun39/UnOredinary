package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.world.structures.type.FrozenVaultStructure;

public class UOStructureTypes {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, UnOredinary.MOD_ID);

    public static final DeferredHolder<StructureType<?>, StructureType<FrozenVaultStructure>> FROZEN_VAULT = STRUCTURE_TYPES.register("frozen_vault", () -> () -> FrozenVaultStructure.CODEC);
}
