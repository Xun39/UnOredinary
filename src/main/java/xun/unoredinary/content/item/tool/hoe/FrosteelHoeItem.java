package xun.unoredinary.content.item.tool.hoe;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import xun.unoredinary.content.item.tool.ModToolTiers;
import xun.unoredinary.util.BiomeUtils;

public class FrosteelHoeItem extends UOHoeItem{

    public FrosteelHoeItem() {
        super(ModToolTiers.FROSTEEL);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (BiomeUtils.isInColdBiome(attacker.level(), attacker.getOnPos())) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
            target.hurt(target.damageSources().freeze(), 0.8F);
        } else {
            target.hurt(target.damageSources().freeze(), 0.2F);
        }

        return super.hurtEnemy(stack, target, attacker);
    }
}
