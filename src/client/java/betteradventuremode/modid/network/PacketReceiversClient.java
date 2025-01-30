package betteradventuremode.modid.network;

import betteradventuremode.modid.CustomHungerManager;
import betteradventuremode.modid.network.Payloads.FoodEatenPayload;
import betteradventuremode.modid.network.Payloads.FoodEmptyPayload;
import betteradventuremode.modid.network.Payloads.FoodTimeLeftPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class PacketReceiversClient 
{
    private static PacketReceiversClient instance;

    private PacketReceiversClient()
    {}

    private static PacketReceiversClient getInstance()
    {
        if (instance == null)
            instance = new PacketReceiversClient();
        
        return instance;
    }

    public static void onInitialize()
    {
        getInstance().setupFoodReciever();
		getInstance().setupFoodTimeReceiver();
		getInstance().setupFoodEmptyReceiver();
    }

    private void setupFoodReciever()
	{
		ClientPlayNetworking.registerGlobalReceiver(FoodEatenPayload.ID, (payload, context) -> 
		{	
			context.client().execute(() -> 
			{
				CustomHungerManager hungerManager = (CustomHungerManager)context.client().player.getHungerManager();
				hungerManager.setFoodEaten(payload.itemStack(), payload.slot());
			});
		});
	}

	private void setupFoodTimeReceiver()
	{
		ClientPlayNetworking.registerGlobalReceiver(FoodTimeLeftPayload.ID, (payload, context) ->
		{
			context.client().execute(() ->
			{
				CustomHungerManager hungerManager = (CustomHungerManager)context.client().player.getHungerManager();
				hungerManager.setTimeLeft(payload.timeleft(), payload.slot());
			});
		});
	}

	private void setupFoodEmptyReceiver()
	{
		ClientPlayNetworking.registerGlobalReceiver(FoodEmptyPayload.ID, (payload, context) ->
		{
			context.client().execute(() ->
			{
				CustomHungerManager hungerManager = (CustomHungerManager)context.client().player.getHungerManager();
				hungerManager.itemsEaten[payload.slot()] = null;
			});
		});
	}
}
