package net.xun.unoredinary.data.generator;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.registry.*;
import net.xun.unoredinary.util.UOTags;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class UOAdvancements extends AdvancementProvider {

    public UOAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new AdvancementGenerator()));
    }

    public static class AdvancementGenerator implements AdvancementProvider.AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
            var biomes = registries.lookup(Registries.BIOME).orElseThrow();
            var structures = registries.lookup(Registries.STRUCTURE).orElseThrow();

            // Root: Story
            AdvancementHolder mineCryic = Advancement.Builder.advancement()
                    .parent(vanillaAdvancement(storyLoc("upgrade_tools")))
                    .display(
                            UOItems.CRYIC_POWDER,
                            Component.translatable("advancement.unoredinary.mine_cryic"),
                            Component.translatable("advancement.unoredinary.mine_cryic.desc"),
                            null, AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("mine_cryic", InventoryChangeTrigger.TriggerInstance.hasItems(UOItems.CRYIC_POWDER))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(consumer, CommonUtils.namespacedID("mine_cryic"));

            AdvancementHolder forgeFroststeel = Advancement.Builder.advancement()
                    .parent(mineCryic)
                    .display(
                            UOItems.FROSTSTEEL_INGOT,
                            Component.translatable("advancement.unoredinary.forge_froststeel"),
                            Component.translatable("advancement.unoredinary.forge_froststeel.desc"),
                            null, AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("froststeel_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(UOItems.FROSTSTEEL_INGOT))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(consumer, CommonUtils.namespacedID("forge_froststeel"));

            AdvancementHolder froststeelTools = Advancement.Builder.advancement()
                    .parent(forgeFroststeel)
                    .display(
                            UOTools.FROSTSTEEL.getAxe().get(),
                            Component.translatable("advancement.unoredinary.froststeel_tools"),
                            Component.translatable("advancement.unoredinary.froststeel_tools.desc"),
                            null, AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("froststeel_axe", InventoryChangeTrigger.TriggerInstance.hasItems(UOTools.FROSTSTEEL.getAxe().get()))
                    .addCriterion("froststeel_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(UOTools.FROSTSTEEL.getPickaxe().get()))
                    .addCriterion("froststeel_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(UOTools.FROSTSTEEL.getHoe().get()))
                    .addCriterion("froststeel_shovel", InventoryChangeTrigger.TriggerInstance.hasItems(UOTools.FROSTSTEEL.getShovel().get()))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(consumer, CommonUtils.namespacedID("froststeel_tools"));

            AdvancementHolder mineGlacium = Advancement.Builder.advancement()
                    .parent(vanillaAdvancement(storyLoc("mine_diamond")))
                    .display(
                            UOItems.GLACIUM_SHARD,
                            Component.translatable("advancement.unoredinary.mine_glacium"),
                            Component.translatable("advancement.unoredinary.mine_glacium.desc"),
                            null, AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("glacium_shard", InventoryChangeTrigger.TriggerInstance.hasItems(UOItems.GLACIUM_SHARD))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(consumer, CommonUtils.namespacedID("mine_glacium"));

            AdvancementHolder getGlaciumCrystal = Advancement.Builder.advancement()
                    .parent(mineGlacium)
                    .display(
                            UOItems.GLACIUM_CRYSTAL,
                            Component.translatable("advancement.unoredinary.get_glacium_crystal"),
                            Component.translatable("advancement.unoredinary.get_glacium_crystal.desc"),
                            null, AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("glacium_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(UOItems.GLACIUM_CRYSTAL))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(consumer, CommonUtils.namespacedID("get_glacium_crystal"));

            AdvancementHolder craftGlacialite = Advancement.Builder.advancement()
                    .parent(mineGlacium)
                    .display(
                            UOItems.GLACIALITE_INGOT,
                            Component.translatable("advancement.unoredinary.craft_glacialite"),
                            Component.translatable("advancement.unoredinary.craft_glacialite.desc"),
                            null, AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("glacialite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(UOItems.GLACIALITE_INGOT))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(consumer, CommonUtils.namespacedID("craft_glacialite"));

            // Root: Adventure
            AdvancementHolder findIceberg = Advancement.Builder.advancement()
                    .parent(vanillaAdvancement(adventureLoc("sleep_in_bed")))
                    .display(
                            UOArmors.FROSTSTEEL.getBoots().get(),
                            Component.translatable("advancement.unoredinary.find_iceberg"),
                            Component.translatable("advancement.unoredinary.find_iceberg.desc"),
                            null, AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("frozen_ocean", PlayerTrigger.TriggerInstance.located(
                            LocationPredicate.Builder.location()
                                    .setBiomes(HolderSet.direct(biomes.getOrThrow(Biomes.FROZEN_OCEAN)))
                                    .setCanSeeSky(true))
                    )
                    .addCriterion("deep_frozen_ocean", PlayerTrigger.TriggerInstance.located(
                            LocationPredicate.Builder.location()
                                    .setBiomes(HolderSet.direct(biomes.getOrThrow(Biomes.DEEP_FROZEN_OCEAN)))
                                    .setCanSeeSky(true))
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(consumer, CommonUtils.namespacedID("find_iceberg"));

            AdvancementHolder freezeMiner = Advancement.Builder.advancement()
                    .parent(findIceberg)
                    .display(
                            UOBlocks.GLACIUM_ORE,
                            Component.translatable("advancement.unoredinary.freeze_miner"),
                            Component.translatable("advancement.unoredinary.freeze_miner.desc"),
                            null, AdvancementType.GOAL,
                            true, true, false
                    )
                    .addCriterion("mine_twenty", PlayerTrigger.TriggerInstance.located(
                            Optional.of(
                                    EntityPredicate.Builder.entity().subPredicate(
                                            PlayerPredicate.Builder.player()
                                                    .addStat(
                                                            Stats.BLOCK_MINED,
                                                            registries.lookupOrThrow(Registries.BLOCK).getOrThrow(UOBlocks.GLACIUM_ORE.getKey()),
                                                            MinMaxBounds.Ints.atLeast(20))
                                                    .build())
                                            .build())
                            )
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(consumer, CommonUtils.namespacedID("freeze_miner"));

            /* AdvancementHolder findFrostDungeon = Advancement.Builder.advancement()
                    .parent(vanillaAdvancement(adventureLoc("root")))
                    .display(
                            Blocks.BLUE_ICE,
                            Component.translatable("advancement.unoredinary.frost_dungeon"),
                            Component.translatable("advancement.unoredinary.frost_dungeon.desc"),
                            null, AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("frost_dungeon", PlayerTrigger.TriggerInstance.located(
                            LocationPredicate.Builder.location()
                                    .setStructures(HolderSet.direct(structures.getOrThrow(UOStructures.FROST_DUNGEON))))
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(consumer, "unoredinary:frost_dungeon");
             */
        }

        private String storyLoc(String suffix) {
            return "story/" + suffix;
        }
        private String adventureLoc(String suffix) {
            return "adventure/" + suffix;
        }

        private AdvancementHolder vanillaAdvancement(String loc) {
            return Advancement.Builder.advancement().build(ResourceLocation.withDefaultNamespace(loc));
        }
    }
}
