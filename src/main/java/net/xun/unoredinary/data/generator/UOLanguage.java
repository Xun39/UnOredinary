package net.xun.unoredinary.data.generator;

import net.minecraft.data.PackOutput;
import net.xun.unoredinary.data.provider.UOLanguageProvider;
import net.xun.unoredinary.registry.*;
import net.xun.unoredinary.util.UOTags;

public class UOLanguage extends UOLanguageProvider {
    public UOLanguage(PackOutput output) {
        super(output);
    }

    @Override
    protected void addTranslations() {

        /* ------------------------------ ITEM GROUPS ------------------------------ */
        add("creative_mode_tab.unoredinary.block", "UnOredinary: Blocks");
        add("creative_mode_tab.unoredinary.item", "UnOredinary: Items");
        add("creative_mode_tab.unoredinary.equipment", "UnOredinary: Equipments");

        /* ------------------------------ ADVANCEMENTS ------------------------------ */
        // Story
        addAdvancement("mine_cryic", "Frost Foundations", "Mine Cryic Ore in any cold biomes");
        addAdvancement("forge_froststeel", "Steel of the Tundra", "Forge a Froststeel Ingot");
        addAdvancement("froststeel_tools", "Frost Walker's Tools", "Craft any Froststeel Tool");
        addAdvancement("mine_glacium", "Iceberg Treasure", "Acquire some Glacium Shards");
        addAdvancement("get_glacium_crystal", "Ancient Ice", "Obtain Glacium Crystal");
        addAdvancement("craft_glacialite", "Glacial Fusion", "Craft a Glacialite Ingot");

        // Adventure
        addAdvancement("find_iceberg", "Iceberg Explorer", "Discover an iceberg");
        addAdvancement("freeze_miner", "Deep Freeze Miner", "Mine at least 20 Glacium Ores");
        addAdvancement("frost_dungeon", "Beneath the Frozen Veil", "Enter a Frost Dungeon");

        /* ------------------------------ BLOCKS ------------------------------ */
        // Ores
        addBlock(UOBlocks.CRYIC_ORE, "Cryic Ore");
        addBlock(UOBlocks.DEEPSLATE_CRYIC_ORE, "Deepslate Cryic Ore");

        addBlock(UOBlocks.SAPPHIRE_ORE, "Sapphire Ore");
        addBlock(UOBlocks.DEEPSLATE_SAPPHIRE_ORE, "Deepslate Sapphire Ore");

        addBlock(UOBlocks.GLACIUM_ORE, "Glacium Ore");
        addBlock(UOBlocks.PRIMAL_GLACIUM_ORE, "Primal Glacium Ore");

        // Storage Blocks
        addBlock(UOBlocks.CRYIC_BLOCK, "Block of Cryic");
        addBlock(UOBlocks.SAPPHIRE_BLOCK, "Block of Sapphire");
        addBlock(UOBlocks.GLACIUM_BLOCK, "Block of Glacium");
        addBlock(UOBlocks.FROSTSTEEL_BLOCK, "Block of Froststeel");
        addBlock(UOBlocks.GLACIALITE_BLOCK, "Block of Glacialite");

        /* ------------------------------ ENTITIES ------------------------------ */
        addEntityAndSpawnEgg(UOEntityTypes.FROST_ZOMBIE, "Frost Zombie");

        /* ------------------------------ MOB EFFECTS ------------------------------ */
        addEffect(UOMobEffects.FROSTED_EFFECT, "Frostbitten");

        /* ------------------------------ ITEMS ------------------------------ */
        addItem(UOItems.CRYIC_POWDER, "Cryic Powder");

        addItem(UOItems.SAPPHIRE, "Sapphire");

        addItem(UOItems.GLACIUM_SHARD, "Glacium Shard");
        addItem(UOItems.GLACIUM_CRYSTAL, "Glacium Crystal");

        addItem(UOItems.FROSTSTEEL_INGOT, "Froststeel Ingot");
        addItem(UOItems.GLACIALITE_INGOT, "Glacialite Ingot");

        addItem(UOItems.GLACIALITE_UPGRADE_SMITHING_TEMPLATE, "Smithing Template");

        addToolSet(UOTools.FROSTSTEEL);
        addToolSet(UOTools.GLACIALITE);
        addToolSet(UOTools.SAPPHIRE);

        addArmorSet(UOArmors.FROSTSTEEL);
        addArmorSet(UOArmors.GLACIALITE);
        addArmorSet(UOArmors.SAPPHIRE);

        /* ------------------------------ TRIM MATERIALS ------------------------------ */
        addTrimMaterial(UOTrimMaterials.CRYIC, "Cryic Material");
        addTrimMaterial(UOTrimMaterials.GLACIUM, "Glacium Material");
        addTrimMaterial(UOTrimMaterials.FROSTSTEEL, "Froststeel Material");
        addTrimMaterial(UOTrimMaterials.GLACIALITE, "Glacialite Material");
        addTrimMaterial(UOTrimMaterials.SAPPHIRE, "Sapphire Material");

        /* ------------------------------ TOOLTIPS ------------------------------ */
        add("upgrade.unoredinary.glacialite_upgrade", "Glacialite Upgrade");
        add("item.unoredinary.smithing_template.glacialite_upgrade.applies_to", "Froststeel Equipment");
        add("item.unoredinary.smithing_template.glacialite_upgrade.ingredients", "Glacialite Ingot");
        add("item.unoredinary.smithing_template.glacialite_upgrade.base_slot_description", "Add froststeel armor, weapon, or tool");
        add("item.unoredinary.smithing_template.glacialite_upgrade.additions_slot_description", "Add Glacialite Ingot");

        /* ------------------------------ TAGS ------------------------------ */
        translateTag(UOTags.Blocks.ORES_CRYIC, "Cryic Ores");
        translateTag(UOTags.Blocks.ORES_GLACIUM, "Glacium Ores");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_CRYIC, "Cryic Storage Blocks");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_GLACIUM, "Glacium Storage Blocks");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_FROSTSTEEL, "Froststeel Storage Blocks");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_GLACIALITE, "Glacialite Storage Blocks");

        translateTag(UOTags.Items.NUGGETS_GLACIUM, "Glacium Nuggets (Shards)");
        translateTag(UOTags.Items.GEMS_GLACIUM, "Glacium Gems");
        translateTag(UOTags.Items.INGOTS_FROSTSTEEL, "Froststeel Ingots");
        translateTag(UOTags.Items.INGOTS_GLACIALITE, "Glacialite Ingots");
    }
}
