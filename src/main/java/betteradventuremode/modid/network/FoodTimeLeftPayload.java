package betteradventuremode.modid.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record FoodTimeLeftPayload(int timeleft, int slot) implements CustomPayload
{
    public static final CustomPayload.Id<FoodTimeLeftPayload> ID = new CustomPayload.Id<>(PacketIDs.FOOD_TIME_LEFT_PACKET_ID);

    public static final PacketCodec<RegistryByteBuf, FoodTimeLeftPayload> CODEC = PacketCodec.tuple
    (
        PacketCodecs.INTEGER, FoodTimeLeftPayload::timeleft,
        PacketCodecs.INTEGER, FoodTimeLeftPayload::slot, 
        FoodTimeLeftPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() 
    {
        return ID;
    }
}
