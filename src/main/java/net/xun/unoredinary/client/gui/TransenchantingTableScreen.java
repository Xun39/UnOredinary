package net.xun.unoredinary.client.gui;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CyclingSlotBackground;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.block.entity.container.TransenchantingTableMenu;
import net.xun.unoredinary.util.TransenchantmentHelper;

import java.awt.*;
import java.util.*;
import java.util.List;

public class TransenchantingTableScreen extends AbstractContainerScreen<TransenchantingTableMenu> {
    private static final ResourceLocation BACKGROUND_TEXTURE = CommonUtils.modLoc("textures/gui/transenchanting_table.png");

    // Screen layout
    private static final Rectangle INFO_AREA = new Rectangle(41, 18, 15, 53);
    private static final Rectangle MODEL_SLOT_1 = new Rectangle(64, 25, 26, 36);
    private static final Rectangle MODEL_SLOT_2 = new Rectangle(105, 25, 26, 36);

    // Texture sources
    private static final Rectangle EXPERIENCE_COST_ROW = new Rectangle(0, 182, 256, 16);
    private static final Rectangle ENCHANTMENTS_NUMBER_ROW = new Rectangle(0, 198, 256, 16);

    // Constant screen values
    private static final int ICON_SIZE = 16;
    private static final int MAX_VISIBLE_ENCHANTMENTS = 15;
    private static final int TEXTURE_SIZE = 256;

    // Individual textures
    private static final Rectangle CHECK_MARK = new Rectangle(17, 166, 16, 16);
    private static final Rectangle CROSS_MARK = new Rectangle(0, 166, 16, 16);
    private static final Rectangle ENCHANTMENTS_NUMBER_ICON = new Rectangle(INFO_AREA.x, INFO_AREA.y, ICON_SIZE, ICON_SIZE);
    private static final Rectangle LEVEL_COST_ICON = new Rectangle(INFO_AREA.x, INFO_AREA.y + ICON_SIZE, ICON_SIZE, ICON_SIZE);
    private static final Rectangle MARK_ICON = new Rectangle(INFO_AREA.x, INFO_AREA.y + 2 * ICON_SIZE, ICON_SIZE, ICON_SIZE);

    private final CyclingSlotBackground transenchantIcon = new CyclingSlotBackground(1);
    private Rectangle lastHoveredArea = null;

    public TransenchantingTableScreen(TransenchantingTableMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.transenchantIcon.tick(TransenchantmentHelper.getTranslationSlotEmptyIcons());
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = leftPos;
        int y = topPos;

        guiGraphics.blit(BACKGROUND_TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight, TEXTURE_SIZE, TEXTURE_SIZE);
        transenchantIcon.render(this.menu, guiGraphics, partialTick, this.leftPos, this.topPos);

        renderItemInfo(guiGraphics, partialTick);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        renderInfoAreaTooltips(guiGraphics, mouseX, mouseY);
    }

    private void renderItemInfo(GuiGraphics guiGraphics, float partialTick) {

        ItemStack translatorSlotStack = this.menu.inventory.getStackInSlot(0);
        ItemStack transenchantSlotStack = this.menu.inventory.getStackInSlot(1);

        if (translatorSlotStack.isEmpty()) {
            return;
        }

        int enchantmentsNumber = TransenchantmentHelper.getEnchantmentsNumberTotal(translatorSlotStack);

        if (enchantmentsNumber > 0) {
            int levelCostForIcon = TransenchantmentHelper.calculateLevelCost(translatorSlotStack);

            int enchantmentsIconX = calculateEnchantmentsIconX(enchantmentsNumber);
            int levelCostIconX = calculateLevelCostIconX(levelCostForIcon);

            renderInfoIcons(guiGraphics, enchantmentsIconX, levelCostIconX, translatorSlotStack, transenchantSlotStack);
        }

        render3DModels(guiGraphics, translatorSlotStack, transenchantSlotStack, partialTick);
    }

    private int calculateEnchantmentsIconX(int enchantmentsNumber) {
        if (enchantmentsNumber <= 0) {
            return ENCHANTMENTS_NUMBER_ROW.x + (TEXTURE_SIZE - ICON_SIZE);
        }

        int xOffset = (enchantmentsNumber - 1) * ICON_SIZE;

        return ENCHANTMENTS_NUMBER_ROW.x + Math.min(xOffset, (MAX_VISIBLE_ENCHANTMENTS - 1) * ICON_SIZE);
    }

