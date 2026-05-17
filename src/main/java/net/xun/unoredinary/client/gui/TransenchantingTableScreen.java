package net.xun.unoredinary.client.gui;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
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
import net.xun.lib.common.api.util.Area;
import net.xun.lib.common.api.util.CommonUtils;
import net.xun.unoredinary.block.entity.container.TransenchantingTableMenu;
import net.xun.unoredinary.util.TransenchantmentHelper;

import java.util.*;
import java.util.List;

public class TransenchantingTableScreen extends AbstractContainerScreen<TransenchantingTableMenu> {
    private static final ResourceLocation BACKGROUND_TEXTURE = CommonUtils.modLoc("textures/gui/transenchanting_table.png");

    // Screen layout
    private static final Area INFO_AREA = new Area(41, 18, 15, 53);
    private static final Area MODEL_SLOT_1 = new Area(64, 25, 26, 36);
    private static final Area MODEL_SLOT_2 = new Area(105, 25, 26, 36);

    // Constant screen values
    private static final int ICON_SIZE = 16;
    private static final int TEXTURE_SIZE = 256;

    // Individual textures
    private static final Area CHECK_MARK = new Area(17, 166, 16, 16);
    private static final Area CROSS_MARK = new Area(0, 166, 16, 16);
    private static final Area ENCHANTMENTS_NUMBER_ICON = new Area(INFO_AREA.x(), INFO_AREA.y(), ICON_SIZE, ICON_SIZE);
    private static final Area LEVEL_COST_ICON = new Area(INFO_AREA.x(), INFO_AREA.y() + ICON_SIZE, ICON_SIZE, ICON_SIZE);
    private static final Area MARK_ICON = new Area(INFO_AREA.x(), INFO_AREA.y() + 2 * ICON_SIZE, ICON_SIZE, ICON_SIZE);

    // 3D Item model constants
    private static final float MODEL_SCALE = 20.0F;
    private static final float MODEL_Z = 150.0F;
    private static final int TEXT_OUTLINE_COLOR = 0;
    private static final int FULL_BRIGHT = 0xF000F0;

    private final CyclingSlotBackground transenchantIcon = new CyclingSlotBackground(1);

    private int enchantmentCount;
    private int levelCost;
    private boolean canTransenchant;

    private Component enchantmentCountText = Component.empty();
    private Component levelCostText = Component.empty();

