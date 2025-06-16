package net.xun.unoredinary.world.loot;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.xun.lib.common.api.util.CommonUtils;

public class UOLootTables {

    public static final ResourceKey<LootTable> FROZEN_VAULT = createKey("chests/frozen_vault");

    private static ResourceKey<LootTable> createKey(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, CommonUtils.modLoc(name));
    }
}
