package betteradventuremode.modid.network;


import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record FoodEmptyPayload(int slot) implements CustomPayload
{
    public static final CustomPayload.Id<FoodEmptyPayload> ID = new CustomPayload.Id<>(PacketIDs.FOOD_EMPTY_PACKET_ID);

    public static final PacketCodec<RegistryByteBuf, FoodEmptyPayload> CODEC = PacketCodec.tuple
    (
        PacketCodecs.INTEGER, FoodEmptyPayload::slot, 
        FoodEmptyPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() 
    {
        return ID;
    }
}
