package noobanidus.libs.particleslib.client.particle.data;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.vector.Vector3d;
import noobanidus.libs.noobutil.util.Codecs;

import java.util.function.Function;

public class WhirlwindParticleData extends GenericParticleData {
  public Vector3d center = Vector3d.ZERO;
  public boolean inverse = false;
  public double radius = 0;

  public static Codec<WhirlwindParticleData> directedCodecFor(ParticleType<?> type) {
    return RecordCodecBuilder.create(instance -> instance.group(
            codecFor(type).fieldOf("data").forGetter(d -> d),
            Codecs.VECTOR_3D.fieldOf("center").forGetter(d -> d.center),
            Codec.BOOL.fieldOf("inverse").forGetter(d -> d.inverse),
            Codec.DOUBLE.fieldOf("radius").forGetter(d -> d.radius))
        .apply(instance, (d, center, inverse, radius) -> new WhirlwindParticleData(type, d, center, inverse, radius)
        ));
  }

  public WhirlwindParticleData(ParticleType<?> type) {
    super(type);
  }

  public WhirlwindParticleData(ParticleType<?> type, GenericParticleData data) {
    super(type);
    this.r1 = data.r1;
    this.g1 = data.g1;
    this.b1 = data.b1;
    this.a1 = data.a1;
    this.r2 = data.r2;
    this.g2 = data.g2;
    this.b2 = data.b2;
    this.a2 = data.a2;
    this.scale1 = data.scale1;
    this.scale2 = data.scale2;
    this.lifetime = data.lifetime;
    this.spin = data.spin;
    this.gravity = data.gravity;
    this.additive = data.additive;
    this.collides = data.collides;
  }

  public WhirlwindParticleData(ParticleType<?> type, GenericParticleData data, Vector3d center, boolean inverse, double radius) {
    this(type, data);
    this.inverse = inverse;
    this.radius = radius;
    this.center = center;
  }

  @Override
  public void writeToNetwork(PacketBuffer buffer) {
    super.writeToNetwork(buffer);
    buffer.writeDouble(center.x).writeDouble(center.y).writeDouble(center.z);
    buffer.writeBoolean(inverse);
    buffer.writeDouble(radius);
  }

  public static class WhirlwindDeserializer extends Deserializer<WhirlwindParticleData> {
    public WhirlwindDeserializer(Function<ParticleType<?>, WhirlwindParticleData> builder) {
      super(builder);
    }

    @Override
    public WhirlwindParticleData fromCommand(ParticleType<WhirlwindParticleData> particleType, StringReader reader) throws CommandSyntaxException {
      WhirlwindParticleData data = super.fromCommand(particleType, reader);
      reader.expect(' ');
      double x1 = reader.readDouble();
      reader.expect(' ');
      double y1 = reader.readDouble();
      reader.expect(' ');
      double z1 = reader.readDouble();
      reader.expect(' ');
      boolean inverse = reader.readBoolean();
      reader.expect(' ');
      double radius = reader.readDouble();
      data.center = new Vector3d(x1, y1, z1);
      data.inverse = inverse;
      data.radius = radius;
      return data;
    }

    @Override
    public WhirlwindParticleData fromNetwork(ParticleType<WhirlwindParticleData> particleType, PacketBuffer buffer) {
      WhirlwindParticleData data = super.fromNetwork(particleType, buffer);
      data.center = new Vector3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
      data.inverse = buffer.readBoolean();
      data.radius = buffer.readDouble();
      return data;
    }
  }

  public static WhirlwindDeserializer WHIRLWIND_DESERIALIZER = new WhirlwindDeserializer(WhirlwindParticleData::new);
}
