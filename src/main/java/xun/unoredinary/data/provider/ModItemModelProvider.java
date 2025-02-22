package xun.unoredinary.data.provider;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import xun.unoredinary.UnOredinary;

import java.util.LinkedHashMap;

public abstract class ModItemModelProvider extends ItemModelProvider {

    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, UnOredinary.MOD_ID, existingFileHelper);
    }

    protected ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(getItemRegistryName(item),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                UnOredinary.modLoc("item/" + getItemRegistryName(item)));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  UnOredinary.modLoc("block/" + baseBlock.getId().getPath()));
    }

    protected void trimmedArmorItem(DeferredItem<ArmorItem> deferredItem) {

        if(deferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath);
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                this.withExistingParent(deferredItem.getId().getPath(), mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0", UnOredinary.modLoc("item/" + getItemRegistryName(deferredItem)));
            });
        }
    }

    protected static String getItemRegistryName(DeferredItem<?> deferredItem) {
        return deferredItem.getId().getPath();
    }
    protected static String getBlockRegistryName(DeferredBlock<?> deferredBlock) { return deferredBlock.getId().getPath(); }
}