    private int calculateLevelCostIconX(int levelCostForIcon) {
        if (levelCostForIcon <= 0) {
            return TEXTURE_SIZE;
        }

        int iconIndex = Math.min(levelCostForIcon, MAX_VISIBLE_ENCHANTMENTS) - 1;
        return EXPERIENCE_COST_ROW.x + (iconIndex * ICON_SIZE);
    }

    private void renderInfoIcons(GuiGraphics guiGraphics, int enchantmentsIconX, int levelCostIconX, ItemStack translator, ItemStack target) {
        int screenX = leftPos + INFO_AREA.x;
        int screenY = topPos + INFO_AREA.y;

        // Enchantments number icon
        guiGraphics.blit(BACKGROUND_TEXTURE, screenX, screenY,
                enchantmentsIconX, ENCHANTMENTS_NUMBER_ROW.y,
                ICON_SIZE, ICON_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);

        // Level cost icon
        guiGraphics.blit(BACKGROUND_TEXTURE, screenX, screenY + ICON_SIZE,
                levelCostIconX, EXPERIENCE_COST_ROW.y,
                ICON_SIZE, ICON_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);

        // Check/cross mark
        Rectangle markIcon = TransenchantmentHelper.canTransenchant(translator, target) ? CHECK_MARK : CROSS_MARK;

        guiGraphics.blit(BACKGROUND_TEXTURE, screenX, screenY + 2 * ICON_SIZE,
                markIcon.x, markIcon.y,
                ICON_SIZE, ICON_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
    }

    private void render3DModels(GuiGraphics guiGraphics, ItemStack translator, ItemStack target, float partialTick) {
        Lighting.setupFor3DItems();

        try {
            render3DItemModel(guiGraphics, translator, partialTick, MODEL_SLOT_1);
            render3DItemModel(guiGraphics, target, partialTick, MODEL_SLOT_2);

            guiGraphics.bufferSource().endBatch();
        } finally {
            Lighting.setupForFlatItems(); // Reset to flat items lighting
        }
    }

    private void render3DItemModel(GuiGraphics guiGraphics, ItemStack stack, float partialTick, Rectangle bounds) {
        if (stack.isEmpty() || stack.is(Items.AIR)) {
            return;
        }

        var poseStack = guiGraphics.pose();
        poseStack.pushPose();

        // Calculate center of the slot
        int centerX = leftPos + bounds.x + bounds.width / 2;
        int centerY = topPos + bounds.y + bounds.height / 2;

        poseStack.translate(centerX, centerY, 150.0F);
        poseStack.scale(20.0F, -20.0F, 20.0F);

        // Animate rotation
        float rotation = (Objects.requireNonNull(minecraft).level.getGameTime() + partialTick) % 360;
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));

        // Render item
        minecraft.getItemRenderer().renderStatic(
                stack,
                ItemDisplayContext.GUI,
                0xF000F0,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                guiGraphics.bufferSource(),
                minecraft.level,
                0
        );

