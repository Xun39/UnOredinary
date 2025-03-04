package xun.unoredinary.content.item.tool.shovel;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import xun.unoredinary.content.item.tool.ModToolTiers;

import java.util.Map;

public class SolariteShovelItem extends UOShovelItem {

    public static final Map<Block, ItemLike> SMELT_MAP =
            Map.of(
                    Blocks.SAND, Blocks.GLASS
            );

    public SolariteShovelItem() {
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
