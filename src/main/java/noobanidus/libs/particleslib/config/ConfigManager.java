package noobanidus.libs.particleslib.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import noobanidus.libs.particleslib.ParticlesLib;

import java.nio.file.Path;

@Mod.EventBusSubscriber(modid=ParticlesLib.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class ConfigManager {
  private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
  private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

  public static ForgeConfigSpec COMMON_CONFIG;
  public static ForgeConfigSpec CLIENT_CONFIG;

  public static final ForgeConfigSpec.BooleanValue BETTER_LAYERING;

  static {
    COMMON_BUILDER.push("ParticlesLib");
    COMMON_BUILDER.pop();
    COMMON_CONFIG = COMMON_BUILDER.build();
    CLIENT_BUILDER.push("ParticlesLib");
    CLIENT_BUILDER.push("graphics");
    BETTER_LAYERING = CLIENT_BUILDER.comment("Enable better particle/effect layering.")
        .comment("Fixes particles and effects rendering behind clouds and weather.")
        .comment("NOTE: Does NOT work with fabulous graphics mode.")
        .define("betterLayering", true);
    CLIENT_BUILDER.pop();
    CLIENT_BUILDER.pop();
    CLIENT_CONFIG = CLIENT_BUILDER.build();
  }

  public static void loadConfig(ForgeConfigSpec spec, Path path) {
    CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
    configData.load();
    spec.setConfig(configData);
  }

  @SubscribeEvent
  public static void configReloaded(ModConfig.ModConfigEvent event) {
    if (event.getConfig().getType() == ModConfig.Type.COMMON) {
      COMMON_CONFIG.setConfig(event.getConfig().getConfigData());
      ParticlesLib.LOG.info("ParticlesLib common configuration reloaded");
    } else if (event.getConfig().getType() == ModConfig.Type.CLIENT) {
      CLIENT_CONFIG.setConfig(event.getConfig().getConfigData());
      ParticlesLib.LOG.info("ParticlesLib client configuration reloaded");
    }
  }
}
