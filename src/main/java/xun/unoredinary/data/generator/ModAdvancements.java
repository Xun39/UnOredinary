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

            AdvancementHolder getRuby = Advancement.Builder.advancement().parent(getAdv(storyLoc("mine_diamond"))).display(
                            ModItems.RUBY,
                            Component.translatable("advancement.unoredinary.get_ruby"),
                            Component.translatable("advancement.unoredinary.get_ruby.desc"),
                            null, AdvancementType.TASK,
                            true, true, false)
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("get_ruby",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBY))
                    .save(consumer, "unoredinary:get_ruby");

            AdvancementHolder getHemocrylic = Advancement.Builder.advancement().parent(getAdv(storyLoc("iron_tools"))).display(
                            ModBlocks.HEMOCRYLIC_ORE,
                            Component.translatable("advancement.unoredinary.get_hemocrylic"),
                            Component.translatable("advancement.unoredinary.get_hemocrylic.desc"),
                            null, AdvancementType.TASK,
                            true, true, false)
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("has_hemocrylic_shards",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HEMOCRYLIC_SHARD))
                    .save(consumer, "unoredinary:root_fp");

            AdvancementHolder getFrosteel = Advancement.Builder.advancement().parent(getHemocrylic).display(
                            ModItems.FROSTEEL_INGOT,
                            Component.translatable("advancement.unoredinary.get_frosteel"),
                            Component.translatable("advancement.unoredinary.get_frosteel.desc"),
                            null, AdvancementType.TASK,
                            true, true, false)
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("get_frosteel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FROSTEEL_INGOT))
                    .save(consumer, "unoredinary:get_frosteel");
        }

        private String storyLoc(String suffix) {
            return "story/" + suffix;
        }

        protected AdvancementHolder getAdv(String loc) {
            return Advancement.Builder.advancement().build(ResourceLocation.withDefaultNamespace(loc));
        }
    }
}
