package betteradventuremode.modid.network;

import betteradventuremode.modid.BetterAdventureMode;
import net.minecraft.util.Identifier;

public class PacketIDs 
{
    public static final Identifier FOOD_EATEN_PACKET_ID = Identifier.of(BetterAdventureMode.MOD_ID, "food_eaten_packet");
    public static final Identifier FOOD_TIME_LEFT_PACKET_ID = Identifier.of(BetterAdventureMode.MOD_ID, "food_time_left_packet");
    public static final Identifier FOOD_EMPTY_PACKET_ID = Identifier.of(BetterAdventureMode.MOD_ID, "food_empty_packet");
}