    public TransenchantingTableScreen(TransenchantingTableMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void containerTick() {
        super.containerTick();

        ItemStack transenchanter = menu.inventory.getStackInSlot(0);
        ItemStack target = menu.inventory.getStackInSlot(1);

        enchantmentCount = TransenchantmentHelper.getEnchantmentsNumberTotal(transenchanter);
        levelCost = TransenchantmentHelper.calculateLevelCost(transenchanter);
        canTransenchant = TransenchantmentHelper.canTransenchant(transenchanter, target);

        enchantmentCountText = Component.literal(Integer.toString(enchantmentCount));
        levelCostText = Component.literal(Integer.toString(levelCost));

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
        ItemStack transenchanterSlotStack = this.menu.inventory.getStackInSlot(0);
        ItemStack targetSlotStack = this.menu.inventory.getStackInSlot(1);

        if (transenchanterSlotStack.isEmpty()) {
            return;
        }

        if (enchantmentCount > 0) {
            renderInfoIcons(guiGraphics, transenchanterSlotStack, targetSlotStack);
        }

        render3DModels(guiGraphics, transenchanterSlotStack, targetSlotStack, partialTick);
    }

    private void renderInfoIcons(GuiGraphics guiGraphics, ItemStack translator, ItemStack target) {
        int screenX = leftPos + INFO_AREA.x();
        int screenY = topPos + INFO_AREA.y();

        // Enchantments number icon
        guiGraphics.blit(BACKGROUND_TEXTURE, screenX, screenY,
                49, 166,
                ICON_SIZE, ICON_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
        renderText(this.font, guiGraphics, screenX, screenY, enchantmentCountText, ChatFormatting.GREEN.getColor());

        // Level cost icon
        guiGraphics.blit(BACKGROUND_TEXTURE, screenX, screenY + ICON_SIZE,
                33, 166,
                ICON_SIZE, ICON_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
        renderText(this.font, guiGraphics, screenX, screenY + ICON_SIZE, levelCostText, ChatFormatting.GREEN.getColor());

        // Check/cross mark
        Area markIcon = TransenchantmentHelper.canTransenchant(translator, target) ? CHECK_MARK : CROSS_MARK;

        guiGraphics.blit(BACKGROUND_TEXTURE, screenX, screenY + 2 * ICON_SIZE,
                markIcon.x(), markIcon.y(),
                ICON_SIZE, ICON_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
    }

    private static void renderText(Font font, GuiGraphics guiGraphics, int posX, int posY, Component component, int color) {
        font.drawInBatch8xOutline(component.getVisualOrderText(),
                posX + (19 - 2 - font.width(component)),
                posY + (6 + 3),
                color,
                TEXT_OUTLINE_COLOR,
                guiGraphics.pose().last().pose(),
                guiGraphics.bufferSource(),
                FULL_BRIGHT);
    }

    private void render3DModels(GuiGraphics guiGraphics, ItemStack transenchanter, ItemStack target, float partialTick) {
        if (transenchanter.isEmpty() && target.isEmpty())
            return;

        Lighting.setupFor3DItems();

        try {
            render3DItemModel(guiGraphics, transenchanter, partialTick, MODEL_SLOT_1);
            render3DItemModel(guiGraphics, target, partialTick, MODEL_SLOT_2);

            guiGraphics.bufferSource().endBatch();
        } finally {
            Lighting.setupForFlatItems(); // Reset to flat items lighting
        }
    }

    private void render3DItemModel(GuiGraphics guiGraphics, ItemStack stack, float partialTick, Area bounds) {
        if (stack.isEmpty()) {
            return;
        }

        var poseStack = guiGraphics.pose();
        poseStack.pushPose();

        // Calculate center of the slot
        int centerX = leftPos + bounds.x() + bounds.width() / 2;
        int centerY = topPos + bounds.y() + bounds.height() / 2;

        poseStack.translate(centerX, centerY, MODEL_Z);
        poseStack.scale(MODEL_SCALE, -MODEL_SCALE, MODEL_SCALE);

        // Animate rotation
        float rotation = (minecraft.level.getGameTime() + partialTick) % 360;
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
        ItemStack transenchanterSlotStack = this.menu.inventory.getStackInSlot(0);
        ItemStack transenchantSlotStack = this.menu.inventory.getStackInSlot(1);

        if (transenchanterSlotStack.isEmpty()) {
            return;
        }

        // Check the whole area at first
        if (!INFO_AREA.contains(mouseX, mouseY, leftPos, topPos)) {
            return;
        }

        if (enchantmentCount == 0) {
            renderNoEnchantmentsTooltip(guiGraphics, mouseX, mouseY);
            return;
        }

        // Check each icon area for hover
        if (ENCHANTMENTS_NUMBER_ICON.contains(mouseX, mouseY, leftPos, topPos)) {
            renderEnchantmentsNumberTooltip(guiGraphics, mouseX, mouseY, transenchanterSlotStack);
        } else if (LEVEL_COST_ICON.contains(mouseX, mouseY, leftPos, topPos)) {
            renderLevelCostTooltip(guiGraphics, mouseX, mouseY, transenchanterSlotStack);
        } else if (MARK_ICON.contains(mouseX, mouseY, leftPos, topPos)) {
            renderMarkTooltip(guiGraphics, mouseX, mouseY, transenchanterSlotStack, transenchantSlotStack);
        }
    }

    private void renderEnchantmentsNumberTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY, ItemStack translator) {
        if (translator.isEmpty()) {
            return;
        }

        List<Component> tooltipLines = new ArrayList<>();
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.enchantments_number")
                .withStyle(ChatFormatting.GOLD));
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.enchantments_count", enchantmentCount).withStyle(ChatFormatting.GRAY));

        guiGraphics.renderTooltip(this.font, tooltipLines, Optional.empty(), mouseX, mouseY);
    }

    private void renderLevelCostTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY, ItemStack translator) {
        if (translator.isEmpty()) {
            return;
        }

        List<Component> tooltipLines = new ArrayList<>();
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.level_cost").withStyle(ChatFormatting.GOLD));
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.levels", levelCost).withStyle(ChatFormatting.GRAY));

        // Show breakdown of cost calculation
        tooltipLines.add(Component.empty());
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.cost_breakdown").withStyle(ChatFormatting.DARK_GRAY));
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.base_cost", TransenchantmentHelper.BASE_COST).withStyle(ChatFormatting.GRAY));
        tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.per_enchantment_cost",
                        enchantmentCount,
                        TransenchantmentHelper.PER_ENCHANTMENT_COST,
                        enchantmentCount / 2 * TransenchantmentHelper.PER_ENCHANTMENT_COST)
                .withStyle(ChatFormatting.GRAY)
        );

        guiGraphics.renderTooltip(this.font, tooltipLines, Optional.empty(), mouseX, mouseY);
    }

    private void renderMarkTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY, ItemStack translator, ItemStack target) {
        if (translator.isEmpty()) {
            return;
        }

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

            if (target.isEmpty()) {
                tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.no_target").withStyle(ChatFormatting.GRAY));
            } else if (target.is(Items.BOOK)) {
                tooltipLines.add(Component.translatable("unoredinary.tooltip.transenchanting_table.transenchanter_has_no_enchants").withStyle(ChatFormatting.GRAY));
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
