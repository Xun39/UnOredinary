package xun.unoredinary.content.item.tool;

import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class ToolSet {

    public static final int[] DEFAULT_DAMAGE = new int[] { 3, 1, 5, -2, 0 };
    public static final float[] DEFAULT_SPEED = new float[] { -2.4F, -2.8F, -3.0F, -1.0F, -2.0F };

    private final String name;
    private final Tier toolTier;
    private final Item.Properties toolProperties;

    private DeferredItem<SwordItem> sword;
    private DeferredItem<PickaxeItem> pickaxe;
    private DeferredItem<AxeItem> axe;
    private DeferredItem<HoeItem> hoe;
    private DeferredItem<ShovelItem> shovel;

    private final List<Integer> attackDamage = new ArrayList<>();
    private final List<Float> attackSpeed = new ArrayList<>();

    public ToolSet(String name, Tier toolTier, Item.Properties toolProperties) {
        this.name = name;
        this.toolTier = toolTier;
        this.toolProperties = toolProperties;
    }

    public void register(DeferredRegister.Items registry) {
        sword = registry.register(name + "_sword", () -> createSword(toolTier, toolProperties));
        pickaxe = registry.register(name + "_pickaxe", () -> createPickaxe(toolTier, toolProperties));
        axe = registry.register(name + "_axe", () -> createAxe(toolTier, toolProperties));
        hoe = registry.register(name + "_hoe", () -> createHoe(toolTier, toolProperties));
        shovel = registry.register(name + "_shovel", () -> createShovel(toolTier, toolProperties));
    }

    protected SwordItem createSword(Tier toolTier, Item.Properties properties) {
        return new SwordItem(
                toolTier, properties.attributes(SwordItem.createAttributes(toolTier, getAttackDamage().getFirst(), getAttackSpeed().getFirst()))
        );
    }

    protected PickaxeItem createPickaxe(Tier toolTier, Item.Properties properties) {
        return new PickaxeItem(
                toolTier, toolProperties.attributes(PickaxeItem.createAttributes(toolTier, getAttackDamage().get(1), getAttackSpeed().get(1)))
        );
    }

    protected AxeItem createAxe(Tier toolTier, Item.Properties properties) {
        return new AxeItem(
                toolTier, toolProperties.attributes(AxeItem.createAttributes(toolTier, getAttackDamage().get(2), getAttackSpeed().get(2)))
        );
    }

    protected HoeItem createHoe(Tier toolTier, Item.Properties properties) {
        return new HoeItem(
                toolTier, toolProperties.attributes(HoeItem.createAttributes(toolTier, getAttackDamage().get(3), getAttackSpeed().get(3)))
        );
    }

    protected ShovelItem createShovel(Tier toolTier, Item.Properties properties) {
        return new ShovelItem(
                toolTier, toolProperties.attributes(ShovelItem.createAttributes(toolTier, getAttackDamage().getLast(), getAttackSpeed().getLast()))
        );
    }

    protected List<Integer> getAttackDamage() {
        if (attackDamage.isEmpty()) {
            List<Integer> defaultList = new ArrayList<>();
            for (int damage : DEFAULT_DAMAGE) {
                defaultList.add(damage);
            }
            return defaultList;
        }
        return attackDamage;
    }

    protected List<Float> getAttackSpeed() {
        if (attackSpeed.isEmpty()) {
            List<Float> defaultList = new ArrayList<>();
            for (float speed : DEFAULT_SPEED) {
                defaultList.add(speed);
            }
            return defaultList;
        }
        return attackSpeed;
    }

    public DeferredItem<SwordItem> sword() { return sword; }
    public DeferredItem<PickaxeItem> pickaxe() { return pickaxe; }
    public DeferredItem<AxeItem> axe() { return axe; }
    public DeferredItem<HoeItem> hoe() { return hoe; }
    public DeferredItem<ShovelItem> shovel() { return shovel; }

    public List<Item> get() {
        return List.of(sword.get(), pickaxe.get(), axe.get(), hoe.get(), shovel.get());
    }
}
