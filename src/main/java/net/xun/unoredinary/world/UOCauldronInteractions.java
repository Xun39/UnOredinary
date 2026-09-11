package net.xun.unoredinary.world;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.xun.unoredinary.registry.UOBlocks;
import net.xun.unoredinary.registry.UOItems;

import java.util.Map;

public class UOCauldronInteractions {
    public static final CauldronInteraction.InteractionMap CRYOPLASM = CauldronInteraction.newInteractionMap("cryoplasm");
    public static final CauldronInteraction FILL_CRYOPLASM = (blockState, level, blockPos, player, hand, stack) ->
            CauldronInteraction.emptyBucket(
                    level,
                    blockPos,
                    player,
                    hand,
                    stack,
                    UOBlocks.CRYOPLASM_CAULDRON.get().defaultBlockState(),
                    SoundEvents.BUCKET_EMPTY
            );

    public static void bootstrap() {
        Map<Item, CauldronInteraction> map1 = CRYOPLASM.map();
        CauldronInteraction.EMPTY.map().put(UOItems.CRYOPLASM_BUCKET.get(), FILL_CRYOPLASM);
        map1.put(
                Items.BUCKET,
                (blockState, level, blockPos, player, hand, stack) -> CauldronInteraction.fillBucket(
                        blockState,
                        level,
                        blockPos,
                        player,
                        hand,
                        stack,
                        new ItemStack(UOItems.CRYOPLASM_BUCKET.get()),
                        state -> true,
                        SoundEvents.BUCKET_FILL
                )
        );
    }
}
