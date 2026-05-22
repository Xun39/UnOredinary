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
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.xun.unoredinary.registry.*;
import net.xun.unoredinary.world.loot.UOLootTableKeys;

import java.util.function.BiConsumer;

public record UOChestLoot(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {

        output.accept(UOLootTableKeys.FROZEN_VAULT,
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
        output.accept(UOLootTableKeys.FROST_DUNGEON_CENTER,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(4.0F))
                                .add(LootItem.lootTableItem(UOBlocks.COBBLED_POLAR_STONE).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0F, 16.0F))))
                                .add(LootItem.lootTableItem(Items.COAL).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 9.0F))))
                                .add(LootItem.lootTableItem(UOItems.FROSTSTEEL_NUGGET).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                                .add(LootItem.lootTableItem(UOItems.FROSTSTEEL_INGOT).setWeight(8))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).setWeight(5).apply(EnchantRandomlyFunction.randomEnchantment()))
                        )
        );

        output.accept(UOLootTableKeys.FROST_DUNGEON_GRAND_HALL,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(Items.SADDLE).setWeight(20))
                                .add(LootItem.lootTableItem(Items.NAME_TAG).setWeight(15))
                                .add(LootItem.lootTableItem(UOItems.SAPPHIRE).setWeight(12))
                                .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(10))
                                .add(LootItem.lootTableItem(Items.DIAMOND_HORSE_ARMOR).setWeight(8))
                                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(8))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(5))
                                .add(LootItem.lootTableItem(UOItems.GLACIUM_SHARD).setWeight(5))
                                .add(LootItem.lootTableItem(UOItems.FROST_KEY))
                        )
        );

        output.accept(UOLootTableKeys.FROST_DUNGEON_VAULT_REWARD,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(NestedLootTable.lootTableReference(UOLootTableKeys.FROST_DUNGEON_VAULT_REWARD_RARE).setWeight(8))
                                .add(NestedLootTable.lootTableReference(UOLootTableKeys.FROST_DUNGEON_VAULT_REWARD_COMMON).setWeight(2))
                        ).withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 3.0F))
                                .add(NestedLootTable.lootTableReference(UOLootTableKeys.FROST_DUNGEON_VAULT_REWARD_COMMON))
                        ).withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(LootItemRandomChanceCondition.randomChance(0.50F))
                                .add(NestedLootTable.lootTableReference(UOLootTableKeys.FROST_DUNGEON_VAULT_REWARD_UNIQUE))
                        )
        );

        // TODO: change glacium shard to Frost Revenant's drop
        output.accept(UOLootTableKeys.FROST_DUNGEON_VAULT_REWARD_COMMON,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(UOItems.CRYIC_POWDER).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 12.0F))))
                                .add(LootItem.lootTableItem(Items.TIPPED_ARROW).setWeight(4)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 12.0F)))
                                        .apply(SetPotionFunction.setPotion(UOPotions.STRONG_FROSTBITTEN)))
                                .add(LootItem.lootTableItem(UOItems.FROSTSTEEL_NUGGET).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(9.0F, 18.0F))))
                                .add(LootItem.lootTableItem(UOItems.GLACIUM_SHARD).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(UOItems.FROSTSTEEL_INGOT).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        )
        );

        output.accept(UOLootTableKeys.FROST_DUNGEON_VAULT_REWARD_RARE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(UOItems.GLACIUM_SHARD).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                                .add(LootItem.lootTableItem(Items.SHIELD).setWeight(3).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.5F, 1.0F))))
                                .add(LootItem.lootTableItem(UOTools.FROSTSTEEL.getAxe().get()).setWeight(2)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(15.0F, 25.0F)))
                                )
                                .add(LootItem.lootTableItem(UOArmors.FROSTSTEEL.getHelmet().get()).setWeight(2)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(15.0F, 25.0F)))
                                )
                                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).setWeight(2).apply(EnchantRandomlyFunction.randomEnchantment()))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).setWeight(2).apply(EnchantRandomlyFunction.randomEnchantment()))
                                .add(LootItem.lootTableItem(Items.DIAMOND_CHESTPLATE).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(15.0F, 30.0F))))
                                .add(LootItem.lootTableItem(Items.DIAMOND_BOOTS).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(15.0F, 30.0F))))
                        )
        );

        // TODO: add new weight 1 entry with Glacialite Shield
        output.accept(UOLootTableKeys.FROST_DUNGEON_VAULT_REWARD_UNIQUE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(4))
                                .add(LootItem.lootTableItem(UOItems.GLACIUM_CRYSTAL).setWeight(3))
                                .add(LootItem.lootTableItem(UOItems.GLACIALITE_INGOT).setWeight(2))
                                .add(LootItem.lootTableItem(UOItems.GLACIALITE_UPGRADE_SMITHING_TEMPLATE).setWeight(2))
                        )
        );

        output.accept(UOLootTableKeys.FROST_DUNGEON_SPAWNER_CONSUMABLES,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.BAKED_POTATO).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                                .add(LootItem.lootTableItem(Items.COOKED_COD).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(Items.GOLDEN_CARROT).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.POTION)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                        .apply(SetPotionFunction.setPotion(Potions.REGENERATION)))
                                .add(LootItem.lootTableItem(Items.POTION)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                        .apply(SetPotionFunction.setPotion(UOPotions.WARMTH)))
                        )
        );

        output.accept(UOLootTableKeys.FROST_DUNGEON_SPAWNER_KEY,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(UOItems.FROST_KEY))
                        )
        );

        output.accept(UOLootTableKeys.FROST_DUNGEON_INTERSECTION,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).setWeight(15).apply(EnchantRandomlyFunction.randomEnchantment()))
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))
                                .add(LootItem.lootTableItem(Items.EMERALD).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(4))
                                .add(LootItem.lootTableItem(UOItems.SAPPHIRE).setWeight(3))
                                .add(LootItem.lootTableItem(Items.GOLDEN_CARROT).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(UOItems.GLACIALITE_UPGRADE_SMITHING_TEMPLATE).setWeight(1))
                                .add(LootItem.lootTableItem(UOItems.GLACIALITE_INGOT).setWeight(1))
                        ).withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(4))
                                .add(LootItem.lootTableItem(Items.BONE).setWeight(20))
                                .add(LootItem.lootTableItem(Items.BONE_MEAL).setWeight(20))
                                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(20))
                                .add(LootItem.lootTableItem(Items.STRING).setWeight(20))
                        )
        );

        output.accept(UOLootTableKeys.FROST_DUNGEON_TREASURE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(2.0F, 3.0F))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).setWeight(15).apply(EnchantRandomlyFunction.randomEnchantment()))
                                .add(LootItem.lootTableItem(Items.NAME_TAG).setWeight(10))
                                .add(LootItem.lootTableItem(Items.SADDLE).setWeight(10))
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))
                                .add(LootItem.lootTableItem(UOItems.SAPPHIRE).setWeight(3))
                        ).withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(4))
                                .add(LootItem.lootTableItem(Blocks.PACKED_ICE).setWeight(20))
                                .add(LootItem.lootTableItem(Blocks.BLUE_ICE).setWeight(15))
                                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(Items.BONE).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                                .add(LootItem.lootTableItem(Items.STRING).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.BONE_MEAL).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F))))
                                .add(LootItem.lootTableItem(Blocks.BONE_BLOCK).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.RABBIT_FOOT).setWeight(1))
                        )
        );

        output.accept(UOLootTableKeys.FROST_DUNGEON_MONSTER_ROOM,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 3.0F))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).setWeight(15).apply(EnchantRandomlyFunction.randomEnchantment()))
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))
                                .add(LootItem.lootTableItem(Items.EMERALD).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(4))
                                .add(LootItem.lootTableItem(UOItems.SAPPHIRE).setWeight(3))
                                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(2))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(1))
                        ).withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(5))
                                .add(LootItem.lootTableItem(Items.GUNPOWDER).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))
                                .add(LootItem.lootTableItem(Items.BONE_MEAL).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F))))
                                .add(LootItem.lootTableItem(Items.STRING).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 5.0F))))
                                .add(LootItem.lootTableItem(Items.COAL).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.LEATHER).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                                .add(LootItem.lootTableItem(Items.RABBIT_HIDE).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F))))
                        )
        );
    }
}