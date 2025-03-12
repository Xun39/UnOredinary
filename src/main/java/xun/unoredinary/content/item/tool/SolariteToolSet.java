package xun.unoredinary.content.item.tool;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class SolariteToolSet extends ToolSet {

    public static final Map<Block, ItemLike> SMELT_ORE_MAP;

    static {
        Map<Block, ItemLike> map = new HashMap<>();

        // Copper
        map.put(Blocks.COPPER_ORE, Items.COPPER_INGOT);
        map.put(Blocks.DEEPSLATE_COPPER_ORE, Items.COPPER_INGOT);
        map.put(Blocks.RAW_COPPER_BLOCK, Blocks.COPPER_BLOCK);

        // Iron
        map.put(Blocks.IRON_ORE, Items.IRON_INGOT);
        map.put(Blocks.DEEPSLATE_IRON_ORE, Items.IRON_INGOT);
        map.put(Blocks.RAW_IRON_BLOCK, Blocks.IRON_BLOCK);

        // Gold
        map.put(Blocks.GOLD_ORE, Items.GOLD_INGOT);
        map.put(Blocks.DEEPSLATE_GOLD_ORE, Items.GOLD_INGOT);
        map.put(Blocks.RAW_GOLD_BLOCK, Blocks.GOLD_BLOCK);

        // Stone
        map.put(Blocks.STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS);
        map.put(Blocks.DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS);

        // Nether
        map.put(Blocks.NETHER_BRICKS, Blocks.CRACKED_NETHER_BRICKS);
        map.put(Blocks.ANCIENT_DEBRIS, Items.NETHERITE_SCRAP);

        // Sand/clay
        map.put(Blocks.SAND, Blocks.GLASS);
        map.put(Blocks.RED_SAND, Blocks.GLASS);
        map.put(Blocks.CLAY, Blocks.TERRACOTTA);

        // Wood
        map.put(Blocks.OAK_LOG, Items.CHARCOAL);
        map.put(Blocks.SPRUCE_LOG, Items.CHARCOAL);
        map.put(Blocks.BIRCH_LOG, Items.CHARCOAL);
        map.put(Blocks.JUNGLE_LOG, Items.CHARCOAL);
        map.put(Blocks.ACACIA_LOG, Items.CHARCOAL);
        map.put(Blocks.DARK_OAK_LOG, Items.CHARCOAL);

        // Misc
        map.put(Blocks.KELP, Blocks.DRIED_KELP_BLOCK);
        map.put(Blocks.WET_SPONGE, Blocks.SPONGE);

        SMELT_ORE_MAP = Map.copyOf(map);
    }

    public SolariteToolSet(String name, Tier toolTier, Item.Properties toolProperties) {
        super(name, toolTier, toolProperties);
    }

    @Override
    protected SwordItem createSword(Tier toolTier, Item.Properties properties) {
        return new SwordItem(toolTier, properties
                .attributes(SwordItem.createAttributes(toolTier, getAttackDamage().getFirst(), getAttackSpeed().getFirst()))) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                boolean result = super.hurtEnemy(stack, target, attacker);
                handleHitEffect(result, target, 4.0F);
                return result;
            }
        };
    }

    @Override
    protected PickaxeItem createPickaxe(Tier toolTier, Item.Properties properties) {
        return new PickaxeItem(toolTier, properties
                .attributes(PickaxeItem.createAttributes(toolTier, getAttackDamage().get(1), getAttackSpeed().get(1)))) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                boolean result = super.hurtEnemy(stack, target, attacker);
                handleHitEffect(result, target, 0.0F);
                return result;
            }
        };
    }

    @Override
    protected AxeItem createAxe(Tier toolTier, Item.Properties properties) {
        return new AxeItem(toolTier, properties
                .attributes(AxeItem.createAttributes(toolTier, getAttackDamage().get(2), getAttackSpeed().get(2)))) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                boolean result = super.hurtEnemy(stack, target, attacker);
                handleHitEffect(result, target, 6.0F);
                return result;
            }
        };
    }

    @Override
    protected HoeItem createHoe(Tier toolTier, Item.Properties properties) {
        return new HoeItem(toolTier, properties
                .attributes(HoeItem.createAttributes(toolTier, getAttackDamage().get(3), getAttackSpeed().get(3)))) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                boolean result = super.hurtEnemy(stack, target, attacker);
                handleHitEffect(result, target, 0.0F);
                return result;
            }
        };
    }

    @Override
    protected ShovelItem createShovel(Tier toolTier, Item.Properties properties) {
        return new ShovelItem(UOTiers.SOLARITE, properties
                .attributes(ShovelItem.createAttributes(UOTiers.SOLARITE, getAttackDamage().getLast(), getAttackSpeed().getLast()))) {
            @Override
            public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                boolean result = super.hurtEnemy(stack, target, attacker);
                handleHitEffect(result, target, 0.0F);
                return result;
            }
        };
    }

    private static void handleHitEffect(boolean result, LivingEntity target, float extraDamage) {
        if (result && !target.level().isClientSide() && !target.fireImmune()) {
            target.igniteForSeconds(15);
            if (extraDamage > 0) {
                target.hurt(target.damageSources().lava(), extraDamage);
            }
        } else {
            Level level = target.level();
            for (int i = 0; i < 20; ++i) {
                double px = target.getX() + level.getRandom().nextFloat() * target.getBbWidth() * 2.0F - target.getBbWidth();
                double py = target.getY() + level.getRandom().nextFloat() * target.getBbHeight();
                double pz = target.getZ() + level.getRandom().nextFloat() * target.getBbWidth() * 2.0F - target.getBbWidth();
                level.addParticle(ParticleTypes.FLAME, px, py, pz, 0.02, 0.02, 0.02);
            }
        }
    }
}