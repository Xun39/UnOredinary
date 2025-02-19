package xun.unoredinary.content.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class FrosteelSwordItem extends SwordItem {

    public FrosteelSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));

        target.hurt(target.damageSources().freeze(), 1.5F);

        return super.hurtEnemy(stack, target, attacker);
    }
}
