package net.xun.unoredinary.event;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.RegisterCauldronFluidContentEvent;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.registry.*;

@EventBusSubscriber(modid = UnOredinary.MOD_ID)
public class RegistrationEvents {
    // TODO: custom spawn rules
    @SubscribeEvent
    public static void onRegisterSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(UOEntityTypes.FROST_ZOMBIE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(UOEntityTypes.FROST_REVENANT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    @SubscribeEvent
    public static void onFluidContentRegister(RegisterCauldronFluidContentEvent event) {
        event.register(UOBlocks.CRYOPLASM_CAULDRON.get(), UOFluids.CRYOPLASM.get(), FluidType.BUCKET_VOLUME, null);
    }

    @SubscribeEvent
    public static void onBrewingRecipesRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.AWKWARD, UOItems.GLACIUM_SHARD.get(), UOPotions.FROSTBITE);
        builder.addMix(UOPotions.FROSTBITE, UOItems.CRYIC_POWDER.get(), UOPotions.LONG_FROSTBITE);
        builder.addMix(Potions.AWKWARD, UOItems.GLACIUM_CRYSTAL.get(), UOPotions.STRONG_FROSTBITE);

        builder.addMix(Potions.AWKWARD, UOItems.LUMINITE_CRYSTAL.get(), Potions.LONG_NIGHT_VISION);
    }
}
