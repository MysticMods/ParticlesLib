package noobanidus.libs.particleslib.setup;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonSetup {
  public static void setup (FMLCommonSetupEvent event) {
    event.enqueueWork(() -> {
    });
  }
}
