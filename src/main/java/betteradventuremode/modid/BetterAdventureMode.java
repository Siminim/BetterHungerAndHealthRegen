package betteradventuremode.modid;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import betteradventuremode.modid.network.PayloadServer;

public class BetterAdventureMode implements ModInitializer 
{
	public static final String MOD_ID = "better-adventure-mode";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() 
	{
		PayloadServer.registerPayloads();
		Commands.onInitialize();
		CustomHungerManager.setPlayerHungerManagerEvents();
	}
}