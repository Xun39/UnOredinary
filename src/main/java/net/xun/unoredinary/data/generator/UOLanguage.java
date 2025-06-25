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

        addBlock(UOBlocks.LUMINITE_ORE, "Luminite Ore");
        addBlock(UOBlocks.DEEPSLATE_LUMINITE_ORE, "Deepslate Luminite Ore");

        // Storage Blocks
        addBlock(UOBlocks.CRYIC_BLOCK, "Block of Cryic");
        addBlock(UOBlocks.SAPPHIRE_BLOCK, "Block of Sapphire");
        addBlock(UOBlocks.GLACIUM_BLOCK, "Block of Glacium");
        addBlock(UOBlocks.LUMINITE_BLOCK, "Block of Luminite");
        addBlock(UOBlocks.FROSTSTEEL_BLOCK, "Block of Froststeel");
        addBlock(UOBlocks.GLACIALITE_BLOCK, "Block of Glacialite");
        addBlock(UOBlocks.LUMINIUM_BLOCK, "Block of Luminium");

        /* ------------------------------ ENTITIES ------------------------------ */
        addEntityAndSpawnEgg(UOEntityTypes.FROST_ZOMBIE, "Frost Zombie");

        /* ------------------------------ MOB EFFECTS and POTIONS ------------------------------ */
        addEffect(UOMobEffects.FROSTED_EFFECT, "Frostbitten");
        addPotion(UOPotions.FROSTBITTEN, "Frostbitten");
        addPotion(UOPotions.LONG_FROSTBITTEN, "Frostbitten");
        addPotion(UOPotions.STRONG_FROSTBITTEN, "Frostbitten");

        /* ------------------------------ ITEMS ------------------------------ */
        addItem(UOItems.CRYIC_POWDER, "Cryic Powder");

        addItem(UOItems.SAPPHIRE, "Sapphire");

        addItem(UOItems.GLACIUM_SHARD, "Glacium Shard");
        addItem(UOItems.GLACIUM_CRYSTAL, "Glacium Crystal");

        addItem(UOItems.FROSTSTEEL_INGOT, "Froststeel Ingot");
        addItem(UOItems.GLACIALITE_INGOT, "Glacialite Ingot");

        addItem(UOItems.GLACIALITE_UPGRADE_SMITHING_TEMPLATE, "Smithing Template");

        addItem(UOItems.LUMINITE_CRYSTAL, "Luminite Crystal");

        addItem(UOItems.LUMINIUM_INGOT, "Luminium Ingot");

        addToolSet(UOTools.FROSTSTEEL);
        addToolSet(UOTools.GLACIALITE);
        addToolSet(UOTools.LUMINIUM);
        addToolSet(UOTools.SAPPHIRE);

        addArmorSet(UOArmors.FROSTSTEEL);
        addArmorSet(UOArmors.GLACIALITE);
        addArmorSet(UOArmors.LUMINIUM);
        addArmorSet(UOArmors.SAPPHIRE);

        /* ------------------------------ TRIM MATERIALS ------------------------------ */
        addTrimMaterial(UOTrimMaterials.CRYIC, "Cryic Material");
        addTrimMaterial(UOTrimMaterials.GLACIUM, "Glacium Material");
        addTrimMaterial(UOTrimMaterials.LUMINITE, "Luminite Material");
        addTrimMaterial(UOTrimMaterials.FROSTSTEEL, "Froststeel Material");
        addTrimMaterial(UOTrimMaterials.GLACIALITE, "Glacialite Material");
        addTrimMaterial(UOTrimMaterials.LUMINIUM, "Luminium Material");
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
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_LUMINITE, "Luminite Storage Blocks");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_FROSTSTEEL, "Froststeel Storage Blocks");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_GLACIALITE, "Glacialite Storage Blocks");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_LUMINIUM, "Luminium Storage Blocks");

        translateTag(UOTags.Items.NUGGETS_GLACIUM, "Glacium Nuggets (Shards)");
        translateTag(UOTags.Items.GEMS_GLACIUM, "Glacium Crystals");
        translateTag(UOTags.Items.GEMS_LUMINITE, "Luminite Crystals");
        translateTag(UOTags.Items.INGOTS_FROSTSTEEL, "Froststeel Ingots");
        translateTag(UOTags.Items.INGOTS_GLACIALITE, "Glacialite Ingots");
        translateTag(UOTags.Items.INGOTS_LUMINIUM, "Luminium Ingots");

        /* ------------------------------ CONFIGURATIONS ------------------------------ */
        translateConfig("froststeel", "Froststeel");
        translateConfig("glacialite", "Glacialite");
        translateConfig("luminium", "Luminium");

        // Tool Effect Configs
        translateConfig("tool_effects", "Tool Effects");

        translateConfig("hit_particles", "Do hit particles spawn");

        translateConfig("enable_tool", "Enable Tool Effects");

        // Armor Effect Configs
        translateConfig("armor_effects", "Armor Effects");

        translateConfig("hurt_particles", "Do hurt particles spawn");

        translateConfig("enable_armor", "Enable Armor Effects");
        translateConfig("enable_frost_walker", "Enable Frost Walker");
        translateConfig("enable_slowness_immunity", "Enable Slowness Immunity");
        translateConfig("enable_hot_floor_damage", "Enable Hot Floor Damage");
        translateConfig("enable_thorns", "Enable Thorns");
        translateConfig("can_walk_on_powder_snow", "Can walk on powder snow");
    }
}