package betteradventuremode.modid;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import static net.minecraft.server.command.CommandManager.literal;

public class Commands 
{

    public void onInitialize()
    {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> 
		dispatcher.register(literal("ClearFood").executes(context -> 
		{
      		//CustomHungerManager hungerManager = (CustomHungerManager)contex getHungerManager();
      		return 1;
    	})));
    }

}
