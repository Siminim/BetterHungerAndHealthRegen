package betteradventuremode.modid.network.Payloads;


import betteradventuremode.modid.network.PacketIDs;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record FoodEatenPayload(ItemStack itemStack, int slot) implements CustomPayload
{
    public static final CustomPayload.Id<FoodEatenPayload> ID = new CustomPayload.Id<>(PacketIDs.FOOD_EATEN_PACKET_ID);

    public static final PacketCodec<RegistryByteBuf, FoodEatenPayload> CODEC = PacketCodec.tuple
    (
        ItemStack.PACKET_CODEC, FoodEatenPayload::itemStack,
        PacketCodecs.INTEGER, FoodEatenPayload::slot, 
        FoodEatenPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() 
    {
        return ID;
    }
}
