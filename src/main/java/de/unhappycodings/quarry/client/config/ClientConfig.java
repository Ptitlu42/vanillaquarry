package de.unhappycodings.quarry.client.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.NeoForgeConfig;

import java.io.File;

public class ClientConfig {
    public static NeoForgeConfig.Client clientConfig;

    //region General
    public static ModConfigSpec.ConfigValue<Boolean> enableQuarryDarkmode;
    public static ModConfigSpec.ConfigValue<Boolean> enableAreaCardCornerRendering;
    //endregion

    static {
        ModConfigSpec.Builder clientBuilder = new ModConfigSpec.Builder();
        init(clientBuilder);
        clientConfig = clientBuilder.build();
    }

    private static void init(ForgeConfigSpec.Builder clientBuilder) {
        clientBuilder.push("General");
        enableQuarryDarkmode = clientBuilder.comment("Should the quarry gui screen be rendered in Dark Mode.").define("enable_quarry_darkmode", false);
        enableAreaCardCornerRendering = clientBuilder.comment("Render the with Area Card selected corners in world.").define("enable_area_card_corner_rendering", true);
        clientBuilder.pop();
    }

    public static void loadConfigFile(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }
}
