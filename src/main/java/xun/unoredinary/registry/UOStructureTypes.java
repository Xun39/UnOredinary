package xun.unoredinary.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.world.structure.FrozenTombStructure;

public class UOStructureTypes {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, UnOredinary.MOD_ID);
    public static void register(IEventBus eventBus) { STRUCTURE_TYPES.register(eventBus); }

    public static final DeferredHolder<StructureType<?>, StructureType<FrozenTombStructure>> FROZEN_TOMB_STRUCTURES = STRUCTURE_TYPES.register("frozen_tomb_structures",
            () -> explicitStructureTypeTyping(FrozenTombStructure.CODEC));

    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(MapCodec<T> structureCodec) {
        return () -> structureCodec;
    }
}
