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

public class DirectedParticleData extends GenericParticleData {
  public Vector3d origin = Vector3d.ZERO;
  public Vector3d destination = Vector3d.ZERO;
  public double distance = 0;

  public static Codec<DirectedParticleData> directedCodecFor(ParticleType<?> type) {
    return RecordCodecBuilder.create(instance -> instance.group(
            codecFor(type).fieldOf("data").forGetter(d -> d),
            Codecs.VECTOR_3D.fieldOf("origin").forGetter(d -> d.origin),
            Codecs.VECTOR_3D.fieldOf("destination").forGetter(d -> d.destination),
            Codec.DOUBLE.fieldOf("distance").forGetter(d -> d.distance))
        .apply(instance, (d, origin, dest, distance) -> new DirectedParticleData(type, d, origin, dest, distance)
        ));
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
    this.collides = data.collides;
  }

  public DirectedParticleData(ParticleType<?> type, GenericParticleData data, Vector3d origin, Vector3d destination, double distance) {
    this(type, data);
    this.destination = destination;
    this.distance = distance;
    this.origin = origin;
  }

  @Override
  public void writeToNetwork(PacketBuffer buffer) {
    super.writeToNetwork(buffer);
    buffer.writeDouble(origin.x).writeDouble(origin.y).writeDouble(origin.z);
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
      double x1 = reader.readDouble();
      reader.expect(' ');
      double y1 = reader.readDouble();
      reader.expect(' ');
      double z1 = reader.readDouble();
      reader.expect(' ');
      double x2 = reader.readDouble();
      reader.expect(' ');
      double y2 = reader.readDouble();
      reader.expect(' ');
      double z2 = reader.readDouble();
      reader.expect(' ');
      double distance = reader.readDouble();
      data.origin = new Vector3d(x1, y1, z1);
      data.destination = new Vector3d(x2, y2, z2);
      data.distance = distance;
      return data;
    }

    @Override
    public DirectedParticleData fromNetwork(ParticleType<DirectedParticleData> particleType, PacketBuffer buffer) {
      DirectedParticleData data = super.fromNetwork(particleType, buffer);
      data.origin = new Vector3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
      data.destination = new Vector3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
      data.distance = buffer.readDouble();
      return data;
    }
  }

  public static DirectedDeserializer DIRECTED_DESERIALIZER = new DirectedDeserializer(DirectedParticleData::new);
}
