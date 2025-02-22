package xun.unoredinary.data.generator;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import xun.unoredinary.registry.ModBlocks;
import xun.unoredinary.registry.ModEntityTypes;
import xun.unoredinary.registry.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancements extends AdvancementProvider {

    public ModAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new AdvancementGenerator()));
    }

    public static class AdvancementGenerator implements AdvancementProvider.AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {

            AdvancementHolder root = Advancement.Builder.advancement().display(
                            Blocks.IRON_ORE,
                            Component.translatable("advancement.unoredinary.root"),
                            Component.translatable("advancement.unoredinary.root.desc"),
                            ResourceLocation.withDefaultNamespace("textures/block/deepslate.png"),
                            AdvancementType.TASK,
                            false, false, true)
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("in_overworld",
                            PlayerTrigger.TriggerInstance.located(
                                    LocationPredicate.Builder.inDimension(Level.OVERWORLD)))
                    .save(consumer, "unoredinary:root");

            AdvancementHolder rootFrost = Advancement.Builder.advancement().parent(root).display(
                            ModBlocks.HEMOCRYLIC_ORE,
                            Component.translatable("advancement.unoredinary.fp/root"),
                            Component.translatable("advancement.unoredinary.fp/root.desc"),
                            ResourceLocation.withDefaultNamespace("textures/block/ice.png"),
                            AdvancementType.TASK,
                            true, true, false)
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("has_hemocrylic_shards",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEMOCRYLIC_SHARD))
                    .save(consumer, "unoredinary:root_fp");

            AdvancementHolder getFrosteel = Advancement.Builder.advancement().parent(rootFrost).display(
                            ModItems.FROSTEEL_INGOT,
                            Component.translatable("advancement.unoredinary.fp/get_frosteel"),
                            Component.translatable("advancement.unoredinary.fp/get_frosteel.desc"),
                            null, AdvancementType.TASK,
                            true, true, false)
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("get_frosteel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FROSTEEL_INGOT))
                    .save(consumer, "unoredinary:get_frosteel");

            AdvancementHolder killFrostZombie = Advancement.Builder.advancement().parent(rootFrost).display(
                            ModItems.FROSTEEL_SWORD,
                            Component.translatable("advancement.unoredinary.fp/kill_frost_zombie"),
                            Component.translatable("advancement.unoredinary.fp/kill_frost_zombie.desc"),
                            null, AdvancementType.TASK,
                            true, true, false)
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("kill_frost_zombie",
                            KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(ModEntityTypes.FROST_ZOMBIE.get())))
                    .save(consumer, "unoredinary:kill_frost_zombie");
        }
    }
}
