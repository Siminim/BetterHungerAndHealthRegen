package betteradventuremode.modid.network;

import betteradventuremode.modid.network.Payloads.FoodEatenPayload;
import betteradventuremode.modid.network.Payloads.FoodEmptyPayload;
import betteradventuremode.modid.network.Payloads.FoodTimeLeftPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class PayloadServer 
{
    public static void registerPayloads()
	{
		PayloadTypeRegistry.playS2C().register(FoodEatenPayload.ID, FoodEatenPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(FoodTimeLeftPayload.ID, FoodTimeLeftPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(FoodEmptyPayload.ID, FoodEmptyPayload.CODEC);
	}
}
