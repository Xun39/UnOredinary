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
import xun.unoredinary.data.provider.ModBlockLootProvider;
import xun.unoredinary.data.provider.ModEntityLootProvider;
import xun.unoredinary.registry.ModBlocks;
import xun.unoredinary.registry.ModEntityTypes;
import xun.unoredinary.registry.ModItems;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ModLootTables extends LootTableProvider {

    public ModLootTables(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Collections.emptySet(),
                List.of(new SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK),
                        new SubProviderEntry(EntityLootTables::new, LootContextParamSets.ENTITY)
                ), registries);
    }

    public static class BlockLootTables extends ModBlockLootProvider {

        public BlockLootTables(HolderLookup.Provider registries) {
            super(registries);
        }

        @Override
        protected void generate() {

            multipleDrops(ModBlocks.HEMOCRYLIC_ORE, ModItems.HEMOCRYLIC_SHARD.get(), 1.0F, 5.0F);
            dropSelf(ModBlocks.HEMOCRYLIC_BLOCK.get());
            dropSelf(ModBlocks.FROSTEEL_BLOCK.get());

            multipleDrops(ModBlocks.CRYOSTONE_ORE, ModItems.CRYOSTONE_DUST.get(), 3.0F, 4.0F);
            multipleDrops(ModBlocks.DEEPSLATE_CRYOSTONE_ORE, ModItems.CRYOSTONE_DUST.get(), 3.0F, 4.0F);
            dropSelf(ModBlocks.CRYOSTONE_BLOCK.get());

            dropOther(ModBlocks.LUMINITE_ORE, ModItems.LUMINITE_CRYSTAL.get());
            dropOther(ModBlocks.DEEPSLATE_LUMINITE_ORE, ModItems.LUMINITE_CRYSTAL.get());
            dropSelf(ModBlocks.LUMINITE_BLOCK.get());

            dropOther(ModBlocks.RUBY_ORE, ModItems.RUBY.get());
            dropOther(ModBlocks.DEEPSLATE_RUBY_ORE, ModItems.RUBY.get());
            dropSelf(ModBlocks.RUBY_BLOCK.get());

            dropOther(ModBlocks.FROSTBOUND_STONE, ModBlocks.FROSTBOUND_COBBLESTONE);
            dropSelf(ModBlocks.FROSTBOUND_STONE_STAIRS.get());
            slabDrop(ModBlocks.FROSTBOUND_STONE_SLAB);

            dropSelf(ModBlocks.FROSTBOUND_COBBLESTONE.get());
            dropSelf(ModBlocks.FROSTBOUND_COBBLESTONE_STAIRS.get());
            slabDrop(ModBlocks.FROSTBOUND_COBBLESTONE_SLAB);
            dropSelf(ModBlocks.FROSTBOUND_COBBLESTONE_WALL.get());

            dropSelf(ModBlocks.CRYOBOUND_COBBLESTONE.get());
            dropSelf(ModBlocks.CRYOBOUND_COBBLESTONE_STAIRS.get());
            slabDrop(ModBlocks.CRYOBOUND_COBBLESTONE_SLAB);
            dropSelf(ModBlocks.CRYOBOUND_COBBLESTONE_WALL.get());

            dropSelf(ModBlocks.FROSTBOUND_STONE_BRICKS.get());
            dropSelf(ModBlocks.FROSTBOUND_STONE_BRICK_STAIRS.get());
            slabDrop(ModBlocks.FROSTBOUND_STONE_BRICK_SLAB);
            dropSelf(ModBlocks.FROSTBOUND_STONE_BRICK_WALL.get());

            dropSelf(ModBlocks.ICE_BRICKS.get());
            dropSelf(ModBlocks.ICE_BRICK_STAIRS.get());
            slabDrop(ModBlocks.ICE_BRICK_SLAB);
            dropSelf(ModBlocks.ICE_BRICK_WALL.get());

            dropOther(ModBlocks.LUMINITE_TORCH.get(), ModItems.LUMINITE_TORCH);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
        }
    }

    public static class EntityLootTables extends ModEntityLootProvider {

        public EntityLootTables(HolderLookup.Provider registries) {
            super(registries);
        }

        @Override
        public void generate() {

            add(ModEntityTypes.FROST_ZOMBIE.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(ModItems.FROSTBITTEN_PHALANGES)
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
            return ModEntityTypes.ENTITY_TYPES.getEntries().stream().map(DeferredHolder::value);
        }
    }
}
