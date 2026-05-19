package net.xun.unoredinary.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerConfig;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.xun.unoredinary.registry.UOItems;
import net.xun.unoredinary.world.loot.UOLootTableKeys;

// Just convenient stuffs
public class CustomTrialStuffs {
    public static ItemStack createFrostDungeonTrialSpawner() {
        TrialSpawnerConfig config = new TrialSpawnerConfig(
                4,
                6.0F,
                2.0F,
                2.0F,
                1.0F,
                40,
                SimpleWeightedRandomList.empty(),
                SimpleWeightedRandomList.<ResourceKey<LootTable>>builder()
                        .add(UOLootTableKeys.FROST_DUNGEON_SPAWNER_CONSUMABLES)
                        .add(UOLootTableKeys.FROST_DUNGEON_SPAWNER_KEY)
                        .build(),
                BuiltInLootTables.SPAWNER_TRIAL_ITEMS_TO_DROP_WHEN_OMINOUS
        );

        CompoundTag configTag =  (CompoundTag) TrialSpawnerConfig.CODEC
                .encodeStart(NbtOps.INSTANCE, config)
                .getOrThrow();

        CompoundTag blockEntityTag = new CompoundTag();

        blockEntityTag.putString("id", "minecraft:trial_spawner");
        blockEntityTag.put("normal_config", configTag);
        blockEntityTag.put("ominous_config", configTag.copy());

        ItemStack stack = new ItemStack(Blocks.TRIAL_SPAWNER);

        stack.set(DataComponents.BLOCK_ENTITY_DATA, CustomData.of(blockEntityTag));
        stack.set(DataComponents.CUSTOM_NAME, Component.translatable("trial_spawner.unoredinary.frost_dungeon"));

        return stack;
    }

    public static ItemStack createFrostDungeonVault() {
        CompoundTag configTag = new CompoundTag();

        configTag.putString("loot_table", UOLootTableKeys.FROST_DUNGEON_VAULT_REWARD.location().toString());

        configTag.putDouble("activation_range", 4.0);
        configTag.putDouble("deactivation_range", 4.5);

        Tag keyTag = ItemStack.CODEC.encodeStart(NbtOps.INSTANCE, new ItemStack(UOItems.FROST_KEY.get())).getOrThrow();
        configTag.put("key_item", keyTag);

        CompoundTag blockEntityTag = new CompoundTag();
        blockEntityTag.putString("id", "minecraft:vault");
        blockEntityTag.put("config", configTag);

        ItemStack stack = new ItemStack(Blocks.VAULT);
        stack.set(DataComponents.BLOCK_ENTITY_DATA, CustomData.of(blockEntityTag));
        stack.set(DataComponents.CUSTOM_NAME, Component.translatable("vault.unoredinary.frost_dungeon"));

        return stack;
    }
}
