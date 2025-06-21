package net.xun.unoredinary.world.structures.pieces;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.registry.UOStructurePieceTypes;
import net.xun.unoredinary.world.loot.UOLootTableKeys;

public class FrozenVaultPiece extends TemplateStructurePiece {
    private static final ResourceLocation STRUCTURE_LOCATION = CommonUtils.modLoc("frozen_vault");
    private static final BlockPos TEMPLATE_OFFSET = new BlockPos(0, 0, 0);

    public FrozenVaultPiece(StructureTemplateManager templateManager, BlockPos pos) {
        super(
                UOStructurePieceTypes.FROZEN_VAULT.get(),
                0,
                templateManager,
                STRUCTURE_LOCATION,
                STRUCTURE_LOCATION.toString(),
                makeSettings(),
                pos.offset(TEMPLATE_OFFSET)
        );
    }

    public FrozenVaultPiece(StructurePieceSerializationContext context, CompoundTag nbt) {
        super(
                UOStructurePieceTypes.FROZEN_VAULT.get(),
                nbt,
                context.structureTemplateManager(),
                resourceLocation -> makeSettings()
        );
    }

    private static StructurePlaceSettings makeSettings() {
        return new StructurePlaceSettings().addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
        if ("chest".equals(function)) {
            level.setBlock(pos, Blocks.CHEST.defaultBlockState(), Block.UPDATE_ALL);

            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof ChestBlockEntity chest) {
                chest.setLootTable(UOLootTableKeys.FROZEN_VAULT, random.nextLong());
            }
        }
    }
}