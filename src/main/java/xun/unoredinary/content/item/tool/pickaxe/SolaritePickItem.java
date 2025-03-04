package xun.unoredinary.content.item.tool.pickaxe;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import xun.unoredinary.content.item.tool.ModToolTiers;

import java.util.HashMap;
import java.util.Map;

public class SolaritePickItem extends UOPickItem {

    public static final Map<Block, ItemLike> SMELT_ORE_MAP;

    static {
        Map<Block, ItemLike> map = new HashMap<>();

        // Copper-related entries
        map.put(Blocks.COPPER_ORE, Items.COPPER_INGOT);
        map.put(Blocks.DEEPSLATE_COPPER_ORE, Items.COPPER_INGOT);
        map.put(Blocks.RAW_COPPER_BLOCK, Blocks.COPPER_BLOCK);

        // Iron-related entries
        map.put(Blocks.IRON_ORE, Items.IRON_INGOT);
        map.put(Blocks.DEEPSLATE_IRON_ORE, Items.IRON_INGOT);
        map.put(Blocks.RAW_IRON_BLOCK, Blocks.IRON_BLOCK);

        // Gold-related entries
        map.put(Blocks.GOLD_ORE, Items.GOLD_INGOT);
        map.put(Blocks.DEEPSLATE_GOLD_ORE, Items.GOLD_INGOT);
        map.put(Blocks.RAW_GOLD_BLOCK, Blocks.GOLD_BLOCK);

        // Stone-related entries
        map.put(Blocks.STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS);
        map.put(Blocks.DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS);

        // Nether-related entries
        map.put(Blocks.NETHER_BRICKS, Blocks.CRACKED_NETHER_BRICKS);
        map.put(Blocks.ANCIENT_DEBRIS, Items.NETHERITE_SCRAP);

        // Sand and clay-related entries
        map.put(Blocks.SAND, Blocks.GLASS);
        map.put(Blocks.RED_SAND, Blocks.GLASS);
        map.put(Blocks.CLAY, Blocks.TERRACOTTA);

        // Wood-related entries (charcoal)
        map.put(Blocks.OAK_LOG, Items.CHARCOAL);
        map.put(Blocks.SPRUCE_LOG, Items.CHARCOAL);
        map.put(Blocks.BIRCH_LOG, Items.CHARCOAL);
        map.put(Blocks.JUNGLE_LOG, Items.CHARCOAL);
        map.put(Blocks.ACACIA_LOG, Items.CHARCOAL);
        map.put(Blocks.DARK_OAK_LOG, Items.CHARCOAL);

        // Miscellaneous entries
        map.put(Blocks.KELP, Blocks.DRIED_KELP_BLOCK);
        map.put(Blocks.WET_SPONGE, Blocks.SPONGE);

        // Make the map immutable
        SMELT_ORE_MAP = Map.copyOf(map);
    }

    public SolaritePickItem() {
        super(ModToolTiers.SOLARITE);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        boolean result = super.hurtEnemy(stack, target, attacker);

        if (result && !target.level().isClientSide() && !target.fireImmune()) {
            target.igniteForSeconds(15);
        } else {
            for (int var1 = 0; var1 < 20; ++var1) {
                double px = target.getX() + target.level().getRandom().nextFloat() * target.getBbWidth() * 2.0F - target.getBbWidth();
                double py = target.getY() + target.level().getRandom().nextFloat() * target.getBbHeight();
                double pz = target.getZ() + target.level().getRandom().nextFloat() * target.getBbWidth() * 2.0F - target.getBbWidth();
                target.level().addParticle(ParticleTypes.FLAME, px, py, pz, 0.02, 0.02, 0.02);
            }
        }

        return result;
    }
}
