package betteradventuremode.modid;

import betteradventuremode.modid.network.PacketReceiversClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class BetterAdventureModeClient implements ClientModInitializer 
{
	@Override
	public void onInitializeClient() 
	{
		HudRenderCallback.EVENT.register(new FoodHudOverlay());
		PacketReceiversClient.onInitialize();
	}

	
}