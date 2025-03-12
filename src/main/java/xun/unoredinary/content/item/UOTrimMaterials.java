package xun.unoredinary.content.item;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.registry.UOItems;

import java.util.Map;

public class UOTrimMaterials {

    public static final ResourceKey<TrimMaterial> HEMOCRYLIC = ResourceKey.create(Registries.TRIM_MATERIAL, UnOredinary.modLoc("hemocrylic"));
    public static final ResourceKey<TrimMaterial> FROSTEEL = ResourceKey.create(Registries.TRIM_MATERIAL, UnOredinary.modLoc("frosteel"));
    public static final ResourceKey<TrimMaterial> CRYOSTONE = ResourceKey.create(Registries.TRIM_MATERIAL, UnOredinary.modLoc("cryostone"));
    public static final ResourceKey<TrimMaterial> LUMINITE = ResourceKey.create(Registries.TRIM_MATERIAL, UnOredinary.modLoc("luminite"));
    public static final ResourceKey<TrimMaterial> LUMINTHIUM = ResourceKey.create(Registries.TRIM_MATERIAL, UnOredinary.modLoc("luminthium"));
    public static final ResourceKey<TrimMaterial> RUBY = ResourceKey.create(Registries.TRIM_MATERIAL, UnOredinary.modLoc("ruby"));

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, HEMOCRYLIC, UOItems.HEMOCRYLIC_CRYSTAL.get(), Style.EMPTY.withColor(TextColor.parseColor("#a8d4f7").getOrThrow()), 0.1F);
        register(context, FROSTEEL, UOItems.FROSTEEL_INGOT.get(), Style.EMPTY.withColor(TextColor.parseColor("#0b40e7").getOrThrow()), 0.9F);
        register(context, CRYOSTONE, UOItems.CRYOSTONE_DUST.get(), Style.EMPTY.withColor(TextColor.parseColor("#0158ff").getOrThrow()), 0.9F);
        register(context, LUMINITE, UOItems.LUMINITE_CRYSTAL.get(), Style.EMPTY.withColor(TextColor.parseColor("#ffe689").getOrThrow()), 0.6F);
        register(context, LUMINTHIUM, UOItems.LUMINTHIUM_INGOT.get(), Style.EMPTY.withColor(TextColor.parseColor("#fe9d0d").getOrThrow()), 0.6F);
        register(context, RUBY, UOItems.RUBY.get(), Style.EMPTY.withColor(TextColor.parseColor("#e20f0f").getOrThrow()), 0.4F);
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Item item,
                                 Style style, float itemModelIndex) {
        TrimMaterial trimmaterial = TrimMaterial.create(trimKey.location().getPath(), item, itemModelIndex,
                Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(style), Map.of());
        context.register(trimKey, trimmaterial);
    }
}