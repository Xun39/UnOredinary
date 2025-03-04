package xun.unoredinary.content.item.tool.axe;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

public class UOAxeItem extends AxeItem {

    public UOAxeItem(Tier tier) {
        super(tier, new Properties().attributes(createAttributes(tier, 5.5F, -3.0F)));
    }
}
