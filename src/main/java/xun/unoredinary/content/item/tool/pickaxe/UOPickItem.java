package xun.unoredinary.content.item.tool.pickaxe;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class UOPickItem extends PickaxeItem {

    public UOPickItem(Tier tier) {
        super(tier, new Properties().attributes(createAttributes(tier, 1.0F, -2.8F)));
    }
}
