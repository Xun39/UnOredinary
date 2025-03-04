package xun.unoredinary.content.item.tool.shovel;

import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class UOShovelItem extends ShovelItem {

    public UOShovelItem(Tier tier) {
        super(tier, new Properties().attributes(createAttributes(tier, 1.5F, -3.0F)));
    }
}
