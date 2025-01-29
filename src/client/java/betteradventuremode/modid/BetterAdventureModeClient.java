package betteradventuremode.modid;

import betteradventuremode.modid.network.FoodEatenPayload;
import betteradventuremode.modid.network.FoodEmptyPayload;
import betteradventuremode.modid.network.FoodTimeLeftPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class BetterAdventureModeClient implements ClientModInitializer 
{
	@Override
	public void onInitializeClient() 
	{
		HudRenderCallback.EVENT.register(new FoodHudOverlay());
		setupFoodReciever();
		setupFoodTimeReceiver();
		setupFoodEmptyReceiver();
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