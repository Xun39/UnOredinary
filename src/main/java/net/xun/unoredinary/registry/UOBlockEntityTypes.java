package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.block.entity.TransenchantingTableBlockEntity;

public class UOBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, UnOredinary.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TransenchantingTableBlockEntity>> TRANSENCHANTING_TABLE = BLOCK_ENTITY_TYPES.register("transenchanting_table",
            () -> BlockEntityType.Builder.of(TransenchantingTableBlockEntity::new, UOBlocks.TRANSENCHANTING_TABLE.get()).build(null));
}
