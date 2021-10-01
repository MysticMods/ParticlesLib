package noobanidus.libs.particleslib.client.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.vector.Vector3d;
import noobanidus.libs.noobutil.util.Codecs;

import java.util.function.Function;

public class DirectedParticleData extends GenericParticleData {
  public Vector3d destination = Vector3d.ZERO;
  public double distance = 0;

  public static Codec<DirectedParticleData> directedCodecFor(ParticleType<?> type) {
    return RecordCodecBuilder.create(instance -> instance.group(
            codecFor(type).fieldOf("data").forGetter(d -> d),
            Codecs.VECTOR_3D.fieldOf("destination").forGetter(d -> d.destination),
            Codec.DOUBLE.fieldOf("distance").forGetter(d -> d.distance))
        .apply(instance, (d, dest, distance) -> {
          DirectedParticleData data = new DirectedParticleData(type, d);
          data.destination = dest;
          data.distance = distance;
          return data;
        }));
  }

  public DirectedParticleData(ParticleType<?> type) {
    super(type);
  }

  public DirectedParticleData(ParticleType<?> type, GenericParticleData data) {
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
  }

  public DirectedParticleData(ParticleType<?> type, GenericParticleData data, Vector3d destination, double distance) {
    this(type, data);
    this.destination = destination;
    this.distance = distance;
  }

  @Override
  public void writeToNetwork(PacketBuffer buffer) {
    super.writeToNetwork(buffer);
    buffer.writeDouble(destination.x).writeDouble(destination.y).writeDouble(destination.z);
    buffer.writeDouble(distance);
  }

  public static class DirectedDeserializer extends Deserializer<DirectedParticleData> {
    public DirectedDeserializer(Function<ParticleType<?>, DirectedParticleData> builder) {
      super(builder);
    }

    @Override
    public DirectedParticleData fromCommand(ParticleType<DirectedParticleData> particleType, StringReader reader) throws CommandSyntaxException {
      DirectedParticleData data = super.fromCommand(particleType, reader);
      reader.expect(' ');
      double x = reader.readDouble();
      reader.expect(' ');
      double y = reader.readDouble();
      reader.expect(' ');
      double z = reader.readDouble();
      reader.expect(' ');
      double distance = reader.readDouble();
      data.destination = new Vector3d(x, y, z);
      data.distance = distance;
      return data;
    }

    @Override
    public DirectedParticleData fromNetwork(ParticleType<DirectedParticleData> particleType, PacketBuffer buffer) {
      DirectedParticleData data = super.fromNetwork(particleType, buffer);
      data.destination = new Vector3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
      data.distance = buffer.readDouble();
      return data;
    }
  }

  public static DirectedDeserializer DIRECTED_DESERIALIZER = new DirectedDeserializer(DirectedParticleData::new);
}
