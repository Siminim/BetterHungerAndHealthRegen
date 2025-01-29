package betteradventuremode.modid;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import static net.minecraft.server.command.CommandManager.*;

public class Commands 
{

    public static void onInitialize()
    {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> 
		dispatcher.register(literal("clearFood")
		.executes(context -> 
		{
      		CustomHungerManager hungerManager = (CustomHungerManager)context.getSource().getPlayer().getHungerManager();
			hungerManager.clearFoods();
      		return 1;
    	})));
    }

}
