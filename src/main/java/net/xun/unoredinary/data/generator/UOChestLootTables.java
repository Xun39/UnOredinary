package net.xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetPotionFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.xun.unoredinary.registry.UOItems;
import net.xun.unoredinary.world.loot.UOLootTables;
import org.checkerframework.checker.units.qual.C;

import java.util.function.BiConsumer;

public record UOChestLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {

        output.accept(UOLootTables.FROZEN_VAULT,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5))
                                .add(LootItem.lootTableItem(Items.TIPPED_ARROW).setWeight(3)
                                        .apply(SetPotionFunction.setPotion(Potions.SLOWNESS))
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F))))
                                .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F))))
                                .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 7.0F))))
                        ).withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(4))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).setWeight(7).apply(EnchantRandomlyFunction.randomEnchantment()))
                                .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(UOItems.SAPPHIRE).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                                .add(LootItem.lootTableItem(UOItems.FROSTSTEEL_INGOT).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))
                                .add(LootItem.lootTableItem(UOItems.GLACIUM_CRYSTAL).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(UOItems.GLACIUM_SHARD).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 7.0F))))
                                .add(LootItem.lootTableItem(UOItems.GLACIALITE_UPGRADE_SMITHING_TEMPLATE))
                        )
        );

        // Frost Dungeons
        output.accept(UOLootTables.FROST_DUNGEON_TREASURE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).setWeight(5).apply(EnchantRandomlyFunction.randomEnchantment()))
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))
                                .add(LootItem.lootTableItem(Blocks.BLUE_ICE).setWeight(4))
                                .add(LootItem.lootTableItem(Blocks.PACKED_ICE).setWeight(6))
                        ).withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(4))
                                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(Items.BONE).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                                .add(LootItem.lootTableItem(Items.BONE_MEAL).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F))))
                                .add(LootItem.lootTableItem(Blocks.BONE_BLOCK).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        )
        );

        output.accept(UOLootTables.FROST_DUNGEON_INTERSECTION,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(4))
                                .add(LootItem.lootTableItem(Items.BONE).setWeight(4))
                                .add(LootItem.lootTableItem(Items.BONE_MEAL).setWeight(2))
                                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(3))
                        ).withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).setWeight(7).apply(EnchantRandomlyFunction.randomEnchantment()))
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))
                                .add(LootItem.lootTableItem(Items.EMERALD).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(UOItems.GLACIALITE_UPGRADE_SMITHING_TEMPLATE).setWeight(1))
                        )
        );
    }
}
