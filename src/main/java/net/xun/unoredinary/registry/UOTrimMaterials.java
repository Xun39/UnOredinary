package net.xun.unoredinary.registry;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.xun.lib.common.api.util.CommonUtils;

import java.util.Map;

public class UOTrimMaterials {

    public static final ResourceKey<TrimMaterial> CRYIC = createKey("cryic");
    public static final ResourceKey<TrimMaterial> GLACIUM = createKey("glacium");
    public static final ResourceKey<TrimMaterial> FROSTSTEEL = createKey("froststeel");
    public static final ResourceKey<TrimMaterial> GLACIALITE = createKey("glacialite");
    public static final ResourceKey<TrimMaterial> SAPPHIRE = createKey("sapphire");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, CRYIC, UOItems.CRYIC_POWDER.get(), Style.EMPTY.withColor(TextColor.parseColor("#a9c4db").getOrThrow()), 0.1F);
        register(context, GLACIUM, UOItems.GLACIUM_CRYSTAL.get(), Style.EMPTY.withColor(TextColor.parseColor("#017ed8").getOrThrow()), 0.1F);
        register(context, FROSTSTEEL, UOItems.FROSTSTEEL_INGOT.get(), Style.EMPTY.withColor(TextColor.parseColor("#017ed8").getOrThrow()), 0.1F);
        register(context, GLACIALITE, UOItems.GLACIALITE_INGOT.get(), Style.EMPTY.withColor(TextColor.parseColor("#017ed8").getOrThrow()), 0.8F);
        register(context, SAPPHIRE, UOItems.SAPPHIRE.get(), Style.EMPTY.withColor(TextColor.parseColor("#017ed8").getOrThrow()), 0.9F);
    }

    private static ResourceKey<TrimMaterial> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, CommonUtils.modLoc(name));
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Item item, Style style, float itemModelIndex) {

        TrimMaterial trimmaterial = TrimMaterial.create(trimKey.location().getPath(), item, itemModelIndex,
                Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(style), Map.of()
        );
        context.register(trimKey, trimmaterial);
    }
}
