package xun.unoredinary.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;

public class BiomeUtils {

    public static boolean isInColdBiome(Level level, BlockPos pos) {
        return level.getBiome(pos).is(Tags.Biomes.IS_COLD);
    }

    public static boolean isInHotBiome(Level level, BlockPos pos) {
        return level.getBiome(pos).is(Tags.Biomes.IS_HOT);
    }
}