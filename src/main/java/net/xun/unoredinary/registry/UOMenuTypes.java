package net.xun.unoredinary.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.block.entity.container.TransenchantingTableMenu;

public class UOMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, UnOredinary.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<TransenchantingTableMenu>> TRANSENCHANTING_TABLE = MENU_TYPES.register("transenchanting_table",
            () -> IMenuTypeExtension.create(TransenchantingTableMenu::new));
}
