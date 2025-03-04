package xun.unoredinary.content.item.tool.axe;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import xun.unoredinary.content.item.tool.ModToolTiers;
import xun.unoredinary.util.BiomeUtils;

public class FrosteelAxeItem extends UOAxeItem{

    public FrosteelAxeItem() {
        super(ModToolTiers.FROSTEEL);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (BiomeUtils.isInColdBiome(attacker.level(), attacker.getOnPos())) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
            target.hurt(target.damageSources().freeze(), 5.0F);
        } else {
            target.hurt(target.damageSources().freeze(), 2.0F);
        }

        return super.hurtEnemy(stack, target, attacker);
    }
}
