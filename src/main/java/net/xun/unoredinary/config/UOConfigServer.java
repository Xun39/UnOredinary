package net.xun.unoredinary.config;

import net.xun.lib.common.api.config.ConfigGroup;
import net.xun.lib.common.api.config.ConfigType;
import net.xun.lib.common.api.config.XunConfig;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.config.server.ArmorEffectConfig;
import net.xun.unoredinary.config.server.ToolEffectConfig;

@XunConfig(modId = UnOredinary.MOD_ID, type = ConfigType.SERVER)
public class UOConfigServer {

    @ConfigGroup(category = "armor_effect")
    public static final ArmorEffectConfig armorEffect = new ArmorEffectConfig();

    @ConfigGroup(category = "tool_effect")
    public static final ToolEffectConfig toolEffect = new ToolEffectConfig();
}