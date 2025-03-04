package xun.unoredinary.event;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.slf4j.Logger;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.content.item.tool.pickaxe.FrosteelPickItem;
import xun.unoredinary.content.item.tool.pickaxe.SolaritePickItem;
import xun.unoredinary.content.item.tool.shovel.SolariteShovelItem;
import xun.unoredinary.registry.ModItems;
import xun.unoredinary.util.BiomeUtils;
import xun.unoredinary.util.ModTags;

import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber(modid = UnOredinary.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ToolEvents {

    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    private static final float FROSTEEL_DAMAGE_FACTOR = 0.65F;
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onFrosteelPickaxeUsage(BlockEvent.BreakEvent event) {

        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if (!event.getState().is(BlockTags.ICE)) return;

        if(mainHandItem.getItem() instanceof FrosteelPickItem pickItem && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : FrosteelPickItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !pickItem.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    @SubscribeEvent
    public static void onSolariteToolUsage(BlockEvent.BreakEvent event) {

        Player player = event.getPlayer();
        BlockPos pos = event.getPos();
        BlockState state = event.getState();
        ItemStack mainHandItem = player.getMainHandItem();

        if (mainHandItem.getItem() instanceof SolaritePickItem pickItem && player instanceof ServerPlayer serverPlayer) {

            if (SolaritePickItem.SMELT_ORE_MAP.containsKey(state.getBlock())) {

                player.level().destroyBlock(pos, false);

                if (state.getBlock() == Blocks.COPPER_ORE || state.getBlock() == Blocks.DEEPSLATE_COPPER_ORE) {
                    ItemStack result = new ItemStack(SolaritePickItem.SMELT_ORE_MAP.get(state.getBlock()), (int) (Math.random() * 4) + 2);
                    Block.popResource(player.level(), pos, result);
                } else {
                    ItemStack result = new ItemStack(SolaritePickItem.SMELT_ORE_MAP.get(state.getBlock()));
                    Block.popResource(player.level(), pos, result);
                }
            }
        } else if (mainHandItem.getItem() instanceof SolariteShovelItem shovelItem && player instanceof ServerPlayer serverPlayer) {
            player.level().destroyBlock(pos, false);

            ItemStack result = new ItemStack(SolariteShovelItem.SMELT_MAP.get(state.getBlock()));
            Block.popResource(player.level(), pos, result);
        }
    }

    @SubscribeEvent
    public static void onFrosteelToolDamage(LivingDamageEvent.Pre event) {

        if (event.getEntity().level().isClientSide) return;
        if (!(event.getSource().getDirectEntity() instanceof Player player)) return;

        ItemStack weapon = player.getMainHandItem();

        if (weapon.isEmpty() || !weapon.is(ModTags.Items.FROSTEEL_TOOL)) {
            return;
        }

        BlockPos playerPos = player.blockPosition();
        Level level = player.level();

        float temperature = level.getBiome(playerPos).value().getBaseTemperature();
        temperature = Math.max(0.1f, temperature);

        float baseDamage = event.getOriginalDamage();
        float finalDamage = baseDamage;

        if (BiomeUtils.isInColdBiome(level, playerPos)) {
            float bonus = FROSTEEL_DAMAGE_FACTOR / temperature;
            finalDamage += bonus;
        }
        if (finalDamage != baseDamage) {
            event.setNewDamage(finalDamage);
        }
    }

    @SubscribeEvent
    public static void onShiftAndRightClick(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() == Blocks.BLUE_ICE) {

            if (!(player.getMainHandItem().getItem() instanceof PickaxeItem)) return;

            if (player.isShiftKeyDown() && player instanceof ServerPlayer) {
                event.setCanceled(true);

                ItemStack iceBrick = new ItemStack(ModItems.ICE_BRICK.get());
                ItemEntity itemEntity = new ItemEntity(level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5, iceBrick);

                level.addFreshEntity(itemEntity);

                if (!player.isCreative()) {
                    player.getMainHandItem().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                }

                level.destroyBlock(pos, false);

                event.setCancellationResult(InteractionResult.SUCCESS);
            }
        }
    }
}
