package net.xun.unoredinary.data.generator;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.registry.UOItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class UOAdvancements extends AdvancementProvider {

    public UOAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new AdvancementGenerator()));
    }

    public static class AdvancementGenerator implements AdvancementProvider.AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {

            AdvancementHolder getCryicFragment = Advancement.Builder.advancement()
                    .parent(vanillaAdvancement(storyLoc("mine_stone")))
                    .display(
                            UOItems.CRYIC_POWDER,
                            Component.translatable("advancement.unoredinary.get_cryic_fragment"),
                            Component.translatable("advancement.unoredinary.get_cryic_fragment.desc"),
                            null, AdvancementType.TASK,
                            true, true, false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("get_cryic_fragment", InventoryChangeTrigger.TriggerInstance.hasItems(UOItems.CRYIC_POWDER))
                    .save(consumer, CommonUtils.namespacedID("get_cryic_fragment"));
        }

        private String storyLoc(String suffix) {
            return "story/" + suffix;
        }

        private AdvancementHolder vanillaAdvancement(String loc) {
            return Advancement.Builder.advancement().build(ResourceLocation.withDefaultNamespace(loc));
        }
    }
}
