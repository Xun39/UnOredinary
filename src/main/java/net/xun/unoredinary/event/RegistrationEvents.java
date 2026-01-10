package net.xun.unoredinary.event;

import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.registry.UOItems;
import net.xun.unoredinary.registry.UOPotions;

@EventBusSubscriber(modid = UnOredinary.MOD_ID)
public class RegistrationEvents {

    @SubscribeEvent
    public static void onBrewingRecipesRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.AWKWARD, UOItems.GLACIUM_SHARD.get(), UOPotions.FROSTBITTEN);
        builder.addMix(UOPotions.FROSTBITTEN, UOItems.CRYIC_POWDER.get(), UOPotions.LONG_FROSTBITTEN);
        builder.addMix(Potions.AWKWARD, UOItems.GLACIUM_CRYSTAL.get(), UOPotions.STRONG_FROSTBITTEN);

        builder.addMix(Potions.AWKWARD, UOItems.LUMINITE_CRYSTAL.get(), Potions.LONG_NIGHT_VISION);
    }
}
