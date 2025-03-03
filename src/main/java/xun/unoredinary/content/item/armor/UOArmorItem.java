package xun.unoredinary.content.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class UOArmorItem extends ArmorItem {

    public static final int HEAD_SLOT = 3;
    public static final int CHEST_SLOT = 2;
    public static final int LEG_SLOT = 1;
    public static final int FEET_SLOT = 0;

    public UOArmorItem(Holder<ArmorMaterial> material, Type type, int durabilityFactor) {
        super(material, type, new Properties().durability(type.getDurability(durabilityFactor)));
    }

    public UOArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    protected ItemStack getWearingArmorInSlot(Player player, int slotIndex) {
        return player.getInventory().getArmor(slotIndex);
    }

    protected boolean hasWearingArmorInSlot(Player player, int slotIndex) {
        return !getWearingArmorInSlot(player, slotIndex).isEmpty();
    }

    protected boolean evaluateArmorInSlot(Player player, int slotIndex, Holder<ArmorMaterial> armorMaterial) {
        if (!(player.getInventory().getArmor(slotIndex).getItem() instanceof ArmorItem)) return false;
        return ((ArmorItem) player.getInventory().getArmor(slotIndex).getItem()).getMaterial() == armorMaterial;
    }

    protected boolean hasCorrectArmorInSlot(Player player, int slotIndex, Holder<ArmorMaterial> armorMaterial) {
        return hasWearingArmorInSlot(player, slotIndex) && evaluateArmorInSlot(player, slotIndex, armorMaterial);
    }

    protected void addEffectToPlayerIfEffectEndWithin( int endWithinDuration, Player player, List<MobEffectInstance> effects) {

        for (MobEffectInstance effect : effects) {
            MobEffectInstance currentEffect = player.getEffect(effect.getEffect());

            if (currentEffect == null || currentEffect.endsWithin(endWithinDuration)) {
                player.addEffect(new MobEffectInstance(
                        effect.getEffect(),
                        effect.getDuration(),
                        effect.getAmplifier(),
                        effect.isAmbient(),
                        effect.isVisible()
                ));
            }
        }
    }

    protected void addEffectToPlayer(Player player, List<MobEffectInstance> effects) {
        boolean hasPlayerEffect = effects.stream().allMatch(effect -> player.hasEffect(effect.getEffect()));

        if(!hasPlayerEffect) {
            for (MobEffectInstance effect : effects) {
                player.addEffect(new MobEffectInstance(effect.getEffect(),
                        effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.isVisible()));
            }
        }
    }

    protected boolean hasPlayerCorrectArmorOn(Player player, Holder<ArmorMaterial> armorMaterial) {

        return evaluateArmorInSlot(player, HEAD_SLOT, armorMaterial) &&
                evaluateArmorInSlot(player, CHEST_SLOT, armorMaterial) &&
                evaluateArmorInSlot(player, LEG_SLOT, armorMaterial) &&
                evaluateArmorInSlot(player, FEET_SLOT, armorMaterial);
    }

    protected boolean hasFullSuitOfArmorOn(Player player) {

        ItemStack helmet = getWearingArmorInSlot(player, HEAD_SLOT);
        ItemStack chestplate = getWearingArmorInSlot(player, CHEST_SLOT);
        ItemStack leggings = getWearingArmorInSlot(player, LEG_SLOT);
        ItemStack boots = getWearingArmorInSlot(player, FEET_SLOT);

        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }
}
