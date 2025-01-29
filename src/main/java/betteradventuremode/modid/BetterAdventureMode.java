package betteradventuremode.modid;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import betteradventuremode.modid.network.FoodEatenPayload;
import betteradventuremode.modid.network.FoodEmptyPayload;
import betteradventuremode.modid.network.FoodTimeLeftPayload;

public class BetterAdventureMode implements ModInitializer 
{
	public static final String MOD_ID = "better-adventure-mode";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final float NEW_MAX_HEALTH = 6.0f; 

	@Override
	public void onInitialize() 
	{
		registerPayloads();
		setPlayerMaxHealthEvents();

		
	}

	private void setPlayerMaxHealthEvents()
	{
		// On Player Respawn
		ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> 
		{
			updatePlayerHungerManager(newPlayer);
		});

		// On Player Join Server
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> 
		{
			updatePlayerHungerManager(handler.getPlayer());
        });
	}

	private void registerPayloads()
	{
		PayloadTypeRegistry.playS2C().register(FoodEatenPayload.ID, FoodEatenPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(FoodTimeLeftPayload.ID, FoodTimeLeftPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(FoodEmptyPayload.ID, FoodEmptyPayload.CODEC);
	}

	// ----------------------------------------------------------------------------
	// ----------------------------- Helpers --------------------------------------
	// ----------------------------------------------------------------------------
	public static void setPlayerMaxHealth(PlayerEntity player, float health) 
	{
		// Access the max health attribute and set its base value
        player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(health);

        // Ensure the player's health is not greater than the new max health
        if (player.getHealth() > health) 
		{
            player.setHealth((float) health);
        }
	}

	private void updatePlayerHungerManager(PlayerEntity player)
	{
		CustomHungerManager hungerManager = (CustomHungerManager)player.getHungerManager();
		hungerManager.updateFoodOnClient();
		hungerManager.updateFoodTimesOnClient();
		hungerManager.updateMaxHealth();
	}
}