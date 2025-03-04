package xun.unoredinary.content.item.tool.sword;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class UOSwordItem extends SwordItem {

    public UOSwordItem(Tier tier) {
        super(tier, new Properties().attributes(createAttributes(tier, 3, -2.4F)));
    }
}
