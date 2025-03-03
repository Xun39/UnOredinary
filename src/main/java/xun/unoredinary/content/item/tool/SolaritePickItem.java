package xun.unoredinary.content.item.tool;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class SolaritePickItem extends PickaxeItem {

    public static final Map<Block, ItemLike> SMELT_ORE_MAP =
            Map.of(
                    Blocks.COPPER_ORE, Items.COPPER_INGOT,
                    Blocks.DEEPSLATE_COPPER_ORE, Items.COPPER_INGOT,
                    Blocks.RAW_COPPER_BLOCK, Blocks.COPPER_BLOCK,
                    Blocks.IRON_ORE, Items.IRON_INGOT,
                    Blocks.DEEPSLATE_IRON_ORE, Items.IRON_INGOT,
                    Blocks.RAW_IRON_BLOCK, Blocks.IRON_BLOCK,
                    Blocks.GOLD_ORE, Items.GOLD_INGOT,
                    Blocks.DEEPSLATE_GOLD_ORE, Items.GOLD_INGOT,
                    Blocks.RAW_GOLD_BLOCK, Blocks.GOLD_BLOCK
            );

    public SolaritePickItem(Tier tier, Properties properties) {
        super(tier, properties);
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
