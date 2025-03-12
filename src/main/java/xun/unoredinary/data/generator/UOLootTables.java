package xun.unoredinary.data.generator;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import xun.unoredinary.data.provider.UOBlockLootProvider;
import xun.unoredinary.data.provider.UOEntityLootProvider;
import xun.unoredinary.registry.UOBlocks;
import xun.unoredinary.registry.UOEntityTypes;
import xun.unoredinary.registry.UOItems;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class UOLootTables extends LootTableProvider {

    public UOLootTables(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Collections.emptySet(),
                List.of(new SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK),
                        new SubProviderEntry(EntityLootTables::new, LootContextParamSets.ENTITY)
                ), registries);
    }

    public static class BlockLootTables extends UOBlockLootProvider {

        public BlockLootTables(HolderLookup.Provider registries) {
            super(registries);
        }

        @Override
        protected void generate() {

            multipleDrops(UOBlocks.HEMOCRYLIC_ORE, UOItems.HEMOCRYLIC_SHARD.get(), 1.0F, 5.0F);
            dropSelf(UOBlocks.HEMOCRYLIC_BLOCK.get());
            dropSelf(UOBlocks.FROSTEEL_BLOCK.get());

            multipleDrops(UOBlocks.CRYOSTONE_ORE, UOItems.CRYOSTONE_DUST.get(), 3.0F, 4.0F);
            multipleDrops(UOBlocks.DEEPSLATE_CRYOSTONE_ORE, UOItems.CRYOSTONE_DUST.get(), 3.0F, 4.0F);
            dropSelf(UOBlocks.CRYOSTONE_BLOCK.get());

            dropOtherWithoutSilkTouch(UOBlocks.LUMINITE_ORE, UOItems.LUMINITE_CRYSTAL.get());
            dropOtherWithoutSilkTouch(UOBlocks.DEEPSLATE_LUMINITE_ORE, UOItems.LUMINITE_CRYSTAL.get());
            dropSelf(UOBlocks.LUMINITE_BLOCK.get());
            dropSelf(UOBlocks.LUMINTHIUM_BLOCK.get());

            dropOtherWithoutSilkTouch(UOBlocks.RUBY_ORE, UOItems.RUBY.get());
            dropOtherWithoutSilkTouch(UOBlocks.DEEPSLATE_RUBY_ORE, UOItems.RUBY.get());
            dropSelf(UOBlocks.RUBY_BLOCK.get());

            dropOther(UOBlocks.SOLARITE_ORE.get(), UOItems.SOLARITE_GEM);

            dropOtherWithoutSilkTouch(UOBlocks.FROSTBOUND_STONE, UOBlocks.FROSTBOUND_COBBLESTONE);
            dropSelf(UOBlocks.FROSTBOUND_STONE_STAIRS.get());
            slabDrop(UOBlocks.FROSTBOUND_STONE_SLAB);

            dropSelf(UOBlocks.FROSTBOUND_COBBLESTONE.get());
            dropSelf(UOBlocks.FROSTBOUND_COBBLESTONE_STAIRS.get());
            slabDrop(UOBlocks.FROSTBOUND_COBBLESTONE_SLAB);
            dropSelf(UOBlocks.FROSTBOUND_COBBLESTONE_WALL.get());

            dropSelf(UOBlocks.CRYOBOUND_COBBLESTONE.get());
            dropSelf(UOBlocks.CRYOBOUND_COBBLESTONE_STAIRS.get());
            slabDrop(UOBlocks.CRYOBOUND_COBBLESTONE_SLAB);
            dropSelf(UOBlocks.CRYOBOUND_COBBLESTONE_WALL.get());

            dropSelf(UOBlocks.FROSTBOUND_STONE_BRICKS.get());
            dropSelf(UOBlocks.FROSTBOUND_STONE_BRICK_STAIRS.get());
            slabDrop(UOBlocks.FROSTBOUND_STONE_BRICK_SLAB);
            dropSelf(UOBlocks.FROSTBOUND_STONE_BRICK_WALL.get());

            dropSelf(UOBlocks.ICE_BRICKS.get());
            dropSelf(UOBlocks.ICE_BRICK_STAIRS.get());
            slabDrop(UOBlocks.ICE_BRICK_SLAB);
            dropSelf(UOBlocks.ICE_BRICK_WALL.get());

            dropOther(UOBlocks.LUMINITE_TORCH.get(), UOItems.LUMINITE_TORCH);
            dropOther(UOBlocks.CRYOSTONE_TORCH.get(), UOItems.CRYOSTONE_TORCH);

            dropOther(UOBlocks.CRYOSTONE_WIRE.get(), UOItems.CRYOSTONE_DUST);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return UOBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
        }
    }

    public static class EntityLootTables extends UOEntityLootProvider {

        public EntityLootTables(HolderLookup.Provider registries) {
            super(registries);
        }

        @Override
        public void generate() {

            add(UOEntityTypes.FROST_ZOMBIE.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(UOItems.FROSTBITTEN_PHALANGES)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))))
                            .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.1F, 0.02F)))
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(Items.SALMON))
                            .add(LootItem.lootTableItem(Items.COD))
                            .add(LootItem.lootTableItem(Items.GOLD_NUGGET))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                            .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.15F, 0.05F))));
        }

        @Override
        protected Stream<EntityType<?>> getKnownEntityTypes() {
            return UOEntityTypes.ENTITY_TYPES.getEntries().stream().map(DeferredHolder::value);
        }
    }
}
