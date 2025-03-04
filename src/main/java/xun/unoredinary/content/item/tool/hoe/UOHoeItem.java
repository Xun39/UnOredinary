package xun.unoredinary.content.item.tool.hoe;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

public class UOHoeItem extends HoeItem {

    public UOHoeItem(Tier tier) {
        super(tier, new Properties().attributes(createAttributes(tier, -2.5F, 0.5F)));
    }
}
