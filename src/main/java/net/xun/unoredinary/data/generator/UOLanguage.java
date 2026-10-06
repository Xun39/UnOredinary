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

        /* ------------------------------ ATTRIBUTES ------------------------------ */
        addAttribute("cold_damage", "Cold Damage");

        /* ------------------------------ BLOCKS ------------------------------ */
        // Ores
        addBlock(UOBlocks.CRYIC_ORE, "Cryic Ore");
        addBlock(UOBlocks.DEEPSLATE_CRYIC_ORE, "Deepslate Cryic Ore");

        addBlock(UOBlocks.SAPPHIRE_ORE, "Sapphire Ore");
        addBlock(UOBlocks.DEEPSLATE_SAPPHIRE_ORE, "Deepslate Sapphire Ore");

        addBlock(UOBlocks.NETHER_RUBY_ORE, "Nether Ruby Ore");

        addBlock(UOBlocks.GLACIUM_ORE, "Glacium Ore");
        addBlock(UOBlocks.PRIMAL_GLACIUM_ORE, "Primal Glacium Ore");

        addBlock(UOBlocks.LUMINITE_ORE, "Luminite Ore");
        addBlock(UOBlocks.DEEPSLATE_LUMINITE_ORE, "Deepslate Luminite Ore");

        // Storage Blocks
        addBlock(UOBlocks.CRYIC_BLOCK, "Block of Cryic");
        addBlock(UOBlocks.SAPPHIRE_BLOCK, "Block of Sapphire");
        addBlock(UOBlocks.RUBY_BLOCK, "Block of Ruby");
        addBlock(UOBlocks.GLACIUM_BLOCK, "Block of Glacium");
        addBlock(UOBlocks.LUMINITE_BLOCK, "Block of Luminite");
        addBlock(UOBlocks.FROSTSTEEL_BLOCK, "Block of Froststeel");
        addBlock(UOBlocks.GLACIALITE_BLOCK, "Block of Glacialite");
        addBlock(UOBlocks.LUMINIUM_BLOCK, "Block of Luminium");

        // Misc
        addBlock(UOBlocks.TRANSENCHANTING_TABLE, "Transenchanting Table");
        addBlock(UOBlocks.CRYOPLASM_CAULDRON, "Cryoplasm Cauldron");

        addBlock(UOBlocks.POLAR_STONE, "Polar Stone");
        addBlock(UOBlocks.POLAR_STONE_STAIRS, "Polar Stone Stairs");
        addBlock(UOBlocks.POLAR_STONE_SLAB, "Polar Stone Slab");

        addBlock(UOBlocks.COBBLED_POLAR_STONE, "Cobbled Polar Stone");
        addBlock(UOBlocks.COBBLED_POLAR_STONE_STAIRS, "Cobbled Polar Stone Stairs");
        addBlock(UOBlocks.COBBLED_POLAR_STONE_SLAB, "Cobbled Polar Stone Slab");
        addBlock(UOBlocks.COBBLED_POLAR_STONE_WALL, "Cobbled Polar Stone Wall");

        addBlock(UOBlocks.POLAR_STONE_BRICKS, "Polar Stone Bricks");
        addBlock(UOBlocks.POLAR_STONE_BRICKS_STAIRS, "Polar Stone Bricks Stairs");
        addBlock(UOBlocks.POLAR_STONE_BRICKS_SLAB, "Polar Stone Bricks Slab");
        addBlock(UOBlocks.POLAR_STONE_BRICKS_WALL, "Polar Stone Bricks Wall");

        addBlock(UOBlocks.ICE_DOOR, "Ice Door");
        addBlock(UOBlocks.ICE_TRAPDOOR, "Ice Trapdoor");
        addBlock(UOBlocks.ICE_BUTTON, "Ice Button");

        addBlock(UOBlocks.TRAP_ICE, "Packed Ice");
        addBlock(UOBlocks.CRYOPLASM, "Cryoplasm");

        // Vanilla block with custom names
        add("trial_spawner.unoredinary.frost_dungeon", "Frost Trial Spawner");
        add("vault.unoredinary.frost_dungeon", "Frost Vault");

        /* ------------------------------ CONTAINERS ------------------------------ */
        add("unoredinary.container.transenchanting_table", "Transenchant");

        /* ------------------------------ ENTITIES ------------------------------ */
        addEntityAndSpawnEgg(UOEntityTypes.FROST_ZOMBIE, "Frost Zombie");
        addEntityAndSpawnEgg(UOEntityTypes.FROST_REVENANT, "Frost Revenant");

        /* ------------------------------ MOB EFFECTS and POTIONS ------------------------------ */
        addEffect(UOMobEffects.FROSTBITE_EFFECT, "Frostbite");
        addPotion(UOPotions.FROSTBITE, "Frostbite");
        addPotion(UOPotions.LONG_FROSTBITE, "Frostbite");
        addPotion(UOPotions.STRONG_FROSTBITE, "Frostbite");

        addEffect(UOMobEffects.WARMTH_EFFECT, "Warmth");
        addPotion(UOPotions.WARMTH, "Warmth");
        addPotion(UOPotions.LONG_WARMTH, "Warmth");

        /* ------------------------------ ITEMS ------------------------------ */
        addItem(UOItems.CRYIC_POWDER, "Cryic Powder");
        addItem(UOItems.CRYOPLASM_BUCKET, "Cryoplasm Bucket");

        addItem(UOItems.SAPPHIRE, "Sapphire");

        addItem(UOItems.NETHER_RUBY, "Nether Ruby");

        addItem(UOItems.GLACIUM_SHARD, "Glacium Shard");
        addItem(UOItems.GLACIUM_CRYSTAL, "Glacium Crystal");

        addItem(UOItems.FROSTSTEEL_NUGGET, "Froststeel Nugget");
        addItem(UOItems.FROSTSTEEL_INGOT, "Froststeel Ingot");

        addItem(UOItems.GLACIALITE_INGOT, "Glacialite Ingot");

        addItem(UOItems.GLACIALITE_UPGRADE_SMITHING_TEMPLATE, "Smithing Template");

        addItem(UOItems.FROST_KEY, "Frost Key");

        addItem(UOItems.LUMINITE_CRYSTAL, "Luminite Crystal");

        addItem(UOItems.LUMINIUM_INGOT, "Luminium Ingot");
        addItem(UOItems.LUMINIUM_NUGGET, "Luminium Nugget");

        addToolSet(UOTools.FROSTSTEEL);
        addToolSet(UOTools.GLACIALITE);
        addToolSet(UOTools.LUMINIUM);
        addToolSet(UOTools.SAPPHIRE);
        addToolSet(UOTools.RUBY);

        addArmorSet(UOArmors.FROSTSTEEL);
        addArmorSet(UOArmors.GLACIALITE);
        addArmorSet(UOArmors.LUMINIUM);
        addArmorSet(UOArmors.SAPPHIRE);
        addArmorSet(UOArmors.RUBY);

        /* ------------------------------ FLUID TYPES ------------------------------ */
        addFluidType(UOFluidTypes.CRYOPLASM, "Cryoplasm");

        /* ------------------------------ TRIM MATERIALS ------------------------------ */
        addTrimMaterial(UOTrimMaterials.CRYIC, "Cryic Material");
        addTrimMaterial(UOTrimMaterials.GLACIUM, "Glacium Material");
        addTrimMaterial(UOTrimMaterials.LUMINITE, "Luminite Material");
        addTrimMaterial(UOTrimMaterials.FROSTSTEEL, "Froststeel Material");
        addTrimMaterial(UOTrimMaterials.GLACIALITE, "Glacialite Material");
        addTrimMaterial(UOTrimMaterials.LUMINIUM, "Luminium Material");
        addTrimMaterial(UOTrimMaterials.SAPPHIRE, "Sapphire Material");
        addTrimMaterial(UOTrimMaterials.RUBY, "Ruby Material");

        /* ------------------------------ TOOLTIPS ------------------------------ */
        add("upgrade.unoredinary.glacialite_upgrade", "Glacialite Upgrade");
        add("item.unoredinary.smithing_template.glacialite_upgrade.applies_to", "Froststeel Equipment");
        add("item.unoredinary.smithing_template.glacialite_upgrade.ingredients", "Glacialite Ingot");
        add("item.unoredinary.smithing_template.glacialite_upgrade.base_slot_description", "Add froststeel armor, weapon, or tool");
        add("item.unoredinary.smithing_template.glacialite_upgrade.additions_slot_description", "Add Glacialite Ingot");

        // Transenchanting Table
        addToolTip("transenchanting_table.enchantments_number", "Stored Enchantments");
        addToolTip("transenchanting_table.enchantments_count", "%s enchantment(s)");
        addToolTip("transenchanting_table.enchantments_description", "These enchantments can be transferred to a compatible item or stored in a book.");

        addToolTip("transenchanting_table.level_cost", "Transenchanting Cost");
        addToolTip("transenchanting_table.levels", "%s level(s)");
        addToolTip("transenchanting_table.cost_breakdown", "Cost Breakdown");
        addToolTip("transenchanting_table.base_cost", "  • Base cost: %s levels");
        addToolTip("transenchanting_table.enchantment_cost", "%s enchantments × %s levels = %s levels");
        addToolTip("transenchanting_table.total_cost", "Total: %s levels");

        addToolTip("transenchanting_table.transfer_enchantments", "Transfer Enchantment(s)");
        addToolTip("transenchanting_table.create_book", "Create Enchanted Book");
        addToolTip("transenchanting_table.move_enchantments", "Move %s enchantments to this item");
        addToolTip("transenchanting_table.store_enchantments", "Store %s enchantments in this book");
        addToolTip("transenchanting_table.cost", "Cost: %s levels");

        addToolTip("transenchanting_table.transfer_description", "The stored enchantments will be transferred to the target.");
        addToolTip("transenchanting_table.target_must_be_unenchanted", "The target item must be unenchanted.");
        addToolTip("transenchanting_table.target_must_be_compatible", "The target must be compatible with the stored enchantments.");

        addToolTip("transenchanting_table.cannot_transenchant", "Cannot Transenchant");
        addToolTip("transenchanting_table.not_enough_levels", "Not enough experience levels");
        addToolTip("transenchanting_table.requires_levels", "Requires: %s levels");

        addToolTip("transenchanting_table.no_enchantments", "The transenchanter contains no enchantments");
        addToolTip("transenchanting_table.no_target", "Place an item or book in the target slot");
        addToolTip("transenchanting_table.transenchanter_has_no_enchants", "The transenchanter contains no enchantments");
        addToolTip("transenchanting_table.target_already_enchanted" , "The target already has enchantments");
        addToolTip("transenchanting_table.incompatible", "This item is incompatible");

        addToolTip("transenchanting_table.afford_description", "You need at least %s experience levels to perform this operation.");
        addToolTip("transenchanting_table.no_enchantments_description", "The transenchanter must contain at least one enchantment.");
        addToolTip("transenchanting_table.no_target_description", "Place an unenchanted, compatible item or a book in the target slot.") ;
        addToolTip("transenchanting_table.already_enchanted_description", "Transenchanting cannot add enchantments to an already enchanted item.") ;
        addToolTip("transenchanting_table.incompatible_description", "The target item cannot receive the stored enchantments.");
        addToolTip("transenchanting_table.hold_shift", "Hold Shift for more information");

        /* ------------------------------ TAGS ------------------------------ */
        translateTag(UOTags.Blocks.ORES_CRYIC, "Cryic Ores");
        translateTag(UOTags.Blocks.ORES_GLACIUM, "Glacium Ores");
        translateTag(UOTags.Blocks.ORES_LUMINITE, "Luminite Ores");
        translateTag(UOTags.Blocks.ORES_SAPPHIRE, "Sapphire Ores");
        translateTag(UOTags.Blocks.ORES_RUBY, "Ruby Ores");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_CRYIC, "Cryic Storage Blocks");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_GLACIUM, "Glacium Storage Blocks");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_LUMINITE, "Luminite Storage Blocks");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_FROSTSTEEL, "Froststeel Storage Blocks");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_GLACIALITE, "Glacialite Storage Blocks");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_LUMINIUM, "Luminium Storage Blocks");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_SAPPHIRE, "Sapphire Storage Blocks");
        translateTag(UOTags.Blocks.STORAGE_BLOCKS_RUBY, "Ruby Storage Blocks");

        translateTag(UOTags.Items.DUSTS_CRYIC, "Cryic Dusts");
        translateTag(UOTags.Items.GEMS_GLACIUM, "Glacium Crystals");
        translateTag(UOTags.Items.GEMS_LUMINITE, "Luminite Crystals");
        translateTag(UOTags.Items.GEMS_SAPPHIRE, "Sapphires");
        translateTag(UOTags.Items.GEMS_RUBY, "Rubies");
        translateTag(UOTags.Items.INGOTS_FROSTSTEEL, "Froststeel Ingots");
        translateTag(UOTags.Items.INGOTS_GLACIALITE, "Glacialite Ingots");
        translateTag(UOTags.Items.INGOTS_LUMINIUM, "Luminium Ingots");
        translateTag(UOTags.Items.NUGGETS_FROSTSTEEL, "Froststeel Nuggets");
        translateTag(UOTags.Items.NUGGETS_LUMINIUM, "Luminium Nuggets");

        /* ------------------------------ CONFIGURATIONS ------------------------------ */
        translateConfigCategory("armor_effect", "server", "Armor Effects");
        translateConfigCategory("tool_effect", "server", "Tool Effects");

        // Armor Effects — Luminium
        translateConfigOption(
                "armorEffect.luminium.enable",
                "Enable",
                "Disabling this setting will turn off all armor effects of Luminium Armor."
        );

        translateConfigOption(
                "armorEffect.luminium.enableNightVision",
                "Night Vision",
                "Enables the Night Vision effect provided by Luminium Armor."
        );

        // Armor Effects — Froststeel
        translateConfigOption(
                "armorEffect.froststeel.enable",
                "Enable",
                "Disabling this setting will turn off all armor effects of Froststeel Armor."
        );

        translateConfigOption(
                "armorEffect.froststeel.enableFrostWalker",
                "Frost Walker",
                "Enables the Frost Walker effect provided by Froststeel Armor."
        );

        translateConfigOption(
                "armorEffect.froststeel.immuneToHotFloor",
                "Hot Floor Immunity",
                "Makes Froststeel Armor immune to damage from hot floors."
        );

        translateConfigOption(
                "armorEffect.froststeel.frostWalkerRadius",
                "Frost Walker Radius",
                "Sets the radius of the Frost Walker effect for Froststeel Armor."
        );

        // Armor Effects — Glacialite
        translateConfigOption(
                "armorEffect.glacialite.enable",
                "Enable",
                "Disabling this setting will turn off all armor effects of Glacialite Armor."
        );

        translateConfigOption(
                "armorEffect.glacialite.enableFrostWalker",
                "Frost Walker",
                "Enables the Frost Walker effect provided by Glacialite Armor."
        );

        translateConfigOption(
                "armorEffect.glacialite.immuneToSlowness",
                "Slowness Immunity",
                "Makes Glacialite Armor immune to Slowness."
        );

        translateConfigOption(
                "armorEffect.glacialite.thorns",
                "Thorns",
                "Enables the Thorns effect provided by Glacialite Armor."
        );

        translateConfigOption(
                "armorEffect.glacialite.immuneToHotFloor",
                "Hot Floor Immunity",
                "Makes Glacialite Armor immune to damage from hot floors."
        );

        translateConfigOption(
                "armorEffect.glacialite.walkOnPowderSnow",
                "Walk on Powder Snow",
                "Allows Glacialite Armor to walk on powder snow without sinking."
        );

        translateConfigOption(
                "armorEffect.glacialite.frostWalkerRadius",
                "Frost Walker Radius",
                "Sets the radius of the Frost Walker effect for Glacialite Armor."
        );

        translateConfigOption(
                "armorEffect.glacialite.damageParticle",
                "Damage Particles",
                "Enables damage particles for Glacialite Armor."
        );

        // Tool Effects — Luminium
        translateConfigOption(
                "toolEffect.luminium.enable",
                "Enable",
                "Disabling this setting will turn off all tool effects of Luminium Tools."
        );

        translateConfigOption(
                "toolEffect.luminium.enableGlowingOnHit",
                "Glowing on Hit",
                "Enables the Glowing effect when hitting an enemy with Luminium Tools."
        );

        // Tool Effects — Froststeel
        translateConfigOption(
                "toolEffect.froststeel.enable",
                "Enable",
                "Disabling this setting will turn off all tool effects of Froststeel Tools."
        );

        translateConfigOption(
                "toolEffect.froststeel.enableNormalEffect",
                "Normal Effect",
                "Enables the normal Froststeel tool effect."
        );

        translateConfigOption(
                "toolEffect.froststeel.doHitParticlesSpawn",
                "Hit Particles",
                "Enables hit particles when attacking an enemy with Froststeel Tools."
        );

        // Tool Effects — Glacialite
        translateConfigOption(
                "toolEffect.glacialite.enable",
                "Enable",
                "Disabling this setting will turn off all tool effects of Glacialite Tools."
        );

        translateConfigOption(
                "toolEffect.glacialite.enableNormalEffect",
                "Normal Effect",
                "Enables the normal Glacialite tool effect."
        );

        translateConfigOption(
                "toolEffect.glacialite.enableFrostNova",
                "Frost Nova",
                "Enables Frost Nova for Glacialite Tools."
        );

        translateConfigOption(
                "toolEffect.glacialite.frostNovaToPassive",
                "Passive Frost Nova",
                "Changes Frost Nova from an active effect to a passive effect."
        );

        translateConfigOption(
                "toolEffect.glacialite.enableFrostNovaSound",
                "Frost Nova Sound",
                "Enables the sound effect played when Frost Nova activates."
        );

        translateConfigOption(
                "toolEffect.glacialite.doHitParticlesSpawn",
                "Hit Particles",
                "Enables hit particles when attacking an enemy with Glacialite Tools."
        );
    }
}
