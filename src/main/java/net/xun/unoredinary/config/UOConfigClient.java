package net.xun.unoredinary.config;

import net.xun.lib.common.api.config.ConfigGroup;
import net.xun.lib.common.api.config.ConfigType;
import net.xun.lib.common.api.config.XunConfig;
import net.xun.unoredinary.UnOredinary;
import net.xun.unoredinary.config.client.ParticleConfig;

@XunConfig(modId = UnOredinary.MOD_ID, type = ConfigType.CLIENT)
public class UOConfigClient {

    @ConfigGroup(category = "particle")
    public static final ParticleConfig particle = new ParticleConfig();
}