        poseStack.popPose();
    }

    private void renderInfoAreaTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        ItemStack translatorSlotStack = this.menu.inventory.getStackInSlot(0);
        ItemStack transenchantSlotStack = this.menu.inventory.getStackInSlot(1);

        if (translatorSlotStack.isEmpty()) {
            return;
        }

        int enchantmentsNumber = TransenchantmentHelper.getEnchantmentsNumberTotal(translatorSlotStack);

        // Reset hover tracking
        lastHoveredArea = null;

        // Check the whole area at first
        if (isMouseOverArea(mouseX, mouseY, INFO_AREA)) {
            if (enchantmentsNumber == 0) {
                renderNoEnchantmentsTooltip(guiGraphics, mouseX, mouseY);
            }
        } else {
            return;
        }

        // Check each icon area for hover
        if (isMouseOverArea(mouseX, mouseY, ENCHANTMENTS_NUMBER_ICON)) {
            // Only show enchantment number tooltip if there are enchantments
            if (enchantmentsNumber > 0) {
                renderEnchantmentsNumberTooltip(guiGraphics, mouseX, mouseY, translatorSlotStack);
                lastHoveredArea = ENCHANTMENTS_NUMBER_ICON;
            }
        } else if (isMouseOverArea(mouseX, mouseY, LEVEL_COST_ICON)) {
            // Only show level cost tooltip if there are enchantments
            if (enchantmentsNumber > 0) {
                renderLevelCostTooltip(guiGraphics, mouseX, mouseY, translatorSlotStack);
                lastHoveredArea = LEVEL_COST_ICON;
            }
        } else if (isMouseOverArea(mouseX, mouseY, MARK_ICON)) {
            // Only show mark tooltip if there are enchantments
            if (enchantmentsNumber > 0) {
                renderMarkTooltip(guiGraphics, mouseX, mouseY, translatorSlotStack, transenchantSlotStack);
                lastHoveredArea = MARK_ICON;
            }
        }
    }

    private boolean isMouseOverArea(int mouseX, int mouseY, Rectangle area) {
        int screenX = leftPos + area.x;
        int screenY = topPos + area.y;

        return mouseX >= screenX && mouseX < screenX + area.width &&
                mouseY >= screenY && mouseY < screenY + area.height;
    }

    private void renderEnchantmentsNumberTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY, ItemStack translator) {
        if (translator.isEmpty()) {
            return;
        }

        int enchantmentsNumber = TransenchantmentHelper.getEnchantmentsNumberTotal(translator);

        List<Component> tooltipLines = new ArrayList<>();
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.enchantments_number")
                .withStyle(ChatFormatting.GOLD));
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.enchantments_count",
                enchantmentsNumber).withStyle(ChatFormatting.GRAY));

        guiGraphics.renderTooltip(this.font, tooltipLines, Optional.empty(), mouseX, mouseY);
    }

    private void renderLevelCostTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY, ItemStack translator) {
        if (translator.isEmpty()) {
            return;
        }

        int enchantmentsNumber = TransenchantmentHelper.getEnchantmentsNumberTotal(translator);
        int levelCost = TransenchantmentHelper.calculateLevelCost(translator);

        List<Component> tooltipLines = new ArrayList<>();
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.level_cost").withStyle(ChatFormatting.GOLD));
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.levels", levelCost).withStyle(ChatFormatting.GRAY));

        // Show breakdown of cost calculation
        tooltipLines.add(Component.empty());
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.cost_breakdown").withStyle(ChatFormatting.DARK_GRAY));
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.base_cost", TransenchantmentHelper.BASE_COST).withStyle(ChatFormatting.GRAY));
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.per_enchantment_cost",
                        enchantmentsNumber,
                        TransenchantmentHelper.PER_ENCHANTMENT_COST,
                        enchantmentsNumber * TransenchantmentHelper.PER_ENCHANTMENT_COST)
                .withStyle(ChatFormatting.GRAY)
        );

        guiGraphics.renderTooltip(this.font, tooltipLines, Optional.empty(), mouseX, mouseY);
    }

    private void renderMarkTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY, ItemStack translator, ItemStack target) {
        if (translator.isEmpty()) {
            return;
        }

        boolean canTransenchant = TransenchantmentHelper.canTransenchant(translator, target);

        List<Component> tooltipLines = new ArrayList<>();

        if (canTransenchant) {
            tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.can_transenchant").withStyle(ChatFormatting.GREEN));

            if (!target.isEmpty()) {
                if (target.is(Items.BOOK)) {
                    tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.will_create_book").withStyle(ChatFormatting.GRAY));
                } else {
                    tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.will_transfer").withStyle(ChatFormatting.GRAY));
                }
            }
        } else {
            tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.cannot_transenchant").withStyle(ChatFormatting.RED));

            if (translator.isEmpty()) {
                tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.no_translator").withStyle(ChatFormatting.GRAY));
            } else if (target.isEmpty()) {
                tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.no_target").withStyle(ChatFormatting.GRAY));
            } else if (target.is(Items.BOOK)) {
                tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.translator_has_no_enchants").withStyle(ChatFormatting.GRAY));
            } else if (TransenchantmentHelper.hasEnchantments(target)) {
                tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.target_already_enchanted").withStyle(ChatFormatting.GRAY));
            } else {
                tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.incompatible").withStyle(ChatFormatting.GRAY));
            }
        }

        guiGraphics.renderTooltip(this.font, tooltipLines, Optional.empty(), mouseX, mouseY);
    }

    private void renderNoEnchantmentsTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        List<Component> tooltipLines = new ArrayList<>();
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.cannot_transenchant").withStyle(ChatFormatting.RED));
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.no_enchantments").withStyle(ChatFormatting.GRAY));

        guiGraphics.renderTooltip(this.font, tooltipLines, Optional.empty(), mouseX, mouseY);
    }
}
