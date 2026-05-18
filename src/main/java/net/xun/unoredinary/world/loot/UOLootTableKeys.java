package net.xun.unoredinary.world.loot;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.xun.lib.common.api.util.CommonUtils;

public class UOLootTableKeys {

    // Frozen Vault
    public static final ResourceKey<LootTable> FROZEN_VAULT = createKey("chests/frozen_vault");

    // Frost Dungeon
    public static final ResourceKey<LootTable> FROST_DUNGEON_CENTER = createKey("chests/frost_dungeon/center");
    public static final ResourceKey<LootTable> FROST_DUNGEON_GRAND_HALL = createKey("chests/frost_dungeon/grand_hall");
    public static final ResourceKey<LootTable> FROST_DUNGEON_INTERSECTION = createKey("chests/frost_dungeon/intersection");
    public static final ResourceKey<LootTable> FROST_DUNGEON_TREASURE = createKey("chests/frost_dungeon/treasure");
    public static final ResourceKey<LootTable> FROST_DUNGEON_MONSTER_ROOM = createKey("chests/frost_dungeon/monster_room");

    public static final ResourceKey<LootTable> FROST_DUNGEON_VAULT_REWARD = createKey("chests/frost_dungeon/vault_reward");
    public static final ResourceKey<LootTable> FROST_DUNGEON_VAULT_REWARD_COMMON = createKey("chests/frost_dungeon/vault_reward_common");
    public static final ResourceKey<LootTable> FROST_DUNGEON_VAULT_REWARD_RARE = createKey("chests/frost_dungeon/vault_reward_rare");
    public static final ResourceKey<LootTable> FROST_DUNGEON_VAULT_REWARD_UNIQUE = createKey("chests/frost_dungeon/vault_reward_unique");

    private static ResourceKey<LootTable> createKey(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, CommonUtils.modLoc(name));
    }
}
