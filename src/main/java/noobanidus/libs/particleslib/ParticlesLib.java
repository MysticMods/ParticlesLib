package noobanidus.libs.particleslib;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import noobanidus.libs.noobutil.registrate.CustomRegistrate;
import noobanidus.libs.particleslib.init.ModBlocks;
import noobanidus.libs.particleslib.init.ModParticles;
import noobanidus.libs.particleslib.setup.ClientInit;
import noobanidus.libs.particleslib.config.ConfigManager;
import noobanidus.libs.particleslib.setup.CommonSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ParticlesLib.MODID)
public class ParticlesLib {
  public static final Logger LOG = LogManager.getLogger();
  public static final String MODID = "particleslib";
  public static CustomRegistrate REGISTRATE;

  public ParticlesLib() {
    REGISTRATE = CustomRegistrate.create(MODID);

    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigManager.COMMON_CONFIG);
    ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigManager.CLIENT_CONFIG);
    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));
    ConfigManager.loadConfig(ConfigManager.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-client.toml"));

    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    modBus.addListener(CommonSetup::setup);

    DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientInit::init);

    ModBlocks.load();
    ModParticles.load();
  }
}
