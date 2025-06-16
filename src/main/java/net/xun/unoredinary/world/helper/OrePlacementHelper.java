package net.xun.unoredinary.world.helper;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class OrePlacementHelper {

    public static List<PlacementModifier> countPlacement(int count, HeightRangePlacement placement) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), placement, BiomeFilter.biome());
    }

    public static List<PlacementModifier> rarityPlacement(int rarity, HeightRangePlacement placement) {
        return List.of(RarityFilter.onAverageOnceEvery(rarity), InSquarePlacement.spread(), placement, BiomeFilter.biome());
    }
}
