package xun.unoredinary.content.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import xun.unoredinary.util.BiomeUtils;

public class FrosteelSwordItem extends SwordItem {

    public FrosteelSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (BiomeUtils.isInColdBiome(attacker.level(), attacker.getOnPos())) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
            target.hurt(target.damageSources().freeze(), 3.0F);
        } else {
            target.hurt(target.damageSources().freeze(), 1.0F);
        }

        return super.hurtEnemy(stack, target, attacker);
    }
}
