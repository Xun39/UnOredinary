package xun.unoredinary.data.generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.registry.ModItems;
import xun.unoredinary.world.modifier.loot.AddItemModifier;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifiers extends GlobalLootModifierProvider {

    public ModGlobalLootModifiers(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, UnOredinary.MOD_ID);
    }

    @Override
    protected void start() {

        this.add("ruby_horse_armor_in_fortress",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/nether_bridge")).build(),
                        LootItemRandomChanceCondition.randomChance(0.5F).build()
                }, ModItems.RUBY_HORSE_ARMOR.get())
        );

        this.add("ruby_horse_armor_in_ancient_city",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/ancient_city")).build(),
                        LootItemRandomChanceCondition.randomChance(0.75F).build()
                }, ModItems.RUBY_HORSE_ARMOR.get())
        );
    }
}
