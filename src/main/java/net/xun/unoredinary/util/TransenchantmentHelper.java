package net.xun.unoredinary.util;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.neoforge.common.Tags;
import net.xun.lib.common.api.util.CommonUtils;

import java.util.Collection;
import java.util.List;

public final class TransenchantmentHelper {

    // Resource locations
    private static final ResourceLocation EMPTY_SLOT_HELMET = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_SWORD = ResourceLocation.withDefaultNamespace("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_AXE = ResourceLocation.withDefaultNamespace("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = ResourceLocation.withDefaultNamespace("item/empty_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = ResourceLocation.withDefaultNamespace("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_HOE = ResourceLocation.withDefaultNamespace("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_BOOK = CommonUtils.modLoc("item/empty_slot_book");

    // Level cost configuration
    public static final int BASE_COST = 2; // Base cost for having any enchantments
    public static final int PER_ENCHANTMENT_COST = 1; // Additional cost per enchantment

    public static boolean canTransenchant(ItemStack translator, ItemStack target) {
        if (translator.isEmpty() || target.isEmpty()) return false;
        if (!hasEnchantments(translator) || hasEnchantments(target)) return false;

        if (target.is(Items.BOOK)) {
            return !translator.is(Items.ENCHANTED_BOOK);
        }

        return target.is(Tags.Items.ENCHANTABLES) && enchantsCompatible(translator, target);
    }

    private static boolean enchantsCompatible(ItemStack stack1, ItemStack stack2) {
        Collection<Holder<Enchantment>> stack1Enchants = EnchantmentHelper.getEnchantmentsForCrafting(stack1).keySet();

        for (Holder<Enchantment> enchantment : stack1Enchants) {
            if (!stack2.supportsEnchantment(enchantment)) {
                return false;
            }
        }

        return true;
    }

    public static boolean hasEnchantments(ItemStack stack) {
        return !EnchantmentHelper.getEnchantmentsForCrafting(stack).isEmpty();
    }

    public static ItemStack createPreviewResult(ItemStack translator, ItemStack target) {
        ItemEnchantments enchantments = EnchantmentHelper.getEnchantmentsForCrafting(translator);

        if (target.is(Tags.Items.ENCHANTABLES)) {
            ItemStack resultStack = new ItemStack(target.getItem());

            EnchantmentHelper.setEnchantments(
                    resultStack,
                    enchantments
            );

            return resultStack;
        } else if (target.is(Items.BOOK)) {
            ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);

            EnchantmentHelper.setEnchantments(
                    enchantedBook,
                    enchantments
            );

            return enchantedBook;
        }

        return ItemStack.EMPTY;
    }

    public static void commitFullTransenchant(Player player, ItemStack translator, ItemStack target) {
        if (!player.isCreative()) {
            player.giveExperienceLevels(-calculateLevelCost(translator));
        }

        EnchantmentHelper.setEnchantments(translator, ItemEnchantments.EMPTY);

        if (target.is(Items.BOOK) && player.isCreative()) {
            return;
        }

        target.shrink(1);
    }

    public static int getEnchantmentsNumberTotal(ItemStack stack) {
        ItemEnchantments enchantments = EnchantmentHelper.getEnchantmentsForCrafting(stack);
        return enchantments.keySet().size();
    }

    /**
     * Calculates the level cost for transenchanting.
     * Formula:{@code
     * BASE_COST + (number_of_enchantments * PER_ENCHANTMENT_COST)
     * }
     */
    public static int calculateLevelCost(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        }

        int enchantmentCount = getEnchantmentsNumberTotal(stack);

        if (enchantmentCount == 0) {
            return 0;
        }

        return BASE_COST + (enchantmentCount * PER_ENCHANTMENT_COST);
    }

    public static List<ResourceLocation> getTranslationSlotEmptyIcons() {
        return List.of(
                EMPTY_SLOT_HELMET,
                EMPTY_SLOT_CHESTPLATE,
                EMPTY_SLOT_LEGGINGS,
                EMPTY_SLOT_BOOTS,
                EMPTY_SLOT_HOE,
                EMPTY_SLOT_AXE,
                EMPTY_SLOT_SWORD,
                EMPTY_SLOT_SHOVEL,
                EMPTY_SLOT_PICKAXE,
                EMPTY_SLOT_BOOK
        );
    }
}
